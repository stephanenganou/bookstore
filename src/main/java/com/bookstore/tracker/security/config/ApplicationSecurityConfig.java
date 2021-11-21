package com.bookstore.tracker.security.config;

import com.bookstore.tracker.security.BookstoreUserDetailsService;
import com.bookstore.tracker.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Profile("!test")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final int PASSWORD_ENCODER_LENGTH = 11;
    private final BookstoreUserDetailsService bookstoreUserDetailsService;
    private final UserService userService;

    @Autowired
    public ApplicationSecurityConfig(final BookstoreUserDetailsService bookstoreUserDetailsService,
                                     final UserService userService) {
        super();
        this.bookstoreUserDetailsService = bookstoreUserDetailsService;
        this.userService = userService;
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {

                if (userService.isNotBockedDomain(authentication.getName())) {
                    log.error("Invalid username: {}", authentication.getName());
                    throw new UsernameNotFoundException("Invalid username: " + authentication.getName());
                } else {
                    return super.authenticate(authentication);
                }
            }
        };

        authenticationProvider.setUserDetailsService(bookstoreUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(PASSWORD_ENCODER_LENGTH));
        authenticationProvider.setAuthoritiesMapper(getAuthorityMapper());

        return authenticationProvider;
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(getAuthenticationProvider());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/css/*", "/js/*", "/image/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/user/authenticate")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/book/list")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutSuccessUrl("/home")
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }

    private GrantedAuthoritiesMapper getAuthorityMapper() {
        final SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        authorityMapper.setDefaultAuthority("USER");

        return authorityMapper;
    }
}

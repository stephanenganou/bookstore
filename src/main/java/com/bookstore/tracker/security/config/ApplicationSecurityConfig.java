package com.bookstore.tracker.security.config;

import com.bookstore.tracker.security.BookstoreUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private static int PASSWORD_ENCODER_LENGTH = 11;
    private final BookstoreUserDetailsService bookstoreUserDetailsService;

    @Autowired
    public ApplicationSecurityConfig(BookstoreUserDetailsService bookstoreUserDetailsService) {
        super();
        this.bookstoreUserDetailsService = bookstoreUserDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(bookstoreUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(PASSWORD_ENCODER_LENGTH));
        authenticationProvider.setAuthoritiesMapper(getAuthorityMapper());

        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

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
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        authorityMapper.setDefaultAuthority("USER");

        return authorityMapper;
    }
}

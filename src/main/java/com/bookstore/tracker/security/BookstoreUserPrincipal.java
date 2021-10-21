package com.bookstore.tracker.security;

import com.bookstore.tracker.data.entity.Authority;
import com.bookstore.tracker.data.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
public class BookstoreUserPrincipal implements UserDetails {

    private final User user;

    private final List<Authority> userRoles;

    public BookstoreUserPrincipal(User user, List<Authority> userRoles) {
        this.user = user;
        this.userRoles = userRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (null == userRoles) {
            return Collections.emptySet();
        }
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        userRoles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthGroup())));

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}

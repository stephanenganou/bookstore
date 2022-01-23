package com.bookstore.tracker.security;

import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface enforces specifics methods (UserDetailsService related) for classes which will implement it.
 */
public interface BookstoreUserDetailsService extends UserDetailsService {
}

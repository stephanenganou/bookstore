package com.bookstore.tracker.security;

import com.bookstore.tracker.data.dao.UserDao;
import com.bookstore.tracker.data.entity.User;
import com.bookstore.tracker.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
public class BookstoreUserDetailsService implements UserDetailsService {

    private final UserDao userDao;
    private final LoginService loginService;

    @Autowired
    public BookstoreUserDetailsService(UserDao userDao, LoginService loginService) {
        super();
        this.userDao = userDao;
        this.loginService = loginService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
        if (null == user) {
            throw new UsernameNotFoundException("cannot find username: " + username);
        }
        if (loginService.isNotBockedDomain(username)) {
            throw new UsernameNotFoundException("Invalid username: " + username);
        }

        return new BookstoreUserPrincipal(user, user.getRoles());
    }
}

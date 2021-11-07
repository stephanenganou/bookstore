package com.bookstore.tracker.security;

import com.bookstore.tracker.data.dao.UserDao;
import com.bookstore.tracker.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
public class BookstoreUserDetailsServiceImpl implements BookstoreUserDetailsService {

    private final UserDao userDao;

    @Autowired
    public BookstoreUserDetailsServiceImpl(final UserDao userDao) {
        super();
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
        if (null == user) {
            throw new UsernameNotFoundException("cannot find username: " + username);
        }

        return new BookstoreUserPrincipal(user, user.getRoles());
    }

}

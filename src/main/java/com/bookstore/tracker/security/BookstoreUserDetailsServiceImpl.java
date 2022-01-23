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
 * <p>
 * This class helps to retrieve user information specific for security purposes.
 */
@Service
public class BookstoreUserDetailsServiceImpl implements BookstoreUserDetailsService {

    private final UserDao userDao;

    @Autowired
    public BookstoreUserDetailsServiceImpl(final UserDao userDao) {
        super();
        this.userDao = userDao;
    }

    /**
     * This method returns user information based upon the username field.
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final User user = userDao.findByUserName(username);
        if (null == user) {
            throw new UsernameNotFoundException("cannot find username: " + username);
        }

        return new BookstoreUserPrincipal(user, user.getRoles());
    }

}

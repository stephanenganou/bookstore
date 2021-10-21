package com.bookstore.tracker.security;

import com.bookstore.tracker.data.dao.AutorityDao;
import com.bookstore.tracker.data.dao.UserDao;
import com.bookstore.tracker.data.entity.Autority;
import com.bookstore.tracker.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
public class BookstoreUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    private final AutorityDao autorityDao;

    @Autowired
    public BookstoreUserDetailsService(UserDao userDao, AutorityDao autorityDao) {
        super();
        this.userDao = userDao;
        this.autorityDao = autorityDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
        if (null == user) {
            throw new UsernameNotFoundException("cannot find username: " + username);
        }
        List<Autority> userRoles = autorityDao.findByUserName(username);

        return new BookstoreUserPrincipal(user, userRoles);
    }
}

package com.bookstore.tracker.service.user;

import com.bookstore.tracker.data.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface enforces specifics methods (User related) for classes which will implement it.
 */
@Service
public interface UserService {

    /**
     * Method used to check whether or not a user domain name is allowed into the system.
     */
    boolean isNotBockedDomain(String userName);

    /**
     * Method responsible for returning the User currently connected in the system.
     */
    User getLoggedUser();
}

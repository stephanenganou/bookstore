package com.bookstore.tracker.service.user;

import com.bookstore.tracker.data.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
public interface UserService {

    boolean isNotBockedDomain(String userName);

    User getLoggedUser();
}

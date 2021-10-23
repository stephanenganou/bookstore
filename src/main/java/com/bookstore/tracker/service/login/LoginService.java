package com.bookstore.tracker.service.login;

import org.springframework.stereotype.Service;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Service
public interface LoginService {

    boolean isNotBockedDomain(String userName);

}

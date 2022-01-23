package com.bookstore.tracker.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface enforces specifics methods (Login related) for classes which will implement it.
 */
@Controller
public interface LoginController {

    /**
     * Method used to return the login page.
     */
    String getLoginPage(boolean isError, Model loginModel);

    /**
     * Method used to get the index page.
     */
    String getIndexPage(Model loginModel);

    /**
     * Method used to return the home page.
     */
    String getHomePage(Model loginModel);
}
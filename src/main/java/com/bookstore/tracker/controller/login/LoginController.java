package com.bookstore.tracker.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Controller
public interface LoginController {

    String getLoginPage(boolean isError, Model loginModel);

    String getIndexPage(Model loginModel);

    String getHomePage(Model loginModel);
}
package com.bookstore.tracker.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class LoginControllerImpl implements LoginController {

    @Override
    public String getLoginPage(boolean isError, Model loginModel) {
        if (isError) {
            loginModel.addAttribute("loginError", isError);
        }

        return "login";
    }

    @Override
    public String getIndexPage() {
        return getHomePage();
    }

    @Override
    public String getHomePage() {
        return "home";
    }
}

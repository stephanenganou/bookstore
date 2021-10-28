package com.bookstore.tracker.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginControllerImpl implements LoginController {

    @GetMapping("/login")
    @Override
    public String getLoginPage(boolean isError, Model loginModel) {
        if (isError) {
            loginModel.addAttribute("loginError", isError);
        }

        return "login";
    }

    @GetMapping("/")
    @Override
    public String getIndexPage() {
        return getHomePage();
    }

    @GetMapping("/home")
    @Override
    public String getHomePage() {
        return "home";
    }
}

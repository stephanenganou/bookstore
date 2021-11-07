package com.bookstore.tracker.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Controller
public class LoginControllerImpl implements LoginController {

    @GetMapping("/login")
    @Override
    public String getLoginPage(@RequestParam(value = "error", required = false) boolean isError, Model loginModel) {
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

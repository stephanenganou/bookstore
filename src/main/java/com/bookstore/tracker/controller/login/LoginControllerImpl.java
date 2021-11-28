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
    public String getLoginPage(@RequestParam(value = "error", required = false) final boolean isError,
                               final Model loginModel) {
        if (isError) {
            loginModel.addAttribute("loginError", isError);
        }

        return "login";
    }

    @GetMapping("/")
    @Override
    public String getIndexPage(final Model model) {
        return getHomePage(model);
    }

    @GetMapping("/home")
    @Override
    public String getHomePage(final Model model) {
        return "home";
    }
}

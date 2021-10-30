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
public interface LoginController {

    @GetMapping("/login")
    String getLoginPage(@RequestParam(value = "error", required = false) boolean isError, Model loginModel);

    @GetMapping("/")
    String getIndexPage();

    @GetMapping("/home")
    String getHomePage();
}

package be.ehb.eindproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // Looks for register.html in src/main/resources/templates
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Looks for login.html in src/main/resources/templates
    }
}

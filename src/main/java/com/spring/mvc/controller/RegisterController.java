package com.spring.mvc.controller;

import com.spring.mvc.entity.User;
import com.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registrationPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPostPage(String username, String email, String password, String confirm_password, Model model) {
        if (username == null || username.isEmpty()) {
            model.addAttribute("error", "Please fill user name field");
            return "register";
        }

        if (email == null || email.isEmpty()) {
            model.addAttribute("error", "Please fill email field");
            return "register";
        }

        if (password == null || password.isEmpty()) {
            model.addAttribute("error", "Please fill password field");
            return "register";
        }

        if (!password.equals(confirm_password)) {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }

        try {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);

            userService.registerUser(user);
            return "redirect:login";
        } catch (Exception e) {
            model.addAttribute("error", "Error occurred during registration");
            return "register";
        }
    }
}
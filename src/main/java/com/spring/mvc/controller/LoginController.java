package com.spring.mvc.controller;

import com.spring.mvc.dto.LoginDTO;
import com.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private UserService usersService;

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Map<String, List<String>> map, Model model, String error) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("loginDTO", new LoginDTO());
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        roles.add("USER");
        map.put("roles", roles);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username"))
                cookie.setMaxAge(0);
            else if (cookie.getName().equals("JSESSIONID"))
                cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        session.removeAttribute("username");
        session.invalidate();
        return "redirect:index.jsp";
    }

    @PostMapping("/login")
    public String loginPostPage(LoginDTO dto, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        try {
            if (dto.getEmail().equals("admin@admin.com") && dto.getPassword().equals("admin@123")) {
                Cookie cookie = new Cookie("username", "admin");
                session.setAttribute("username", "admin");
                response.addCookie(cookie);
                return "redirect:admin";
            } else if (this.usersService.loginUser(dto)) {
                String username = this.usersService.getUserByEmailId(dto.getEmail());
                Cookie cookie = new Cookie("username", username);
                session.setAttribute("username", username);
                response.addCookie(cookie);

                Cookie emailCookie = new Cookie("email", dto.getEmail());
                session.setAttribute("email", dto.getEmail());
                response.addCookie(emailCookie);
                return "redirect:dashboard";
            }
        } catch (Exception e) {
            return "redirect:login?error=Invalid credentials";
        }
        return "redirect:login?error=Invalid credentials";
    }
}
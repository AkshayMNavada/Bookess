package com.spring.mvc.controller;

import com.spring.mvc.entity.Book;
import com.spring.mvc.service.BookService;
import com.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService usersService;

    @GetMapping
    public String adminPage(Map<String, List<Book>> map) {
        map.put("books", this.bookService.getAllBooks());
        return "admin";
    }

    @GetMapping("/book")
    public String addBookForm() {
        return "bookform";
    }

    @GetMapping("/users")
    public String getAllUsers() {
        return "users";
    }
}
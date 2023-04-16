package com.spring.mvc.controller;

import com.spring.mvc.entity.Book;
import com.spring.mvc.service.BookService;
import com.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showDashboard(Model model, HttpSession session, Map<String, List<Book>> map) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "dashboard";
    }

    @GetMapping("/likeBook/{index}")
    public String likeBook(@PathVariable("index") String index, HttpSession session) {
        int id = Integer.parseInt(index) + 1;
        String email = (String) session.getAttribute("email");
        userService.addToLikedBooks(email, id);
        return "redirect:/dashboard";
    }

    @GetMapping("/readLater/{index}")
    public String readLater(@PathVariable("index") String index, HttpSession session) {
        int id = Integer.parseInt(index) + 1;
        String email = (String) session.getAttribute("email");
        userService.addToReadLaterBooks(email, id);
        return "redirect:/dashboard";
    }

    @GetMapping("/unlikeBook/{index}")
    public String unlikeBook(@PathVariable("index") String index, HttpSession session) {
        int id = Integer.parseInt(index) + 1;
        String email = (String) session.getAttribute("email");
        userService.removeFromLikedBooks(email, id);
        return "redirect:/dashboard";
    }

    @GetMapping("/removeReadLater/{index}")
    public String removeReadLater(@PathVariable("index") String index, HttpSession session) {
        int id = Integer.parseInt(index) + 1;
        String email = (String) session.getAttribute("email");
        userService.removeFromReadLater(email, id);
        return "redirect:/dashboard";
    }

    @GetMapping("/liked-books")
    public String getLikedBooks(Map<String, List<Book>> map, HttpSession session) {
        String email = (String) session.getAttribute("email");
        map.put("likedBooks", this.userService.getLikedBooks(email));
        return "liked-books";
    }

    @GetMapping("/read-later-books")
    public String getReadLaterBooks(Map<String, List<Book>> readLaterMap, HttpSession session) {
        String email = (String) session.getAttribute("email");
        readLaterMap.put("readLaterBooks", this.userService.getReadLaterBooks(email));
        return "read-later-books";
    }
}
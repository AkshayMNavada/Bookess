package com.spring.mvc.controller;

import com.spring.mvc.entity.Book;
import com.spring.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/addBook")
    public String addBookPage() {
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addBookPostPage(@RequestParam("title") String title,
                                  @RequestParam("author") String author,
                                  @RequestParam("description") String description,
                                  RedirectAttributes redirectAttributes) {
        if (title.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Title can not be empty");
            return "redirect:/addBook";
        }

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setDescription(description);

        bookService.addBook(book);
        redirectAttributes.addFlashAttribute("success", "Book added successfully.");
        return "redirect:/dashboard";
    }
}
package com.spring.mvc.service;

import com.spring.mvc.database.BookDatabase;
import com.spring.mvc.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDatabase bookDatabase;

    @Transactional
    public void addBook(Book book) {
        bookDatabase.addBook(book);
    }

    public List<Book> getAllBooks() {
        return bookDatabase.getAllBooks();
    }

}

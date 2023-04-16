package com.spring.mvc.service;

import com.spring.mvc.database.UserDatabase;
import com.spring.mvc.dto.LoginDTO;
import com.spring.mvc.entity.Book;
import com.spring.mvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDatabase userDatabase;

    public List<User> getAllUsers() {
        return userDatabase.getAllUsers();
    }

    public void registerUser(User user) {
        userDatabase.registerUser(user);
    }

    public boolean loginUser(LoginDTO dto) throws Exception {
        return this.userDatabase.loginUser(dto);
    }

    public String getUserByEmailId(String email) throws Exception {
        return this.userDatabase.getUserByEmail(email);
    }

    public void addToLikedBooks(String email, int bookId) {
        userDatabase.addToLikedBooks(email, bookId);
    }

    public void addToReadLaterBooks(String email, int bookId) {
        userDatabase.addToReadLater(email, bookId);
    }

    public void removeFromLikedBooks(String email, int bookId) {
        userDatabase.removeFromLikedBooks(email, bookId);
    }

    public void removeFromReadLater(String email, int bookId) {
        userDatabase.removeFromReadLater(email, bookId);
    }

    public List<Book> getLikedBooks(String email) {
        return userDatabase.getLikedBooks(email);
    }

    public List<Book> getReadLaterBooks(String email) {
        return userDatabase.getReadLaterBooks(email);
    }
}
package com.spring.mvc.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    private String email;
    private String username;
    private String password;

    @ManyToMany
    @JoinTable(name="user_read_later_books", joinColumns=@JoinColumn(name="email"), inverseJoinColumns=@JoinColumn(name="book_id"))
    private List<Book> readLaterBooks = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="user_liked_books", joinColumns=@JoinColumn(name="email"), inverseJoinColumns=@JoinColumn(name="book_id"))
    private List<Book> likedBooks = new ArrayList<>();

    public User() {

    }

    public User(String username, String email, String password) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getReadLaterBooks() {
        return readLaterBooks;
    }

    public void setReadLaterBooks(List<Book> readLaterBooks) {
        this.readLaterBooks = readLaterBooks;
    }

    public List<Book> getLikedBooks() {
        return likedBooks;
    }

    public void setLikedBooks(List<Book> likedBooks) {
        this.likedBooks = likedBooks;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", readLaterBooks=" + readLaterBooks +
                ", likedBooks=" + likedBooks +
                '}';
    }
}
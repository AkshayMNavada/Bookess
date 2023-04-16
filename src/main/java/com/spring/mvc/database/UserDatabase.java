package com.spring.mvc.database;

import com.spring.mvc.dto.LoginDTO;
import com.spring.mvc.entity.Book;
import com.spring.mvc.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDatabase {
    @Autowired
    private SessionFactory factory;

    public List<User> getAllUsers() {
        Session session = factory.openSession();
        List<User> users = session.createQuery("from User", User.class).getResultList();
        session.close();
        return users;
    }

    public void registerUser(User newUser) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, newUser.getUsername());
        if (user != null) {
            session.close();
            throw new RuntimeException("User with email " + user.getUsername() + " already exists");
        }
        session.save(newUser);
        transaction.commit();
        session.close();
    }

    public boolean loginUser(LoginDTO dto) throws Exception {
        return validateUser(dto);
    }

    public boolean validateUser(LoginDTO dto) throws Exception {
        Session session = factory.openSession();
        User user = session.get(User.class, dto.getEmail());
        if (user == null) {
            session.close();
            throw new Exception("User not found");
        } else if (!dto.getPassword().equals(user.getPassword())) {
            session.close();
            throw new Exception("Incorrect password");
        }
        session.close();
        return true;
    }

    public String getUserByEmail(String email) throws Exception {
        String username;
        Session session = factory.openSession();
        User user = session.get(User.class, email);
        if (user == null) {
            session.close();
            throw new Exception("User not found");
        } else {
            username = user.getUsername();
        }
        session.close();
        return username;
    }

    public void addToLikedBooks(String email, int bookId) {
        try {
            Session session = factory.openSession();
            User user = session.get(User.class, email);
            Book book = session.get(Book.class, bookId);
            book.setLiked(true);
            List<Book> likedBooks = user.getLikedBooks();
            likedBooks.add(book);

            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void addToReadLater(String email, int bookId) {
        try {
            Session session = factory.openSession();
            User updateUser = session.get(User.class, email);
            Book book = session.get(Book.class, bookId);
            book.setReadLater(true);
            List<Book> readLaterBooks = updateUser.getReadLaterBooks();
            readLaterBooks.add(book);
            Transaction transaction = session.beginTransaction();
            session.update(updateUser);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Book> getLikedBooks(String email) {
        Session session = factory.openSession();
        User user = session.get(User.class, email);
        List<Book> books = user.getLikedBooks();
        System.out.println(books);
        session.close();
        return books;
    }

    public List<Book> getReadLaterBooks(String email) {
        Session session = factory.openSession();
        User user = session.get(User.class, email);
        List<Book> books = user.getReadLaterBooks();
        System.out.println(books);
        session.close();
        return books;
    }

    public void removeFromLikedBooks(String email, int bookId) {
        try {
            Session session = factory.openSession();
            User user = session.get(User.class, email);
            Book book = session.get(Book.class, bookId);
            book.setLiked(false);
            List<Book> likedBooks = user.getLikedBooks();
            likedBooks.remove(book);

            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void removeFromReadLater(String email, int bookId) {
        try {
            Session session = factory.openSession();
            User user = session.get(User.class, email);
            Book book = session.get(Book.class, bookId);
            book.setReadLater(false);
            List<Book> readLaterBooks = user.getReadLaterBooks();
            readLaterBooks.remove(book);

            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
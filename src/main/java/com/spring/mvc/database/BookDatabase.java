package com.spring.mvc.database;

import com.spring.mvc.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class BookDatabase {
    @Autowired
    private SessionFactory sessionFactory;

    public void addBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(book);
        tx.commit();
        session.close();
    }

    public Book getBookById(Long id) {
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, id);
        session.close();
        return book;
    }

    public List<Book> getAllBooks() {
        Session session = sessionFactory.openSession();
        List<Book> books = session.createQuery("from Book", Book.class).getResultList();
        session.close();
        return books;
    }

    public void updateBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.update(book);
        tx.commit();
        session.close();
    }

    public void deleteBook(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        session.delete(book);
    }
}
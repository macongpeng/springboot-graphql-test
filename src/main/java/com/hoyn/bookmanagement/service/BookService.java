package com.hoyn.bookmanagement.service;

import com.hoyn.bookmanagement.model.Book;
import com.hoyn.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    /*private List<Book> books = new ArrayList<>();

    @PostConstruct
    private void init() {
        books.add(new Book(1, "Quran", 604, 3));
        books.add(new Book(2, "Harry Potter", 700, 2));
        books.add(new Book(3, "Foobar", 100, 1));
        books.add(new Book(4, "Spring Boot", 344, 2));
    }*/
    @Autowired
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id).stream().findFirst();
    }
}

package com.hoyn.bookmanagement.service;

import com.hoyn.bookmanagement.model.Book;
import com.hoyn.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id).stream().findFirst();
    }

    public Book save(Book book) {
        Book savedBook;
        savedBook = bookRepository.save(book);
        return savedBook;
    }
}

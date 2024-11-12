package com.hoyn.bookmanagement.service;

import com.hoyn.bookmanagement.model.Book;
import com.hoyn.bookmanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.hoyn.bookmanagement.config.CachingConfig.BOOK_CACHE;

@Service
public class BookService {
    @Autowired
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable(value = BOOK_CACHE, keyGenerator="customKeyGenerator")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Cacheable(value = BOOK_CACHE, key = "#id")
    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id).stream().findFirst();
    }

    @CachePut(cacheNames = BOOK_CACHE, key = "#book.id")
    public Book save(Book book) {
        Book savedBook;
        savedBook = bookRepository.save(book);
        return savedBook;
    }
}

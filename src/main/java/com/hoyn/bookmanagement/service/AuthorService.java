package com.hoyn.bookmanagement.service;

import com.hoyn.bookmanagement.model.Author;
import com.hoyn.bookmanagement.model.Book;
import com.hoyn.bookmanagement.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Cacheable(value = "complete-response-cache", keyGenerator="customKeyGenerator")
    public Map<Book, Author> getAuthors(List<Book> books) {
        return books.stream()
                .collect(Collectors.toMap(
                        book -> book,
                        book -> authorRepository.findById(book.getAuthor().getId()).orElse(null),
                        (existing, replacement) -> existing
                ));
    }
}

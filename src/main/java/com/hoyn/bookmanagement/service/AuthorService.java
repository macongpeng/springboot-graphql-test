package com.hoyn.bookmanagement.service;

import com.hoyn.bookmanagement.model.Author;
import com.hoyn.bookmanagement.repository.AuthorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    /*private List<Author> authors = new ArrayList<>();

    @PostConstruct
    private void init() {
        authors.add(new Author(1, "Mama Samba"));
        authors.add(new Author(2, "Jamila"));
        authors.add(new Author(3, "Allah"));
    }*/
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }
}

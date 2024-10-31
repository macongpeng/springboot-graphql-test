package com.hoyn.bookmanagement.repository;

import com.hoyn.bookmanagement.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Find author by id exact match
    Optional<Author> findById(Long id);
}

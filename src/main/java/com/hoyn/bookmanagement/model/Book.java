package com.hoyn.bookmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column
    String name;

    @Column(name = "page_count")
    Integer pageCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    /*public Book(Long id, String name, Integer pageCount, Long authorId) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
        this.authorId = authorId;
    }*/
    /*public static List<Book> books = Arrays.asList(
            new Book(1, "Quran", 604, 3),
            new Book(2, "Harry Potter", 700, 2),
            new Book(3, "Foobar", 100, 1),
            new Book(4, "Spring Boot", 344, 2)
    );

    public static Optional<Book> getBookById(Integer id) {
        return books.stream()
                .filter(b -> b.id.equals(id))
                .findFirst();
    }*/
}

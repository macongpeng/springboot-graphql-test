package com.hoyn.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Setter
@Getter
@NoArgsConstructor
public class Book {
    Integer id;
    String name;
    Integer pageCount;
    Integer authorId;
    public Book(Integer id, String name, Integer pageCount, Integer authorId) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
        this.authorId = authorId;
    }
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

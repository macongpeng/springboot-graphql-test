package com.hoyn.bookmanagement.controller;

import com.hoyn.bookmanagement.model.Author;
import com.hoyn.bookmanagement.model.Book;
import com.hoyn.bookmanagement.service.AuthorService;
import com.hoyn.bookmanagement.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.junit.jupiter.api.Assertions.assertEquals;

@GraphQlTest(BookController.class)
@Import({BookService.class, AuthorService.class})
class BookControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @Test
    void canGetBooks() {
        graphQlTester
                .documentName("books")
                .execute()
                .path("books")
                .entityList(Book.class)
                .hasSize(4);

    }

    @Test
    void canGetBookById() {
        graphQlTester
                .documentName("bookById")
                .execute()
                .path("bookById.author")
                .entity(Author.class)
                .satisfies(author -> {
                    assertEquals("Allah",author.getName());
                    assertEquals(3,author.getId());
                    //assertEquals(3,book.authorId());
                });

    }
}
package com.hoyn.bookmanagement.controller;

import com.hoyn.bookmanagement.model.Author;
import com.hoyn.bookmanagement.model.Book;
import com.hoyn.bookmanagement.repository.AuthorRepository;
import com.hoyn.bookmanagement.repository.BookRepository;
import com.hoyn.bookmanagement.service.AuthorService;
import com.hoyn.bookmanagement.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@GraphQlTest(BookController.class)
@Import({BookService.class, AuthorService.class})
class BookControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    BookService bookService;

    @Autowired
    AuthorService authorService;

    @MockBean
    AuthorRepository authorRepository;

    @MockBean
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        Author author = new Author();
        author.setId(3L);
        author.setName("Allah");
        author.setCountry("US");

        Book book1 = new Book();
        book1.setId(1L);
        book1.setName("Quran");
        book1.setPageCount(604);
        book1.setAuthor(author);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setName("Harry Potter");
        book2.setPageCount(700);
        book2.setAuthor(author);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));
        when(bookRepository.findById(anyInt())).thenReturn(Arrays.asList(book1));

        when(authorRepository.findById(any())).thenReturn(Optional.of(author));
    }

    @Test
    void canGetBooks() {
        graphQlTester
                .documentName("books")
                .execute()
                .path("books")
                .entityList(Book.class)
                .hasSize(2);

    }

    @Test
    void canGetBookById() {
        graphQlTester
                .documentName("bookById")
                .execute()
                .path("bookById")
                .entity(Book.class)
                .satisfies(book -> {
                    assertEquals("Quran", book.getName());
                    assertEquals(1, book.getId());
                });

    }

    @Test
    void canGetBookByIdWithCorrectAuthor() {
        Book book = graphQlTester
                .documentName("bookById")
                .execute()
                .path("bookById")
                .entity(Book.class)
                .get();
        graphQlTester
                .documentName("bookById")
                .execute()
                .path("bookById.author")
                .entity(Author.class)
                .satisfies(author -> {
                    assertEquals("Allah", author.getName());
                    assertEquals(3, author.getId());
                    //assertEquals(3,book.authorId());
                });

    }
}
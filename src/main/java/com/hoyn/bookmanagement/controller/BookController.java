package com.hoyn.bookmanagement.controller;

import com.hoyn.bookmanagement.model.Author;
import com.hoyn.bookmanagement.model.Book;
import com.hoyn.bookmanagement.service.AuthorService;
import com.hoyn.bookmanagement.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }
    @QueryMapping
    public List<Book> books() {
        return bookService.findAll();
    }

    @QueryMapping
    public Optional<Book> bookById(@Argument Integer id) {
        return bookService.getBookById(id);
    }

    @SchemaMapping
    public Optional<Author> author(Book book) {
        return authorService.getAuthorById(book.getAuthor().getId());
    }
}

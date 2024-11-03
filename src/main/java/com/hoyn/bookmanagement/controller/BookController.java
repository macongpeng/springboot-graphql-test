package com.hoyn.bookmanagement.controller;

import com.hoyn.bookmanagement.model.Author;
import com.hoyn.bookmanagement.model.Book;
import com.hoyn.bookmanagement.model.BookInput;
import com.hoyn.bookmanagement.service.AuthorService;
import com.hoyn.bookmanagement.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import graphql.GraphQLError;


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

    // New Mutation Mappings
    @MutationMapping
    public Book newBook(@Argument BookInput book) {
        Author author = authorService.getAuthorById(book.getAuthorId())
                .orElseThrow(() -> new NoSuchElementException("Author not found with id: " + book.getAuthorId()));
        return bookService.save(new Book(null, book.getName(), book.getPageCount(), author));
    }

    @GraphQlExceptionHandler
    public GraphQLError handle(@NonNull Throwable ex, @NonNull DataFetchingEnvironment environment){
        return GraphQLError
                .newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(ex.getMessage())
                .path(environment.getExecutionStepInfo().getPath())
                .location(environment.getField().getSourceLocation())
                .build();
    }
}

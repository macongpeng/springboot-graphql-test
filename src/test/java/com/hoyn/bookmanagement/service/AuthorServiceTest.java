package com.hoyn.bookmanagement.service;

import com.hoyn.bookmanagement.config.CustomKeyGenerator;
import com.hoyn.bookmanagement.model.Author;
import com.hoyn.bookmanagement.model.Book;
import com.hoyn.bookmanagement.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.hoyn.bookmanagement.config.CachingConfig.BOOK_CACHE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@Import({AuthorServiceTest.CachingTestConfig.class, AuthorService.class})
public class AuthorServiceTest {

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @EnableCaching
    @Configuration
    public static class CachingTestConfig {

        @Bean("customKeyGenerator")
        public KeyGenerator keyGenerator() {
            return new CustomKeyGenerator();
        }

        @Bean
        public CacheManager cacheManager() {
            return new ConcurrentMapCacheManager(BOOK_CACHE);
        }
    }

    @BeforeEach
    public void setUp() {
        reset(authorRepository);
    }

    @Test
    public void testGetAuthors_Cacheable() {
        // Arrange
        Author author1 = new Author();
        author1.setId(1L);
        Author author2 = new Author();
        author2.setId(2L);

        Book book1 = new Book();
        book1.setAuthor(author1);
        Book book2 = new Book();
        book2.setAuthor(author2);

        List<Book> books = Arrays.asList(book1, book2);

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author1));
        when(authorRepository.findById(2L)).thenReturn(Optional.of(author2));

        // Act
        Map<Book, Author> result1 = authorService.getAuthors(books);
        int callCount = 2;
        // Assert
        assertEquals(2, result1.size());
        assertEquals(author1, result1.get(book1));
        assertEquals(author2, result1.get(book2));
        verify(authorRepository, times(callCount)).findById(any());

        Map<Book, Author> result2 = authorService.getAuthors(books);
        assertEquals(2, result2.size());
        assertEquals(author1, result2.get(book1));
        assertEquals(author2, result2.get(book2));
        verify(authorRepository, times(callCount)).findById(any());
    }

    @Test
    public void testGetAuthorsWithDuplicatedBooks_Cacheable() {
        // Arrange
        Author author1 = new Author();
        author1.setId(1L);
        Author author2 = new Author();
        author2.setId(2L);

        Book book1 = new Book();
        book1.setAuthor(author1);
        Book book2 = new Book();
        book2.setAuthor(author2);
        /*Book duplicateBook1 = new Book();
        duplicateBook1.setId(book1.getId());
        duplicateBook1.setName(book1.getName());
        duplicateBook1.setPageCount(book1.getPageCount());
        duplicateBook1.setAuthor(author1);*/

        List<Book> books = Arrays.asList(book1, book2, book1);

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author1));
        when(authorRepository.findById(2L)).thenReturn(Optional.of(author2));

        // Act
        Map<Book, Author> result1 = authorService.getAuthors(books);
        int callCount = 3;
        // Assert
        assertEquals(2, result1.size());
        assertEquals(author1, result1.get(book1));
        assertEquals(author2, result1.get(book2));
        verify(authorRepository, times(callCount)).findById(any());

        Map<Book, Author> result2 = authorService.getAuthors(books);
        assertEquals(2, result2.size());
        assertEquals(author1, result2.get(book1));
        assertEquals(author2, result2.get(book2));
        verify(authorRepository, times(callCount)).findById(any());
    }
}
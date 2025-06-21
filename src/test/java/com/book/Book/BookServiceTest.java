package com.book.Book;

import com.book.Book.model.Book;
import com.book.Book.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService service;

    @Test
    void findAll_shouldReturnEmptyList() {
        List<Book> books = service.findAll();
        assertTrue(books.isEmpty());
    }

    @Test
    void addBook_shouldAddBook() {
        Book book = new Book();
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");

        service.add(book);

        List<Book> books = service.findAll();
        assertEquals(1, books.size());
        assertEquals("Clean Code", books.getFirst().getTitle());
        assertEquals("Robert C. Martin", books.getFirst().getAuthor());
    }
}

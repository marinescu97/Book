package com.book.Book.controller;

import com.book.Book.model.Book;
import com.book.Book.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model){
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("newBook", new Book());
        return "books";
    }

    @PostMapping
    public String addBook(Book book){
        bookService.add(book);
        return "redirect:/books";
    }
}

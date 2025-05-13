package com.book.Book.service;

import com.book.Book.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private List<Book> books = new ArrayList<>();

    public void add(Book book){
        books.add(book);
    }

    public List<Book> findAll(){
        return books;
    }
}

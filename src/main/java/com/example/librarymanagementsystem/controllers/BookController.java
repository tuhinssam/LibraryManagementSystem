package com.example.librarymanagementsystem.controllers;

import com.example.librarymanagementsystem.dtos.CreateBookRequest;
import com.example.librarymanagementsystem.dtos.SearchBookRequest;
import com.example.librarymanagementsystem.models.Book;
import com.example.librarymanagementsystem.services.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;
    @PostMapping("/book")
    public void createBook(@RequestBody @Valid CreateBookRequest bookCreateRequest) {
        bookService.createOrUpdate(bookCreateRequest.to());
    }

    @GetMapping("/book")
    public List<Book> getBooks(@RequestBody @Valid SearchBookRequest searchBookRequest) throws Exception {
        return bookService.find(searchBookRequest.getSearchKey(), searchBookRequest.getSearchValue());
    }
}

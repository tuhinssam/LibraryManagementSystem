package com.example.librarymanagementsystem.controllers;

import com.example.librarymanagementsystem.dtos.CreateBookRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @PostMapping("/book")
    public void createBook(@RequestBody CreateBookRequest bookCreateRequest) {

    }
}

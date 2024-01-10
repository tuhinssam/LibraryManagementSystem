package com.example.librarymanagementsystem.controllers;

import com.example.librarymanagementsystem.dtos.CreateStudentRequest;
import com.example.librarymanagementsystem.dtos.CreateUserRequest;
import com.example.librarymanagementsystem.models.LibraryUser;
import com.example.librarymanagementsystem.services.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryUserController {
    @Autowired
    LibraryUserService libraryUserService;

    @PostMapping("/user/register")
    public void createStudent(@RequestBody CreateUserRequest createUserRequest) {
        libraryUserService.create(createUserRequest.to());
    }
}

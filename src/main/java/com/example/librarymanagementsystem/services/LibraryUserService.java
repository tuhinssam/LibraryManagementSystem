package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.models.LibraryUser;
import com.example.librarymanagementsystem.models.Student;
import com.example.librarymanagementsystem.repositories.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryUserService {
    @Autowired
    LibraryUserRepository libraryUserRepository;

    public void create(LibraryUser user) {
        libraryUserRepository.save(user);
    }
}

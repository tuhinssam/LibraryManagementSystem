package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.models.Author;
import com.example.librarymanagementsystem.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author getOrCreate(Author author) {
        Author retrievedAuthor = authorRepository.findAuthorByEmail(author.getEmail());
        if(retrievedAuthor == null) {
            retrievedAuthor = authorRepository.save(author);
        }
        return retrievedAuthor;
    }
}

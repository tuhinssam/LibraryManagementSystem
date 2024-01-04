package com.example.librarymanagementsystem.repositories;

import com.example.librarymanagementsystem.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findAuthorByEmail(String email);

    Author save(Author author);
}

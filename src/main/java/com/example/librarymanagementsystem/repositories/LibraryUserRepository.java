package com.example.librarymanagementsystem.repositories;

import com.example.librarymanagementsystem.models.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, Integer> {
}

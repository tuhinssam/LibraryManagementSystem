package com.example.librarymanagementsystem.repositories;

import com.example.librarymanagementsystem.models.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityRepository extends JpaRepository<LibraryUser, Integer> {
    UserDetails findByUsername(String username);
}

package com.example.librarymanagementsystem.dtos;

import com.example.librarymanagementsystem.models.LibraryUser;
import com.example.librarymanagementsystem.models.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    @Autowired
    PasswordEncoder passwordEncoder;
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String authorities;

    public LibraryUser to() {
        return LibraryUser.builder()
                .username(this.username)
                .password(passwordEncoder.encode(this.password))
                .authorities(this.authorities)
                .build();
    }
}

package com.example.librarymanagementsystem.dtos;

import com.example.librarymanagementsystem.models.Author;
import com.example.librarymanagementsystem.models.Book;
import com.example.librarymanagementsystem.models.Genre;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookRequest {
    @NotBlank
    private String name;

    @NotNull
    private Genre genre;

    @NotBlank
    private String authorName;

    @NotBlank
    private String authorEmail;

    public Book to() {
        Author author = Author.builder()
                .name(authorName)
                .email(authorEmail)
                .build();
        return Book.builder()
                .name(name)
                .genre(genre)
                .author(author)
                .build();
    }
}

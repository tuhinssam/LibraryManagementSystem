package com.example.librarymanagementsystem.dtos;

import com.example.librarymanagementsystem.models.Admin;
import com.example.librarymanagementsystem.models.LibraryUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminCreateRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Admin to() {
        return Admin.builder()
                .email(this.email)
                .name(this.name)
                .securedUser(
                        LibraryUser.builder()
                                .username(this.username)
                                .password(this.password)
                                .build()
                )
                .build();
    }
}

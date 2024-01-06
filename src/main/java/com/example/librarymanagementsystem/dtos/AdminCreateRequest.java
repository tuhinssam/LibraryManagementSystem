package com.example.librarymanagementsystem.dtos;

import com.example.librarymanagementsystem.models.Admin;
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

    public Admin to() {
        return Admin.builder()
                .email(this.email)
                .name(this.name)
                .build();
    }
}

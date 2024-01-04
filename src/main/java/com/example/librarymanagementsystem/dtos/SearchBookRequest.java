package com.example.librarymanagementsystem.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchBookRequest {
    @NotBlank
    private String searchKey;

    @NotBlank
    private String searchValue;
}

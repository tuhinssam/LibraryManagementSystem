package com.example.librarymanagementsystem.dtos;

import com.example.librarymanagementsystem.models.LibraryUser;
import com.example.librarymanagementsystem.models.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    private Integer age;
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Student to() {
        return Student.builder()
                .name(this.name)
                .email(this.email)
                .age(this.age)
                .securedUser(
                        LibraryUser.builder()
                                .username(this.username)
                                .password(this.password)
                                .build()
                )
                .build();
    }
}

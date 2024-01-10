package com.example.librarymanagementsystem.controllers;

import com.example.librarymanagementsystem.dtos.CreateStudentRequest;
import com.example.librarymanagementsystem.dtos.InitiateTransactionRequest;
import com.example.librarymanagementsystem.models.Student;
import com.example.librarymanagementsystem.services.StudentService;
import com.example.librarymanagementsystem.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController

public class StudentController {
    @Autowired
    StudentService studentService;


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/student")
    public void createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
         studentService.create(createStudentRequest.to());
    }

    @PreAuthorize("hasAuthority('ROLE_STUDENT')")
    @GetMapping("/student")
    public Student findStudents(@RequestParam("id") int studentId) {
        return studentService.find(studentId);
    }

}

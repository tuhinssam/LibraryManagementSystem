package com.example.librarymanagementsystem.controllers;

import com.example.librarymanagementsystem.dtos.CreateStudentRequest;
import com.example.librarymanagementsystem.models.Student;
import com.example.librarymanagementsystem.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
         studentService.create(createStudentRequest.to());
    }

    @GetMapping("/student")
    public Student findStudents(@RequestParam("id") int studentId) {
        return studentService.find(studentId);
    }
}

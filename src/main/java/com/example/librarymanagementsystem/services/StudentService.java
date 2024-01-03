package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.models.Student;
import com.example.librarymanagementsystem.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public void create(Student student) {
        studentRepository.save(student);
    }
    public Student find(int studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }
}

package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.models.LibraryUser;
import com.example.librarymanagementsystem.models.Student;
import com.example.librarymanagementsystem.repositories.StudentRepository;
import com.example.librarymanagementsystem.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    LibraryUserService libraryUserService;

    public void create(Student student) {
        LibraryUser securedUser = student.getSecuredUser();
        securedUser = libraryUserService.save(securedUser, Constants.STUDENT_USER);
        student.setSecuredUser(securedUser);
        studentRepository.save(student);
    }

    public Student find(int studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }
}

package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.models.LibraryUser;
import com.example.librarymanagementsystem.models.Student;
import com.example.librarymanagementsystem.repositories.StudentCacheRepository;
import com.example.librarymanagementsystem.repositories.StudentRepository;
import com.example.librarymanagementsystem.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentCacheRepository studentCacheRepository;

    @Autowired
    LibraryUserService libraryUserService;

    public void create(Student student) {
        LibraryUser securedUser = student.getSecuredUser();
        securedUser = libraryUserService.save(securedUser, Constants.STUDENT_USER);
        student.setSecuredUser(securedUser);
        studentRepository.save(student);
    }

    public Student find(int studentId) {
        Student student = studentCacheRepository.get(studentId);
        if(student != null) {
            log.info(String.format("Student info: %s, %s, %s",student.getName(), student.getAge(), student.getEmail()));
            return student;
        }

        student = studentRepository.findById(studentId).orElse(null);
        if(student != null) {
            log.info(String.format("Student info: %s, %s, %s",student.getName(), student.getAge(), student.getEmail()));
            studentCacheRepository.set(student);
        }
        return student;
    }
}

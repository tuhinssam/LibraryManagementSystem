package com.example.librarymanagementsystem.controllers;

import com.example.librarymanagementsystem.dtos.CreateStudentRequest;
import com.example.librarymanagementsystem.models.LibraryUser;
import com.example.librarymanagementsystem.models.Student;
import com.example.librarymanagementsystem.services.StudentService;
import com.example.librarymanagementsystem.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController

public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        studentService.create(createStudentRequest.to());
    }

    @GetMapping("/student-by-id/{id}")
    public Student findStudentById(@PathVariable("id") int studentId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LibraryUser securedUser = (LibraryUser) authentication.getPrincipal();

        for(GrantedAuthority grantedAuthority: securedUser.getAuthorities()){
            String[] authorities = grantedAuthority.getAuthority().split(Constants.DELIMITER);
            boolean isCalledByAdmin = Arrays.asList(authorities).contains(Constants.STUDENT_INFO_AUTHORITY);
            if (isCalledByAdmin) {
                return studentService.find(studentId);
            }
        }
        throw new Exception("User is not authorized to do this");
    }

    @GetMapping("/student")
    public Student findStudents() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LibraryUser libraryUser = (LibraryUser) authentication.getPrincipal();
        int studentId = libraryUser.getStudent().getId();

        return studentService.find(studentId);
    }

}

package com.example.librarymanagementsystem.controllers;

import com.example.librarymanagementsystem.dtos.AdminCreateRequest;
import com.example.librarymanagementsystem.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/admin")
    public void createAdmin(@RequestBody @Valid AdminCreateRequest adminCreateRequest) {
        adminService.create(adminCreateRequest.to());
    }
}

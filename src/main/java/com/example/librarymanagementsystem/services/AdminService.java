package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.models.Admin;
import com.example.librarymanagementsystem.models.LibraryUser;
import com.example.librarymanagementsystem.repositories.AdminRepository;
import com.example.librarymanagementsystem.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    LibraryUserService libraryUserService;
    public void create(Admin admin) {
        LibraryUser securedUser = admin.getSecuredUser();
        securedUser = libraryUserService.save(securedUser, Constants.ADMIN_USER);
        admin.setSecuredUser(securedUser);
        adminRepository.save(admin);
    }

    public Admin find(Integer adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }
}

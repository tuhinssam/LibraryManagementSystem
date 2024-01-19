package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.models.LibraryUser;
import com.example.librarymanagementsystem.repositories.LibraryUserRepository;
import com.example.librarymanagementsystem.utils.ResourceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LibraryUserService implements UserDetailsService {
    @Autowired
    LibraryUserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public LibraryUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByusername(username);
    }

    public LibraryUser save(LibraryUser securedUser, String userType){

        String encryptedPwd = encoder.encode(securedUser.getPassword());
        String authorities = ResourceUtils.getAuthoritiesForUser().get(userType);

        securedUser.setAuthorities(authorities);
        securedUser.setPassword(encryptedPwd);
        return this.userRepository.save(securedUser);
    }
}

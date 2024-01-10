package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.models.LibraryUser;
import com.example.librarymanagementsystem.repositories.BookRepository;
import com.example.librarymanagementsystem.repositories.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class LibraryManagementSystemApplication implements CommandLineRunner {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	LibraryUserRepository libraryUserRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired


	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Book book = Book.builder()
//				.name("Intro to Java")
//				.genre(Genre.TECHNOLOGY)
//				.build();
//		bookRepository.save(book);
//
//		LibraryUser demoUser = LibraryUser.builder()
//				.username("student")
//				.password(passwordEncoder.encode("student"))
//				.authorities("ROLE_STUDENT")
//				.build();
//		libraryUserRepository.save(demoUser);
	}
}

package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.models.Book;
import com.example.librarymanagementsystem.models.Genre;
import com.example.librarymanagementsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementSystemApplication implements CommandLineRunner {

	@Autowired
	BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Book book = Book.builder()
				.name("Intro to Java")
				.genre(Genre.TECHNOLOGY)
				.build();
		bookRepository.save(book);
	}
}

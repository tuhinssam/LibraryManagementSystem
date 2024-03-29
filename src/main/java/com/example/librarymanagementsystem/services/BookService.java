package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.models.Author;
import com.example.librarymanagementsystem.models.Book;
import com.example.librarymanagementsystem.models.Genre;
import com.example.librarymanagementsystem.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookService {
    @Autowired
    AuthorService authorService;

    @Autowired
    BookRepository bookRepository;
    public List<Book> find(String searchKey, String searchValue) throws Exception {
        switch (searchKey) {
            case "id": {
                Optional<Book> bookList = bookRepository.findById(Integer.parseInt(searchValue));
                return bookList.isPresent() ? List.of(bookList.get()) : new ArrayList<>();
            }
            case "name":
                return bookRepository.findByName(searchValue);
            case "genre":
                return bookRepository.findByGenre(Genre.valueOf(searchValue));
            case "author":
                return bookRepository.findByAuthor(searchValue);
            default:
                throw new Exception("search key not valid: "+searchKey);
        }
    }

    public void createOrUpdate(Book book) {
        Author bookAuthor = book.getAuthor();
        Author savedAuthor = authorService.getOrCreate(bookAuthor);
        book.setAuthor(savedAuthor);
        bookRepository.save(book);
    }
}

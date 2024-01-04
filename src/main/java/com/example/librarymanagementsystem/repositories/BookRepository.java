package com.example.librarymanagementsystem.repositories;

import com.example.librarymanagementsystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByName(String name);

    List<Book> findByGenre(String genre);

    @Query("select b from Book b, Author a where b.author.id = a.id and a.name = ?1")
    List<Book> findByAuthor(String author);
}

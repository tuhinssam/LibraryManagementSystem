package com.example.librarymanagementsystem.repositories;

import com.example.librarymanagementsystem.models.Book;
import com.example.librarymanagementsystem.models.Student;
import com.example.librarymanagementsystem.models.Transaction;
import com.example.librarymanagementsystem.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    //find last transaction with transactiontype = ISSUE
    //@Query("select * from transaction where student = ?1 and book = ?2 and transactionType = ?3 order by id desc limit 1")
    Transaction findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(Student student, Book book, TransactionType transactionType);

}

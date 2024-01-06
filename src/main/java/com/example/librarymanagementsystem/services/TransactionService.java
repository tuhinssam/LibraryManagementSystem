package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.dtos.InitiateTransactionRequest;
import com.example.librarymanagementsystem.models.*;
import com.example.librarymanagementsystem.repositories.AdminRepository;
import com.example.librarymanagementsystem.repositories.BookRepository;
import com.example.librarymanagementsystem.repositories.StudentRepository;
import com.example.librarymanagementsystem.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    TransactionRepository transactionRepository;
    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    @Autowired
    AdminService adminService;

    @Value("${student.allowed.max-books}")
    Integer maxBookAllowed;

    @Value("${student.allowed.duration}")
    Integer duration;
    public String initiateTransaction(InitiateTransactionRequest transactionRequest) throws Exception {
        /**
         * Issuance
         * 1. If the book is available or not and student is valid or not
         * 2. entry in the Txn
         * 3. we need to check if student has reached the maximum limit of issuance
         * 4. book to be assigned to a student ==> update in book table
         *
         */

        /**
         * Return
         * 1. If the book is valid or not and student is valid or not
         * 2. entry in the Txn table
         * 3. due date check and fine calculation
         * 4. if there is no fine, then de-allocate the book from student's name ==> book table
         */
        return transactionRequest.getTransactionType() == TransactionType.ISSUE ?
                issueBook(transactionRequest) :
                returnBook(transactionRequest);
    }
    private String issueBook(InitiateTransactionRequest transactionRequest) throws Exception {
        List<Book> bookList = bookService.find("id", String.valueOf(transactionRequest.getBookId()));
        Student student = studentService.find(transactionRequest.getStudentId());
        Admin admin = adminService.find(transactionRequest.getAdminId());

        Book book = (bookList != null && bookList.size() != 0) ? bookList.get(0) : null;
        if(student == null
                || book == null
                || admin == null
                || book.getStudent() != null
                || student.getBookList().size() >= maxBookAllowed) {
            throw new Exception("Invalid Request");
        }

        Transaction transaction = null;
        try {
            transaction = Transaction.builder()
                    .transactionId(UUID.randomUUID().toString())
                    .student(student)
                    .book(book)
                    .admin(admin)
                    .transactionType(transactionRequest.getTransactionType())
                    .transactionStatus(TransactionStatus.PENDING)
                    .build();
            book.setStudent(student);
            bookService.createOrUpdate(book);
            transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        }
        catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
        }
        finally {
            transactionRepository.save(transaction);
        }
        return transaction.getTransactionId();
    }

    private  String returnBook(InitiateTransactionRequest transactionRequest) {
        //TODO: Implement Return Book
        return null;
    }
}

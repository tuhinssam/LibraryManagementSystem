package com.example.librarymanagementsystem.services;

import com.example.librarymanagementsystem.dtos.InitiateTransactionRequest;
import com.example.librarymanagementsystem.models.*;
import com.example.librarymanagementsystem.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
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
        if(book == null ||
                admin == null ||
                book.getStudent() != null ||
                student.getBookList().size() >= maxBookAllowed)
        {
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
            assert transaction != null;
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
        }
        finally {
            transactionRepository.save(transaction);
        }
        return transaction.getTransactionId();
    }

    private  String returnBook(InitiateTransactionRequest transactionRequest) throws Exception {
        List<Book> bookList = bookService.find("id", String.valueOf(transactionRequest.getBookId()));
        Student student = studentService.find(transactionRequest.getStudentId());
        Admin admin = adminService.find(transactionRequest.getAdminId());

        Book book = (bookList != null && bookList.size() != 0) ? bookList.get(0) : null;

        if(student == null
                || book == null
                || admin == null
                || book.getStudent() == null
                || !Objects.equals(book.getStudent().getId(), student.getId())) {
            throw new Exception("Invalid Request");
        }

        // Getting the corresponding issuance txn
        Transaction issuanceTransaction = transactionRepository.findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(
                student, book, TransactionType.ISSUE);
        if(issuanceTransaction == null){
            throw new Exception("Invalid request");
        }

        Transaction transaction = null;
        try {
            Integer fine  = calculateFine(issuanceTransaction.getCreatedOn());
            transaction = Transaction.builder()
                    .transactionId(UUID.randomUUID().toString())
                    .student(student)
                    .book(book)
                    .admin(admin)
                    .fine(fine)
                    .transactionType(transactionRequest.getTransactionType())
                    .transactionStatus(TransactionStatus.PENDING)
                    .build();

            if(fine == 0) {
                book.setStudent(null);
                bookService.createOrUpdate(book);
                transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            }
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
        } finally {
            transactionRepository.save(transaction);
        }
        return transaction.getTransactionId();
    }

    private int calculateFine(Date transactionIssueTime) {
        long issueTimeInMillis = transactionIssueTime.getTime();
        long currentTimeInMillis = System.currentTimeMillis();
        long diff = currentTimeInMillis - issueTimeInMillis;
        long daysSinceIssue = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return daysSinceIssue > duration ? (int)(daysSinceIssue - duration) : 0;
    }

    public void payFine(String transactionId, Integer studentId, Integer amount) throws Exception {
        Transaction returnTxn = transactionRepository.findByTransactionId(transactionId);

        Book book = returnTxn.getBook();

        if(returnTxn.getFine() == amount && book.getStudent() != null && book.getStudent().getId() == studentId){
            returnTxn.setTransactionStatus(TransactionStatus.SUCCESS);
            book.setStudent(null);
            bookService.createOrUpdate(book);
            transactionRepository.save(returnTxn);
        }else{
            throw new Exception("invalid request");
        }
    }
}

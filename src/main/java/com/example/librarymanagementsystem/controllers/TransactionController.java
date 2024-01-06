package com.example.librarymanagementsystem.controllers;

import com.example.librarymanagementsystem.dtos.InitiateTransactionRequest;
import com.example.librarymanagementsystem.services.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @PostMapping("/transaction")
    public String initiateTransaction(@RequestBody @Valid InitiateTransactionRequest transactionRequest) throws Exception {
        return transactionService.initiateTransaction(transactionRequest);
    }
}

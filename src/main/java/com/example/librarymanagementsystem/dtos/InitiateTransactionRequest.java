package com.example.librarymanagementsystem.dtos;

import com.example.librarymanagementsystem.models.Transaction;
import com.example.librarymanagementsystem.models.TransactionType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InitiateTransactionRequest {
    @NotNull
    private Integer studentId;

    @NotNull
    private Integer bookId;

    @NotNull
    private Integer adminId;

    @NotNull
    private TransactionType transactionType;
}

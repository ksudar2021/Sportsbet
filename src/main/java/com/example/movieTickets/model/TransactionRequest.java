package com.example.movieTickets.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

/**
 * DTO for incoming transaction request.
 * Ensures transactionId and customer list are validated.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    @NotNull(message = "Transaction ID cannot be null")
    private int transactionId;
    @NotEmpty(message = "Customer list cannot be empty")
    private List<@Valid Customer> customers;

}

package com.example.movieTickets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO for the response after processing a transaction.
 * Includes total calculated cost and detailed ticket information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private int transactionId;
    private List<Ticket> tickets;
    private double totalCost;

}

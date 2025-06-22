package com.example.movieTickets.model;

import lombok.*;

/**
 * Ticket summary per ticket type (e.g., ADULT, CHILD).
 * Contains quantity and total cost information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private String ticketType;
    private int quantity;
    private double totalCost;

}

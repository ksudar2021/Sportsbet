package com.example.movieTickets.util;

/**
 * Enum representing ticket types and their prices.
 */
public enum TicketType {
    ADULT(25.0),
    SENIOR(17.50),
    TEEN(12.0),
    CHILD(5.0);

    private final double price;

    TicketType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

}

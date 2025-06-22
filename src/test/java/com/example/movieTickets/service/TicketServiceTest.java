package com.example.movieTickets.service;

import com.example.movieTickets.model.Customer;
import com.example.movieTickets.model.TransactionRequest;
import com.example.movieTickets.model.TransactionResponse;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TicketServiceTest {

    private final TicketService service = new TicketService();

    @Test
    void testCalculateTickets_AllTicketTypes_NoDiscount() {
        TransactionRequest request = new TransactionRequest(1,
                List.of(
                        new Customer("Child", 10),
                        new Customer("Teen", 16),
                        new Customer("Adult", 30),
                        new Customer("Senior", 70)
                ));
        TransactionResponse response = service.calculateTickets(request);

        assertEquals(1, response.getTransactionId());
        assertEquals(4, response.getTickets().size());
        assertEquals(59.5, response.getTotalCost());
    }

    @Test
    void testCalculateTickets_EmptyCustomerList() {
        TransactionRequest request = new TransactionRequest(3, List.of());

        TransactionResponse response = service.calculateTickets(request);

        assertEquals(3, response.getTransactionId());
        assertEquals(0, response.getTickets().size());
        assertEquals(0.0, response.getTotalCost());

    }

    @Test
    void testCalculateTickets_childDiscountApplied() {
        TransactionRequest request = new TransactionRequest(2,
                List.of(
                        new Customer("Kid1", 5),
                        new Customer("Kid2", 6),
                        new Customer("kid3", 8)
                ));

        TransactionResponse response = service.calculateTickets(request);

        assertEquals(2, response.getTransactionId());
        assertEquals(1, response.getTickets().size());
        assertEquals("CHILD", response.getTickets().get(0).getTicketType());
        assertEquals(3, response.getTickets().get(0).getQuantity());
        assertEquals(11.25, response.getTotalCost());

    }

    @Test
    void testCalculateTickets_OnlyAdults() {

        TransactionRequest request = new TransactionRequest(4,
                List.of(
                        new Customer("Adult1", 25),
                        new Customer("Adult2", 35)
                ));

        TransactionResponse response = service.calculateTickets(request);

        assertEquals(4, response.getTransactionId());
        assertEquals(1, response.getTickets().size());
        assertEquals("ADULT", response.getTickets().get(0).getTicketType());
        assertEquals(2, response.getTickets().get(0).getQuantity());
        assertEquals(50.0, response.getTickets().get(0).getTotalCost());

    }

}

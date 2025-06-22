package com.example.movieTickets.controller;

import com.example.movieTickets.model.TransactionRequest;
import com.example.movieTickets.model.TransactionResponse;
import com.example.movieTickets.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller exposing endpoints to calculate
 * total cost for given transaction
 * @author sudar
 */
@RestController
@RequestMapping("/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * POST endpoint to calculate movie ticket pricing for given transaction
     *
     * @param request Transaction request containing transactionId and customerlist
     * @return returns calculated ticket details along with total cost
     */
    @PostMapping("/calculate")
    public TransactionResponse calculateTickets(@Valid @RequestBody TransactionRequest request) {
        return ticketService.calculateTickets(request);
    }

}

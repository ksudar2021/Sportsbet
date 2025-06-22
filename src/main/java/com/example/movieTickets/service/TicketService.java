package com.example.movieTickets.service;

import com.example.movieTickets.model.Customer;
import com.example.movieTickets.model.Ticket;
import com.example.movieTickets.model.TransactionRequest;
import com.example.movieTickets.model.TransactionResponse;
import com.example.movieTickets.util.TicketType;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class responsible for calculating ticket prices and quantities
 * for a given transaction request containing customers
 * @author sudar
 */
@Service
public class TicketService {

    /**
     * Calculates tickets and total cost  of given transaction request
     * groups customers by ticket type based on age and applies discount
     *
     * @param request Transaction request containing transactionId and customer list
     * @return TransactionResponse containing transactioId, list of tickets and total cost
     */
    public TransactionResponse calculateTickets(TransactionRequest request) {

        Map<TicketType, List<Customer>> ticketMap = new HashMap<>();

        for (Customer customer : request.getCustomers()) {
            TicketType type = getTicketType(customer.getAge());
            ticketMap.computeIfAbsent(type, k -> new ArrayList<>()).add(customer);
        }

        List<Ticket> tickets = new ArrayList<>();

        for (Map.Entry<TicketType, List<Customer>> entry : ticketMap.entrySet()) {
            int quantity = entry.getValue().size();
            double pricePerTicket = entry.getKey().getPrice();
            double totalCost = quantity * pricePerTicket;

            /** Applying discount rule for 3 or more CHILD tickets **/
            if (entry.getKey() == TicketType.CHILD && quantity >= 3) {
                totalCost *= 0.75;
            }

            tickets.add(new Ticket(entry.getKey().name(), quantity, totalCost));
        }

        tickets.sort(Comparator.comparing(Ticket::getTicketType));
        double total = tickets.stream().mapToDouble(Ticket::getTotalCost).sum();

        return new TransactionResponse(request.getTransactionId(), tickets, total);
    }

    /**
     * Helper method to determine ticketType based on age
     *
     * @param age Customer's age
     * @return Corresponding TicketType enumvalue
     */
    private TicketType getTicketType(int age) {
        if (age < 11) return TicketType.CHILD;
        if (age < 18) return TicketType.TEEN;
        if (age < 65) return TicketType.ADULT;
        return TicketType.SENIOR;
    }

}

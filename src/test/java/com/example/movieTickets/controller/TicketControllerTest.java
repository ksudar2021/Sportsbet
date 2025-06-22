package com.example.movieTickets.controller;

import com.example.movieTickets.model.Customer;
import com.example.movieTickets.model.Ticket;
import com.example.movieTickets.model.TransactionRequest;
import com.example.movieTickets.model.TransactionResponse;
import com.example.movieTickets.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TicketService service;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCalculateTicketsEndpoint() throws Exception {
        TransactionRequest request = new TransactionRequest(1,
                List.of(
                        new Customer("Senior", 70)
                ));

        TransactionResponse response = new TransactionResponse(1,
                List.of(new Ticket("SENIOR", 1, 17.5)), 17.5);

        when(service.calculateTickets(request)).thenReturn(response);

        mockMvc.perform(post("/v1/tickets/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value(1))
                .andExpect(jsonPath("$.tickets[0].ticketType").value("SENIOR"))
                .andExpect(jsonPath("$.tickets[0].quantity").value(1))
                .andExpect(jsonPath("$.tickets[0].totalCost").value(17.5))
                .andExpect(jsonPath("$.totalCost").value(17.5));
    }
}

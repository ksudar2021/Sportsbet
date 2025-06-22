package com.example.movieTickets.exceptionHandler;

import com.example.movieTickets.controller.TicketController;
import com.example.movieTickets.model.Customer;
import com.example.movieTickets.model.TransactionRequest;
import com.example.movieTickets.service.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;


@WebMvcTest(TicketController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TicketService service;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testHandleValidationException() throws Exception {
        String invalidRequest = "{}";

        mockMvc.perform(post("/v1/tickets/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message",containsString("customers")));

    }

    @Test
    void testHandleGeneralException() throws Exception {
        Mockito.when(service.calculateTickets(any()))
                .thenThrow(new RuntimeException("An unexpected error occurred"));

        TransactionRequest request = new TransactionRequest();
        request.setTransactionId(1);

        Customer customer = new Customer();
        customer.setName("John");
        customer.setAge(30);
        request.setCustomers(List.of(customer));

        mockMvc.perform(post("/v1/tickets/calculate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.message").value("An unexpected error occurred"));

    }

}

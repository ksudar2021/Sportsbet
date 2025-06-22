package com.example.movieTickets.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Customer model representing a movie ticket buyer.
 * Contains validation annotations to ensure data integrity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @NotBlank(message = "Customer name must not be blank")
    private String name;
    @Min(value = 0, message = "Age must be positive")
    private int age;

}

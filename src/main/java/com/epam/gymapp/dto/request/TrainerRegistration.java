package com.epam.gymapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerRegistration {

    @NotBlank(message = "Field First Name should not be empty")
    private String firstName;
    @NotBlank(message = "Field Last Name should not be empty")
    private String lastName;

    @NotBlank(message = "Please provide email")
    private String email;

    @NotBlank(message = "Field Specialization should not be empty")
    private String specialization;
}

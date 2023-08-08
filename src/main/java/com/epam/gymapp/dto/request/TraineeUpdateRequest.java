package com.epam.gymapp.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeUpdateRequest {

    @NotBlank(message = "Field userName should not be empty")
    private String userName;

    @NotBlank(message = "Field First Name should not be empty")
    private String firstName;

    @NotBlank(message = "Field Last Name should not be empty")
    private String lastName;

    @Past(message = "The date must be in the past")
    private LocalDate dob;

    @NotNull(message = "Field Address should not be empty")
    private String address;


    private String email;

    @NotNull(message = "Field isActive should not be empty")
    private boolean isActive;
}

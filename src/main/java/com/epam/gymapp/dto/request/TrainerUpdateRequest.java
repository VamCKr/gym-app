package com.epam.gymapp.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerUpdateRequest {

    @NotBlank(message = "Field userName should not be empty")
    private String userName;
    @NotBlank(message = "Field First Name should not be empty")
    private String firstName;
    @NotBlank(message = "Field Last Name should not be empty")
    private String lastName;

    private String email;
    private boolean isActive;
}

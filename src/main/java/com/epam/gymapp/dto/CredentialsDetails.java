package com.epam.gymapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CredentialsDetails {

    @NotBlank(message = "Field userName should not be empty")
    private String userName;

    @NotBlank(message = "Field password should not be empty")
    private String password;
}

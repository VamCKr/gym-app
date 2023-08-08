package com.epam.gymapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyCredentialsRequest {

    @NotBlank(message = "Field userName should not be empty")
    private String userName;

    @NotBlank(message = "Field Old Password should not be empty")
    private String oldPassword;

    @NotBlank(message = "Field New Password should not be empty")
    private String newPassword;
}

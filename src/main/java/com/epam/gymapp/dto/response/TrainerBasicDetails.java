package com.epam.gymapp.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerBasicDetails {

    private String userName;
    private String firstName;
    private String lastName;
    private String specialization;
    private boolean isActive;
}

package com.epam.gymapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeTrainingsRequest {

    @NotBlank(message = "Field userName should not be empty")
    private String userName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String trainerName;
    private int trainingTypeId;
}

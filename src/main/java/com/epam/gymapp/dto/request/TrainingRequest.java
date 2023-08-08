package com.epam.gymapp.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRequest {

    @NotBlank(message = "Field Trainee Username should not be empty")
    private String traineeUserName;
    @NotBlank(message = "Field Trainer userName should not be empty")
    private String trainerUserName;
    @NotBlank(message = "Field Training Name should not be empty")
    private String trainingName;

    private LocalDate trainingDate;
    @NotNull(message = "Field Training Type should not be empty")
    private int trainingTypeId;
    @NotNull(message = "Field Duration should not be empty")
    private int trainingDuration;

}

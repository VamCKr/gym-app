package com.epam.gymapp.dto.report;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainingMailDTO {

    private String trainerName;

    private String traineeName;

    private String traineeEmail;

    private String trainerEmail;

    private String trainingName;

}

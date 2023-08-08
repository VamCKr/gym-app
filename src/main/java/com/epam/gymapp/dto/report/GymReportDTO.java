package com.epam.gymapp.dto.report;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class GymReportDTO {
    private String trainerUserName;

    private String trainerFirstName;

    private String trainerLastName;

    private LocalDate date;

    private boolean isActive;

    private int duration;

}

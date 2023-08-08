package com.epam.gymapp.dto;

import com.epam.gymapp.dto.report.GymReportDTO;
import com.epam.gymapp.dto.report.TrainingMailDTO;
import lombok.Data;

@Data
public class TrainingOverallDTO {

    GymReportDTO gymReportDTO;

    TrainingMailDTO trainingMailDTO;
}

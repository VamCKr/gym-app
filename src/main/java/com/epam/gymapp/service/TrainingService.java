package com.epam.gymapp.service;

import com.epam.gymapp.dto.TrainingOverallDTO;
import com.epam.gymapp.dto.request.TrainingRequest;
import com.epam.gymapp.model.Trainee;
import com.epam.gymapp.model.Trainer;
import com.epam.gymapp.model.Training;

import java.time.LocalDate;
import java.util.List;

public interface TrainingService {
    String ADD_TRAINING_TYPE = "addTrainingType";

    TrainingOverallDTO addTraining(TrainingRequest trainingDetails);

    List<Training> getTrainingsForTrainee(Trainee trainee, LocalDate startDate, LocalDate endDate, int trainingTypeId);

    List<Training> getTrainingsForTrainer(Trainer trainer, LocalDate startDate, LocalDate endDate, String userName);
}

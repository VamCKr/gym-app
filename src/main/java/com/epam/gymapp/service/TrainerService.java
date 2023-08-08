package com.epam.gymapp.service;

import com.epam.gymapp.dto.CredentialsDetails;
import com.epam.gymapp.dto.request.TrainerRegistration;
import com.epam.gymapp.dto.request.TrainerTrainings;
import com.epam.gymapp.dto.request.TrainerUpdateRequest;
import com.epam.gymapp.dto.response.TrainerBasicDetails;
import com.epam.gymapp.dto.response.TrainerDetails;
import com.epam.gymapp.dto.response.TrainingResponse;

import java.util.List;

public interface TrainerService {
    String CREATE_TRAINER = "createTrainer";
    String GET_TRAINER = "getTrainer";
    String UPDATE_TRAINER = "updateTrainer";

    CredentialsDetails createTrainer(TrainerRegistration trainerDetails);

    TrainerDetails getTrainer(String username);

    TrainerDetails updateTrainer(TrainerUpdateRequest trainerUpdateRequest);

    List<TrainerBasicDetails> getTraineeNotAssignedActiveTrainers(String traineeName);

    List<TrainingResponse> getTrainerTrainings(TrainerTrainings trainerTrainings);
}

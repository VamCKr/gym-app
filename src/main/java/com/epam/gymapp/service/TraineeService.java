package com.epam.gymapp.service;

import com.epam.gymapp.dto.CredentialsDetails;
import com.epam.gymapp.dto.request.TraineeRegistration;
import com.epam.gymapp.dto.request.TraineeTrainingsRequest;
import com.epam.gymapp.dto.request.TraineeUpdateRequest;
import com.epam.gymapp.dto.request.TraineesTrainerUpdateRequest;
import com.epam.gymapp.dto.response.TraineeDetails;
import com.epam.gymapp.dto.response.TrainingResponse;

import java.util.List;

public interface TraineeService {

    String NONE_OF_THE_USERS_ARE_TRAINERS = "None of the users are trainers";
    String NO_ACCOUNT_WITH_THAT_USERNAME = "No Account with that username";
    String THIS_USER_IS_NOT_A_TRAINEE = "This user is not a trainee";
    String THERE_IS_NO_USER_WITH_THAT_USERNAME = "There is no user with that username";
    String UPDATE_TRAINEES_TRAINER_LIST = "updateTraineesTrainerList";
    String GET_TRAINEE = "getTrainee";
    String UPDATE_TRAINEE = "updateTrainee";

    CredentialsDetails createTrainee(TraineeRegistration traineeDetails);

    TraineeDetails getTrainee(String username);

    TraineeDetails updateTrainee(TraineeUpdateRequest traineeUpdateRequest);

    TraineeDetails updateTraineesTrainerList(TraineesTrainerUpdateRequest traineeUpdateRequest);

    void removeTrainee(String username);

    List<TrainingResponse> getTraineeTrainings(TraineeTrainingsRequest traineeTrainings);
}

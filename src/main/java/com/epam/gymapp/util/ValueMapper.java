package com.epam.gymapp.util;

import com.epam.gymapp.dto.report.GymReportDTO;
import com.epam.gymapp.dto.response.*;
import com.epam.gymapp.model.*;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ValueMapper {

    private ValueMapper(){}

    public static final String CONVERT_TRAINING_TO_TRAINING_RESPONSE = "convertTrainingToTrainingResponse";

    public static List<TrainingResponse> convertTrainingToTrainingResponse(List<Training> trainingList) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), CONVERT_TRAINING_TO_TRAINING_RESPONSE,ValueMapper.class,trainingList);
        List<TrainingResponse> trainingResponseList = new ArrayList<>();
        trainingList.forEach(trainingDetails -> trainingResponseList.add(new TrainingResponse(
                trainingDetails.getName(),
                trainingDetails.getDate(),
                trainingDetails.getTrainingType().getTrainingTypeName(),
                trainingDetails.getDuration(),
                trainingDetails.getTrainer().getUser().getUserName(),
                trainingDetails.getTrainee().getUser().getUserName()
        )));
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), CONVERT_TRAINING_TO_TRAINING_RESPONSE);
        return trainingResponseList;
    }

    public static List<TraineeBasicDetails> convertTraineeToTraineeBasicDetails(List<Trainee> traineeList){
        log.info(StringConstants.START_SERVICE_METHOD.getValue(),"convertTraineeToTraineeBasicDetails",ValueMapper.class,traineeList);
        List<TraineeBasicDetails> traineeDetailsList = new ArrayList<>();
        for(Trainee trainee : traineeList){
            TraineeBasicDetails traineeBasicDetails = new TraineeBasicDetails();
            traineeBasicDetails.setUserName(trainee.getUser().getUserName());
            traineeBasicDetails.setFirstName(trainee.getUser().getFirstName());
            traineeBasicDetails.setLastName(trainee.getUser().getLastName());
            traineeBasicDetails.setActive(trainee.getUser().getIsActive());
            traineeDetailsList.add(traineeBasicDetails);
        }
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), CONVERT_TRAINING_TO_TRAINING_RESPONSE);
        return traineeDetailsList;
    }

    public static List<TrainerBasicDetails> convertTrainerToTrainerBasicDetails(List<Trainer> trainerList){
        log.info(StringConstants.START_SERVICE_METHOD.getValue(),"convertTrainerToTrainerBasicDetails",ValueMapper.class.getName(),trainerList);
        List<TrainerBasicDetails> traineeDetailsList = new ArrayList<>();
        for(Trainer trainer : trainerList){
            TrainerBasicDetails trainerBasicDetails = new TrainerBasicDetails();
            trainerBasicDetails.setUserName(trainer.getUser().getUserName());
            trainerBasicDetails.setFirstName(trainer.getUser().getFirstName());
            trainerBasicDetails.setLastName(trainer.getUser().getLastName());
            trainerBasicDetails.setActive(trainer.getUser().getIsActive());
            traineeDetailsList.add(trainerBasicDetails);
        }
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), "convertTrainerToTrainerBasicDetails");
        return traineeDetailsList;
    }

    public static GymReportDTO trainingToGymReportDTO(Trainer trainer){
        log.info(StringConstants.START_SERVICE_METHOD.getValue(),"TrainingToGymReportDTO",ValueMapper.class.getName(),trainer);
        GymReportDTO gymReportDTO = GymReportDTO.builder()
                .trainerUserName(trainer.getUser().getUserName())
                .trainerFirstName(trainer.getUser().getFirstName())
                .trainerLastName(trainer.getUser().getLastName())
                .isActive(trainer.getUser().getIsActive())
                .build();
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), "TrainingToGymReportDTO");
        return gymReportDTO;
    }



    public static User convertToUser(String firstname, String lastname, String email)
    {
        User user = new User();
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        return user;
    }
}

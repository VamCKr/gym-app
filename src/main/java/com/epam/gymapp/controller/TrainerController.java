package com.epam.gymapp.controller;

import com.epam.gymapp.dto.CredentialsDetails;
import com.epam.gymapp.dto.request.*;
import com.epam.gymapp.dto.response.TrainerBasicDetails;
import com.epam.gymapp.dto.response.TrainerDetails;
import com.epam.gymapp.dto.response.TrainingResponse;
import com.epam.gymapp.kafka.NotificationProducer;
import com.epam.gymapp.model.StringConstants;
import com.epam.gymapp.service.TrainerServiceImpl;
import com.epam.gymapp.util.NotificationMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/trainers")
@RestController
@RequiredArgsConstructor
public class TrainerController {


    private final TrainerServiceImpl trainerService;

    private TrainerDetails trainerDetails;


    private final NotificationProducer notificationProducer;

    @PostMapping
    public ResponseEntity<CredentialsDetails> registerTrainer(@Valid @RequestBody TrainerRegistration trainerDetails) throws JsonProcessingException {
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(), "registerTrainer", this.getClass().getName(), trainerDetails);
        CredentialsDetails credentialsDetails = trainerService.createTrainer(trainerDetails);


        notificationProducer.sendCredentialsNotification(NotificationMapper.getNotificationDTO(credentialsDetails,trainerDetails.getEmail()));

        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(), "registerTrainer");
        return new ResponseEntity<>(credentialsDetails, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<TrainerDetails> getTrainer(@Valid @RequestParam String username){
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(), "getTrainee", this.getClass().getName(), username);
        trainerDetails = trainerService.getTrainer(username);
        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(), "getTrainee");
        return new ResponseEntity<>(trainerDetails, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TrainerDetails> updateTrainer(@Valid @RequestBody TrainerUpdateRequest trainerUpdatedDetails) throws JsonProcessingException {
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(), "updateTrainer", this.getClass().getName(), trainerUpdatedDetails);
        trainerDetails = trainerService.updateTrainer(trainerUpdatedDetails);

        notificationProducer.sendUpdateNotification(NotificationMapper.getNotificationDTO(trainerDetails,trainerUpdatedDetails.getEmail()));

        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(), "updateTrainer");
        return new ResponseEntity<>(trainerDetails, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTrainer(@Valid @RequestParam String username){
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(),"deleteTrainer",this.getClass().getName(),username);
        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(),"deleteTrainer");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/trainee")
    public ResponseEntity<List<TrainerBasicDetails>> getTrainerNotAssignedOnTrainee(@Valid @RequestParam String traineeUsername){
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(), "getTrainerNotAssignedOnTrainee", this.getClass().getName(), traineeUsername);
        List<TrainerBasicDetails> trainerDetailsList = trainerService.getTraineeNotAssignedActiveTrainers(traineeUsername);
        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(), "getTrainerNotAssignedOnTrainee");
        return new ResponseEntity<>(trainerDetailsList ,HttpStatus.OK);
    }

    @PostMapping("/trainings")
    public ResponseEntity<List<TrainingResponse>> getTrainerTrainingsList(@Valid @RequestBody TrainerTrainings trainerTrainings){
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(), "getTraineeTrainingsList", this.getClass().getName(), trainerTrainings);
        List<TrainingResponse> trainingResponseList = trainerService.getTrainerTrainings(trainerTrainings);
        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(), "getTraineeTrainingsList");
        return new ResponseEntity<>(trainingResponseList, HttpStatus.OK);
    }
}

package com.epam.gymapp.controller;

import com.epam.gymapp.dto.CredentialsDetails;
import com.epam.gymapp.dto.request.*;
import com.epam.gymapp.dto.response.TraineeDetails;
import com.epam.gymapp.dto.response.TrainingResponse;
import com.epam.gymapp.kafka.NotificationProducer;
import com.epam.gymapp.model.StringConstants;
import com.epam.gymapp.service.TraineeServiceImpl;
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
@RequestMapping("/trainees")
@RestController
@RequiredArgsConstructor
public class TraineeController {

    private final TraineeServiceImpl traineeService;

    private TraineeDetails traineeDetails;


    private final NotificationProducer notificationProducer;

    @PostMapping
    public ResponseEntity<CredentialsDetails> registerTrainee(@Valid @RequestBody TraineeRegistration traineeDetails) throws JsonProcessingException {
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(), "registerTrainee", this.getClass().getName(), traineeDetails);
        CredentialsDetails credentialsDetails = traineeService.createTrainee(traineeDetails);

       notificationProducer.sendCredentialsNotification(NotificationMapper.getNotificationDTO(credentialsDetails,traineeDetails.getEmail()));

        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(), "registerTrainee");
        return new ResponseEntity<>(credentialsDetails, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<TraineeDetails> getTrainee(@Valid @RequestParam String username){
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(), "getTrainee", this.getClass().getName(), username);
        traineeDetails = traineeService.getTrainee(username);
        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(), "getTrainee");
        return new ResponseEntity<>(traineeDetails, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<TraineeDetails> updateTrainee(@Valid @RequestBody TraineeUpdateRequest traineeUpdatedDetails) throws JsonProcessingException {
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(), "updateTrainee", this.getClass().getName(), traineeUpdatedDetails);
        traineeDetails = traineeService.updateTrainee(traineeUpdatedDetails);
        notificationProducer.sendUpdateNotification(NotificationMapper.getNotificationDTO(traineeDetails,traineeUpdatedDetails.getEmail()));

        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(), "updateTrainee");
        return new ResponseEntity<>(traineeDetails, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTrainee(@Valid @RequestParam String username){
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(),"deleteTrainee",this.getClass().getName(),username);
        traineeService.removeTrainee(username);
        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(),"deleteTrainee");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/trainers_list")
    public ResponseEntity<TraineeDetails> updateTraineesTrainerList(@Valid @RequestBody TraineesTrainerUpdateRequest traineesTrainerUpdateRequestDetails) {
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(), "updateTraineesTrainerList", this.getClass().getName(), traineesTrainerUpdateRequestDetails);
        traineeDetails = traineeService.updateTraineesTrainerList(traineesTrainerUpdateRequestDetails);
        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(), "updateTraineesTrainerList");
        return new ResponseEntity<>(traineeDetails, HttpStatus.OK);
    }

    @PostMapping("/trainings")
    public ResponseEntity<List<TrainingResponse>> getTraineeTrainingsList(@Valid @RequestBody TraineeTrainingsRequest traineeTrainings){
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(), "getTraineeTrainingsList", this.getClass().getName(), traineeTrainings);
        List<TrainingResponse> trainingResponseList = traineeService.getTraineeTrainings(traineeTrainings);
        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(), "getTraineeTrainingsList");
        return new ResponseEntity<>(trainingResponseList, HttpStatus.OK);
    }
}

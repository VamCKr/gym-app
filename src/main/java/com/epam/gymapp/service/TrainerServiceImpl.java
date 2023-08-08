package com.epam.gymapp.service;

import com.epam.gymapp.customexceptions.TraineeException;
import com.epam.gymapp.customexceptions.UserException;
import com.epam.gymapp.dto.CredentialsDetails;
import com.epam.gymapp.dto.request.TrainerRegistration;
import com.epam.gymapp.dto.request.TrainerTrainings;
import com.epam.gymapp.dto.request.TrainerUpdateRequest;
import com.epam.gymapp.dto.response.TraineeBasicDetails;
import com.epam.gymapp.dto.response.TrainerBasicDetails;
import com.epam.gymapp.dto.response.TrainerDetails;
import com.epam.gymapp.dto.response.TrainingResponse;
import com.epam.gymapp.model.*;
import com.epam.gymapp.repository.TrainerRepository;
import com.epam.gymapp.repository.TrainingTypeRepository;
import com.epam.gymapp.repository.UserRepository;
import com.epam.gymapp.util.CredentialGenerator;
import com.epam.gymapp.util.ValueMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainerServiceImpl implements TrainerService {


    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final CredentialGenerator credentialGenerator;

    private final TrainingTypeRepository trainingTypeRepository;

    private final UserServiceImpl userService;

    private final TrainingServiceImpl trainingService;

    @Override
    public CredentialsDetails createTrainer(TrainerRegistration trainerDetails) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), CREATE_TRAINER, this.getClass().getName(), trainerDetails);
        User user = ValueMapper.convertToUser(trainerDetails.getFirstName(), trainerDetails.getLastName(), trainerDetails.getEmail());
        CredentialsDetails credentialsDetails = credentialGenerator.generateCredentials(user);
        user.setUserName(credentialsDetails.getUserName());
        user.setPassword(credentialsDetails.getPassword());
        user.setIsActive(true);
        user = userRepository.save(user);
        TrainingType trainingType = trainingTypeRepository.findByTrainingTypeName(trainerDetails.getSpecialization()).orElseThrow(() -> new UserException("No specialization with that name available"));
        Trainer trainer = new Trainer();
        trainer.setUser(user);
        trainer.setSpecialization(trainingType);
        trainerRepository.save(trainer);
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), CREATE_TRAINER);
        return credentialsDetails;
    }

    @Override
    public TrainerDetails getTrainer(String username) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), GET_TRAINER, this.getClass().getName(), username);
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UserException("No Account with that username"));
        if (user.getTrainer() == null) {
            log.info(StringConstants.ERROR_MESSAGE.getValue(), GET_TRAINER, this.getClass().getName());
            throw new UserException("This user is not a trainer");
        }
        TrainerDetails trainerDetails = new TrainerDetails();
        Trainer trainer = trainerRepository.findByUserId(user.getId());
        trainerDetails.setActive(user.getIsActive());
        trainerDetails.setFirstName(user.getFirstName());
        trainerDetails.setLastName(user.getLastName());
        trainerDetails.setUserName(user.getUserName());
        List<TraineeBasicDetails> traineeDetailsList = ValueMapper.convertTraineeToTraineeBasicDetails(trainer.getTraineeList());
        trainerDetails.setSpecialization(trainer.getSpecialization().getTrainingTypeName());
        trainerDetails.setTraineeDetailsList(traineeDetailsList);
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), GET_TRAINER);
        return trainerDetails;
    }

    @Override
    public TrainerDetails updateTrainer(TrainerUpdateRequest trainerUpdateRequest) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), UPDATE_TRAINER, this.getClass().getName(), trainerUpdateRequest);
        User user = userRepository.findByUserName(trainerUpdateRequest.getUserName()).orElseThrow(() -> new UserException("There is no user with that username"));
        if (user.getTrainer() == null) {
            log.info(StringConstants.ERROR_MESSAGE.getValue(), UPDATE_TRAINER, this.getClass().getName());
            throw new UserException("This user is not a trainer");
        }
        user.setFirstName(trainerUpdateRequest.getFirstName());
        user.setLastName(trainerUpdateRequest.getLastName());
        user.setEmail(trainerUpdateRequest.getEmail());
        user.setIsActive(trainerUpdateRequest.isActive());
        User updatedUser = userRepository.save(user);
        Trainer updatedTrainer = trainerRepository.findByUserId(updatedUser.getId());
        updatedTrainer.setUser(user);
        trainerRepository.save(updatedTrainer);
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), UPDATE_TRAINER);
        return getTrainer(updatedUser.getUserName());
    }

    @Override
    public List<TrainerBasicDetails> getTraineeNotAssignedActiveTrainers(String traineeName) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), "getTraineeNotAssignedActiveTrainers", this.getClass().getName(), traineeName);
        User user = userService.getUser(traineeName);
        List<Trainer> trainerDetails = trainerRepository.findTrainersNotInList(user.getTrainee().getTrainerList());
        List<TrainerBasicDetails> trainerBasicDetailsList = ValueMapper.convertTrainerToTrainerBasicDetails(trainerDetails);
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), "getTraineeNotAssignedActiveTrainers");
        return trainerBasicDetailsList;
    }

    @Override
    public List<TrainingResponse> getTrainerTrainings(TrainerTrainings trainerTrainings) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), "getTrainerTrainings", this.getClass().getName(), trainerTrainings);
        Trainer trainer = Optional.of(userService.getUser(trainerTrainings.getUserName()).getTrainer()).orElseThrow(() -> new TraineeException("No trainer with that username"));
        List<Training> trainingList = trainingService.getTrainingsForTrainer(trainer, trainerTrainings.getStartDate(), trainerTrainings.getEndDate(), trainerTrainings.getUserName());
        List<TrainingResponse> trainingResponseList = ValueMapper.convertTrainingToTrainingResponse(trainingList);
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), "getTrainerTrainings");
        return trainingResponseList;
    }

}

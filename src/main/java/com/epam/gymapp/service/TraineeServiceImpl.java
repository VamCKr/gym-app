package com.epam.gymapp.service;

import com.epam.gymapp.customexceptions.TraineeException;
import com.epam.gymapp.customexceptions.UserException;
import com.epam.gymapp.dto.CredentialsDetails;
import com.epam.gymapp.dto.request.TraineeRegistration;
import com.epam.gymapp.dto.request.TraineeTrainingsRequest;
import com.epam.gymapp.dto.request.TraineeUpdateRequest;
import com.epam.gymapp.dto.request.TraineesTrainerUpdateRequest;
import com.epam.gymapp.dto.response.TraineeDetails;
import com.epam.gymapp.dto.response.TrainerBasicDetails;
import com.epam.gymapp.dto.response.TrainingResponse;
import com.epam.gymapp.model.*;
import com.epam.gymapp.repository.TraineeRepository;
import com.epam.gymapp.repository.UserRepository;
import com.epam.gymapp.util.CredentialGenerator;
import com.epam.gymapp.util.ValueMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class TraineeServiceImpl implements TraineeService {

    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;
    private final CredentialGenerator credentialGenerator;

    private final UserServiceImpl userService;


    private final TrainingServiceImpl trainingService;

    @Override
    public CredentialsDetails createTrainee(TraineeRegistration traineeDetails) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), "createTrainee", this.getClass().getName(), traineeDetails);
        User user = ValueMapper.convertToUser(traineeDetails.getFirstName(), traineeDetails.getLastName(), traineeDetails.getEmail());
        CredentialsDetails credentialsDetails = credentialGenerator.generateCredentials(user);
        user.setUserName(credentialsDetails.getUserName());
        user.setPassword(credentialsDetails.getPassword());
        user.setIsActive(true);
        user = userRepository.save(user);
        Trainee trainee = new Trainee();
        trainee.setAddress(traineeDetails.getAddress());
        trainee.setDateOfBirth(traineeDetails.getDateOfBirth());
        trainee.setUser(user);
        traineeRepository.save(trainee);
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), "createTrainee");
        return credentialsDetails;
    }

    @Override
    public TraineeDetails getTrainee(String username) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), GET_TRAINEE, this.getClass().getName(), username);
        User user = userRepository.findByUserName(username).orElseThrow(() -> new UserException(NO_ACCOUNT_WITH_THAT_USERNAME));
        if (user.getTrainee() == null) {
            log.info(StringConstants.ERROR_MESSAGE.getValue(), GET_TRAINEE, this.getClass().getName());
            throw new UserException(THIS_USER_IS_NOT_A_TRAINEE);
        }
        Trainee trainee = user.getTrainee();
        TraineeDetails traineeDetails = new TraineeDetails();
        traineeDetails.setDateOfBirth(trainee.getDateOfBirth());
        traineeDetails.setActive(user.getIsActive());
        traineeDetails.setAddress(trainee.getAddress());
        traineeDetails.setFirstName(user.getFirstName());
        traineeDetails.setLastName(user.getLastName());
        traineeDetails.setUserName(user.getUserName());
        List<TrainerBasicDetails> trainerDetailsList = ValueMapper.convertTrainerToTrainerBasicDetails(trainee.getTrainerList());
        traineeDetails.setTrainersList(trainerDetailsList);
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), GET_TRAINEE);
        return traineeDetails;
    }

    @Override
    public TraineeDetails updateTrainee(TraineeUpdateRequest traineeUpdateRequest) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), UPDATE_TRAINEE, this.getClass().getName(), traineeUpdateRequest);
        User user = userRepository.findByUserName(traineeUpdateRequest.getUserName()).orElseThrow(() -> new UserException(NO_ACCOUNT_WITH_THAT_USERNAME));
        if (user.getTrainee() == null) {
            log.info(StringConstants.ERROR_MESSAGE.getValue(), UPDATE_TRAINEE, this.getClass().getName());
            throw new TraineeException(THIS_USER_IS_NOT_A_TRAINEE);
        }
        user.setFirstName(traineeUpdateRequest.getFirstName());
        user.setLastName(traineeUpdateRequest.getLastName());
        user.setEmail(traineeUpdateRequest.getEmail());
        user.setIsActive(traineeUpdateRequest.isActive());
        User updatedUser = userRepository.save(user);
        Trainee updatedTrainee = traineeRepository.findByUserId(updatedUser.getId());
        updatedTrainee.setUser(user);
        updatedTrainee.setDateOfBirth(traineeUpdateRequest.getDob());
        updatedTrainee.setAddress(traineeUpdateRequest.getAddress());
        traineeRepository.save(updatedTrainee);
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), UPDATE_TRAINEE);
        return getTrainee(updatedUser.getUserName());
    }

    public TraineeDetails updateTraineesTrainerList(TraineesTrainerUpdateRequest traineeUpdateRequest) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), UPDATE_TRAINEES_TRAINER_LIST, this.getClass().getName(), traineeUpdateRequest);

        User user = userRepository.findByUserName(traineeUpdateRequest.getTraineeUserName()).orElseThrow(() -> new UserException(NO_ACCOUNT_WITH_THAT_USERNAME));

        if (user.getTrainee() == null) {
            log.info(StringConstants.ERROR_MESSAGE.getValue(), UPDATE_TRAINEES_TRAINER_LIST, this.getClass().getName());
            throw new TraineeException(THIS_USER_IS_NOT_A_TRAINEE);
        }

        Trainee updatedTrainee = traineeRepository.findByUserId(user.getId());


        List<User> trainers = traineeUpdateRequest.getTrainersUserName().stream().map(trainerUsername -> userRepository.findByUserName(trainerUsername).orElseThrow(() -> {
            log.info(StringConstants.ERROR_MESSAGE.getValue(), UPDATE_TRAINEES_TRAINER_LIST, this.getClass().getName());
            return new UserException(THERE_IS_NO_USER_WITH_THAT_USERNAME);
        })).filter(trainerUser -> trainerUser.getTrainer() != null).collect(Collectors.toCollection(ArrayList::new));

        if (trainers.isEmpty()) {
            log.info(StringConstants.ERROR_MESSAGE.getValue(), UPDATE_TRAINEES_TRAINER_LIST, this.getClass().getName());
            throw new UserException(NONE_OF_THE_USERS_ARE_TRAINERS);
        }

        List<Trainer> updatedTrainers = trainers.stream().map(User::getTrainer).collect(Collectors.toCollection(ArrayList::new));
        updatedTrainee.setTrainerList(updatedTrainers);
        traineeRepository.save(updatedTrainee);

        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), UPDATE_TRAINEES_TRAINER_LIST);
        return getTrainee(user.getUserName());
    }

    @Override
    @Transactional
    public void removeTrainee(String username) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), "removeTrainee", this.getClass().getName(), username);
        User user = userService.getUser(username);
        Trainee trainee = Optional.of(user.getTrainee()).orElseThrow(() -> new TraineeException("No trainee with that username"));
        trainee.getTrainerList().forEach(trainer -> trainer.getTraineeList().remove(trainee));
        userService.removeUser(user);
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), "removeTrainee");
    }

    @Override
    public List<TrainingResponse> getTraineeTrainings(TraineeTrainingsRequest traineeTrainings) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(), "getTraineeTrainings", this.getClass().getName(), traineeTrainings);
        Trainee trainee = Optional.of(userService.getUser(traineeTrainings.getUserName()).getTrainee()).orElseThrow(() -> new TraineeException("No trainee with that username"));
        List<Training> trainingList = trainingService.getTrainingsForTrainee(trainee, traineeTrainings.getStartDate(), traineeTrainings.getEndDate(), traineeTrainings.getTrainingTypeId());
        List<TrainingResponse> trainingResponseList = ValueMapper.convertTrainingToTrainingResponse(trainingList);
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(), "getTraineeTrainings");
        return trainingResponseList;
    }
}

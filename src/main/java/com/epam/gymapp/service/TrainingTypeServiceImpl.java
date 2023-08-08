package com.epam.gymapp.service;

import com.epam.gymapp.customexceptions.UserException;
import com.epam.gymapp.model.StringConstants;
import com.epam.gymapp.model.TrainingType;
import com.epam.gymapp.repository.TrainingTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainingTypeServiceImpl implements TrainingTypeService{

    private final TrainingTypeRepository trainingTypeRepository;

    @Override
    public void addTrainingType(String trainingName) {
        log.info(StringConstants.START_SERVICE_METHOD.getValue(),"addTrainingType",this.getClass().getName(),trainingName);
        TrainingType trainingType = new TrainingType();
        trainingType.setTrainingTypeName(trainingName);
        trainingTypeRepository.save(trainingType);
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(),"addTrainingType");
    }

    @Override
    public TrainingType getTrainingType(int id){
        log.info(StringConstants.START_SERVICE_METHOD.getValue(),"getTrainingType",this.getClass().getName(),id);
        Optional<TrainingType> optionalTrainingType = trainingTypeRepository.findById(id);
        TrainingType trainingType = optionalTrainingType.orElseThrow(() -> new UserException("No Training type with that id"));
        log.info(StringConstants.EXIT_SERVICE_METHOD.getValue(),"getTrainingType");
        return  trainingType;
    }
}

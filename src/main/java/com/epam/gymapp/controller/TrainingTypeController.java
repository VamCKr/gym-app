package com.epam.gymapp.controller;

import com.epam.gymapp.model.StringConstants;
import com.epam.gymapp.service.TrainingTypeServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/training-types")
@RestController
@RequiredArgsConstructor
public class TrainingTypeController {


    private final TrainingTypeServiceImpl trainingTypeService;

    @PostMapping
    public ResponseEntity<Void> addTrainingTypeName(@Valid @RequestParam String trainingName) {
        log.info(StringConstants.START_CONTROLLER_METHOD.getValue(), "addTraining", this.getClass().getName(), trainingName);
        trainingTypeService.addTrainingType(trainingName);
        log.info(StringConstants.EXIT_CONTROLLER_METHOD.getValue(), "addTraining");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

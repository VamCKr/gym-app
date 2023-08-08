package com.epam.gymapp.service;

import com.epam.gymapp.model.TrainingType;

public interface TrainingTypeService {
    public void addTrainingType (String trainingName);

    TrainingType getTrainingType(int id);
}

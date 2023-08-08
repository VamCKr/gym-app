package com.epam.gymapp.repository;

import com.epam.gymapp.model.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingTypeRepository extends JpaRepository<TrainingType,Integer> {
        public Optional<TrainingType> findByTrainingTypeName(String trainingName);
}

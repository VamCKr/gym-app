package com.epam.gymapp.repository;

import com.epam.gymapp.model.Trainee;
import com.epam.gymapp.model.Trainer;
import com.epam.gymapp.model.Training;
import com.epam.gymapp.model.TrainingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training,Integer> {
    @Query("SELECT t FROM training t WHERE (:from IS NULL OR t.date >= :from) AND (:to IS NULL OR t.date < :to) AND (:trainee IS NULL OR t.trainee = :trainee) AND (:trainingType IS NULL OR t.trainingType = :trainingType)")
    List<Training> findAllTrainingInBetween(@Param("from") LocalDate from, @Param("to") LocalDate to, @Param("trainee") Trainee trainee, @Param("trainingType") TrainingType trainingType);

    @Query("SELECT t FROM training t WHERE (:from IS NULL OR t.date >= :from) AND (:to IS NULL OR t.date < :to) AND (:trainer IS NULL OR t.trainer = :trainer) AND (:trainingType IS NULL OR t.trainee = :trainingType)")
    List<Training> findAllTrainingInBetween(@Param("from") LocalDate from, @Param("to") LocalDate to, @Param("trainer") Trainer trainer, @Param("trainingType")Trainee trainingType);
}

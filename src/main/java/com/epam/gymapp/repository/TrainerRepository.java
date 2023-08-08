package com.epam.gymapp.repository;


import com.epam.gymapp.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TrainerRepository extends JpaRepository<Trainer,Integer> {
    public Trainer findByUserId(int id);

    @Query("SELECT t FROM trainer t WHERE t NOT IN :trainerList")
    List<Trainer> findTrainersNotInList(@Param("trainerList") List<Trainer> trainerList);
}

package com.epam.gymapp.repository;

import com.epam.gymapp.model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee,Integer> {

    public Trainee findByUserId(int id);



}

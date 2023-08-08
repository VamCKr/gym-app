package com.epam.gymapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "trainer")
@Data
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    User user;

    @OneToMany(mappedBy = "trainer")
    List<Training> trainingList;

    @ManyToMany(mappedBy = "trainerList")
    List<Trainee> traineeList;

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", trainingList=" + trainingList +
                ", traineeList=" + traineeList +
                ", specialization=" + specialization +
                '}';
    }

    @ManyToOne
    TrainingType specialization;


}

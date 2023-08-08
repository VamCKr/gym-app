package com.epam.gymapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "training")
@Data
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private LocalDate date;

    private int duration;

    @ManyToOne
    Trainee trainee;

    @ManyToOne
    Trainer trainer;

    @ManyToOne
    TrainingType trainingType;

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", duration=" + duration +
                '}';
    }
}

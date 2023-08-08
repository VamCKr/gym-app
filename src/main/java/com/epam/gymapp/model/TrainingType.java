package com.epam.gymapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Entity(name = "training_type")
@Data
public class TrainingType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @Pattern(regexp = "Fitness|Yoga|Zumba|Stretching|Resistance", message = "Invalid training type. Allowed values are fitness, yoga, Zumba, stretching, resistance.")
    private String trainingTypeName;

    @OneToMany(mappedBy = "specialization")
    List<Trainer> trainerList;

    @OneToMany(mappedBy = "trainingType")
    List<Training> trainingList;

    @Override
    public String toString() {
        return "TrainingType{" +
                "id=" + id +
                ", trainingTypeName='" + trainingTypeName + '\'' +
                ", trainingList=" + trainingList +
                '}';
    }
}

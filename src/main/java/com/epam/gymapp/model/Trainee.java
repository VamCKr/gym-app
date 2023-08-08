package com.epam.gymapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "Trainee")
@Data
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate dateOfBirth;

    private String address;

    @JsonManagedReference
    @OneToOne
    User user;

    @OneToMany(mappedBy = "trainee")
    private List<Training> trainingList;

    @ManyToMany
    private List<Trainer> trainerList;

    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", user=" + user +
                ", trainingList=" + trainingList +
                '}';
    }
}

package com.epam.gymapp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity(name = "user")
@Data
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false,unique = true)
    String userName;

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    Boolean isActive;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Trainee trainee;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    Trainer trainer;



}

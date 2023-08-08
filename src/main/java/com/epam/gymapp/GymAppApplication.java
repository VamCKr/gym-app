package com.epam.gymapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.security.SecureRandom;

@SpringBootApplication
public class GymAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymAppApplication.class, args);
    }
    @Bean
    public ModelMapper getModelMapper()
    {
        return new ModelMapper();
    }

    @Bean
    public SecureRandom getRandom(){return new SecureRandom();}
}

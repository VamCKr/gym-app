package com.epam.gymapp.model;

import lombok.Getter;

@Getter
public enum StringConstants {

    START_CONTROLLER_METHOD("ENTERED {} Method of {} class with data {}"),
    EXIT_CONTROLLER_METHOD("Exiting {} method"),

    START_SERVICE_METHOD("Entered {} Method of {} Class with data {} with port {}"),
    EXIT_SERVICE_METHOD("Exiting {} method"),
    ERROR_MESSAGE("Exception risen in {} method of {} class, Exiting the entered method");


    private final String value;


    StringConstants(String value) {
        this.value = value;
    }
}


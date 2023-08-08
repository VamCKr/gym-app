package com.epam.gymapp.customexceptions;

public class TraineeException extends RuntimeException{
    TraineeException(){
        super("Cannot perform operation with trainer");
    }

    public TraineeException(String message){
        super(message);
    }
}

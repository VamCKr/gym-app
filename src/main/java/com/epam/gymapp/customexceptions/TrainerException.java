package com.epam.gymapp.customexceptions;

public class TrainerException extends RuntimeException{
    TrainerException(){
        super("Cannot perform operation with trainer");
    }

    public TrainerException(String message){
        super(message);
    }
}

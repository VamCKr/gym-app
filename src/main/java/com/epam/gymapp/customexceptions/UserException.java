package com.epam.gymapp.customexceptions;

public class UserException extends RuntimeException{
    public UserException(){
        super("Cannot perform operation with trainer");
    }

    public UserException(String message){
        super(message);
    }
}

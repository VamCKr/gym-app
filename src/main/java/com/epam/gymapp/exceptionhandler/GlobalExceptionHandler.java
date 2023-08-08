package com.epam.gymapp.exceptionhandler;

import com.epam.gymapp.customexceptions.TraineeException;
import com.epam.gymapp.customexceptions.TrainerException;
import com.epam.gymapp.customexceptions.UserException;
import com.epam.gymapp.model.ExceptionResponse;
import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest req){
        List<String> inputError = new ArrayList<>();
        e.getAllErrors().forEach(error -> inputError.add(error.getDefaultMessage()));
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date().toString(),inputError.toString(),req.getDescription(false), HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {TraineeException.class, TrainerException.class, UserException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleQuestionQuizException(Exception e, WebRequest req){
        log.error("Exception raised "+ExceptionUtils.getStackTrace(e));
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date().toString(),e.getMessage(),req.getDescription(false), HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<ExceptionResponse> handleUnexpectedTypeException(UnexpectedTypeException e, WebRequest req){
        log.error(ExceptionUtils.getStackTrace(e));
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date().toString(),e.getMessage(),req.getDescription(false), HttpStatus.UNSUPPORTED_MEDIA_TYPE.name());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(value ={DataIntegrityViolationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleDataIntegrityException(DataIntegrityViolationException e,WebRequest req){
        log.error(ExceptionUtils.getStackTrace(e));
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date().toString(),e.getMessage(),req.getDescription(false),HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value ={RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponse> handleRunTimeException(RuntimeException e,WebRequest req){
        log.error(ExceptionUtils.getStackTrace(e));
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date().toString(),e.getMessage(),req.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR.name());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

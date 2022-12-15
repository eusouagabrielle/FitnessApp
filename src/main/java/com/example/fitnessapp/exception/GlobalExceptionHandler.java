package com.example.fitnessapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AthleteNotFoundException.class)
    public ResponseEntity<ErrorObject> handleAthleteNotFoundException (AthleteNotFoundException err, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(err.getMessage());
        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TrainerNotFoundException.class)
    public ResponseEntity<ErrorObject> handleTrainerNotFoundException (TrainerNotFoundException err, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(err.getMessage());
        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoutineNotFoundException.class)
    public ResponseEntity<ErrorObject> handleRoutineNotFoundException (RoutineNotFoundException err, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(err.getMessage());
        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ExerciseNotFoundException.class)
    public ResponseEntity<ErrorObject> handleExerciseNotFoundException (ExerciseNotFoundException err, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(err.getMessage());
        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorObject> handleUserNotFoundException (ExerciseNotFoundException err, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(err.getMessage());
        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AuthorityException.class)
    public ResponseEntity<ErrorObject> handleAuthorityException (ExerciseNotFoundException err, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(err.getMessage());
        return new ResponseEntity<ErrorObject>(errorObject,HttpStatus.BAD_REQUEST);
    }

}

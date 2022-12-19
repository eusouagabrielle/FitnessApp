package com.example.fitnessapp.controller;


import com.example.fitnessapp.dto.ExerciseDto;
import com.example.fitnessapp.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/workout")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercises")
    @RolesAllowed({"ROLE_ATHLETE", "ROLE_TRAINER"})
    public ResponseEntity<List<ExerciseDto>> getExercises(){
        return new ResponseEntity<>(exerciseService.getAllExercises(), HttpStatus.OK);
    }

    @GetMapping("/exercise/{id}")
    @RolesAllowed({"ROLE_ATHLETE", "ROLE_TRAINER"})
    public ResponseEntity<ExerciseDto> getAExercise(@PathVariable Long id){
        return ResponseEntity.ok(exerciseService.getExerciseById(id));
    }

    @PostMapping("/exercise")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<ExerciseDto> createExercise(@RequestBody ExerciseDto dto){
        return new ResponseEntity<>(exerciseService.createExercise(dto), HttpStatus.CREATED);
    }

    @PutMapping("/update/exercise/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<ExerciseDto> updateExercise(@RequestBody ExerciseDto dto, @PathVariable("id") Long id){
        ExerciseDto response = exerciseService.updateExercise(dto, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/exercise/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<String> deleteExercise(@PathVariable("id") Long id){
        exerciseService.deleteExercise(id);
        return new ResponseEntity<>("Exercise delete", HttpStatus.OK);
    }

}

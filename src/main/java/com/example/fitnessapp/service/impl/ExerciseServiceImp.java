package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.dto.ExerciseDto;
import com.example.fitnessapp.exception.ExerciseNotFoundException;
import com.example.fitnessapp.model.Exercise;
import com.example.fitnessapp.repository.ExerciseRepository;
import com.example.fitnessapp.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImp implements ExerciseService {

    private ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseServiceImp(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<ExerciseDto> getAllExercises() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exercises.stream().map(exercise -> mapToDto(exercise)).collect(Collectors.toList());
    }

    @Override
    public ExerciseDto getExerciseById(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()->
                new ExerciseNotFoundException("Exercise could not be found"));
        return mapToDto(exercise);
    }

    @Override
    public ExerciseDto createExercise(ExerciseDto dto) {
        Exercise exercise = new Exercise();
        exercise.setName(dto.getName());
        exercise.setTargetMuscle(dto.getTargetMuscle());
        exercise.setSets(dto.getSets());
        exercise.setRepetitions(dto.getRepetitions());
        exercise.setWeight(dto.getWeight());
        exercise.setRoutines(dto.getRoutines());

        Exercise newExercise = exerciseRepository.save(exercise);

        ExerciseDto response = new ExerciseDto();
        response.setId(newExercise.getId());
        response.setName(newExercise.getName());
        response.setTargetMuscle(newExercise.getTargetMuscle());
        response.setSets(newExercise.getSets());
        response.setRepetitions(newExercise.getRepetitions());
        response.setWeight(newExercise.getWeight());
        response.setRoutines(newExercise.getRoutines());
        return response;
    }

    @Override
    public ExerciseDto updateExercise(ExerciseDto dto, Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()->
                new ExerciseNotFoundException("Exercise could not be updated"));

        exercise.setName(dto.getName());
        exercise.setTargetMuscle(dto.getTargetMuscle());
        exercise.setSets(dto.getSets());
        exercise.setRepetitions(dto.getRepetitions());
        exercise.setWeight(dto.getWeight());
        exercise.setRoutines(dto.getRoutines());

        Exercise updatedExercise = exerciseRepository.save(exercise);
        return mapToDto(updatedExercise);
    }

    @Override
    public void deleteExercise(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElseThrow(()->
                new ExerciseNotFoundException("Exercise could not be delete"));
        exerciseRepository.delete(exercise);
    }


    private ExerciseDto mapToDto(Exercise exercise){
        ExerciseDto dto = new ExerciseDto();
        dto.setId(exercise.getId());
        dto.setName(exercise.getName());
        dto.setTargetMuscle(exercise.getTargetMuscle());
        dto.setSets(exercise.getSets());
        dto.setRepetitions(exercise.getRepetitions());
        dto.setWeight(exercise.getWeight());
        dto.setRoutines(exercise.getRoutines());
        return dto;
    }



}

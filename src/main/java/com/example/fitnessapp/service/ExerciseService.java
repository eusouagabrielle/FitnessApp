package com.example.fitnessapp.service;



import com.example.fitnessapp.dto.ExerciseDto;

import java.util.List;

public interface ExerciseService {
    ExerciseDto createExercise(ExerciseDto dto );
    List<ExerciseDto> getAllExercises();
    ExerciseDto getExerciseById(Long id);
    ExerciseDto updateExercise(ExerciseDto dto, Long id);
    void deleteExercise(Long id);
}

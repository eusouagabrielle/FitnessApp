package com.example.fitnessapp.dto;

import com.example.fitnessapp.model.Routine;
import lombok.Data;

import java.util.List;

@Data
public class ExerciseDto {
    private Long id;
    private String name;
    private String targetMuscle;
    private int sets;
    private int repetitions;
    private int weight;
    private int calorie;
    private List <Routine> routines;
}

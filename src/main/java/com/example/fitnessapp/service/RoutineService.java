package com.example.fitnessapp.service;


import com.example.fitnessapp.dto.RoutineDto;

import java.util.List;

public interface RoutineService {
    RoutineDto createRoutine(RoutineDto dto );
    List<RoutineDto> getAllRoutines();
    RoutineDto getRoutineById(Long id);
    RoutineDto updateRoutine(RoutineDto dto, Long id);
    void deleteRoutine(Long id);
    Integer getRoutinesCalories(Long id);
}

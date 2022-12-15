package com.example.fitnessapp.service.impl;


import com.example.fitnessapp.dto.RoutineDto;
import com.example.fitnessapp.exception.RoutineNotFoundException;
import com.example.fitnessapp.model.Routine;
import com.example.fitnessapp.repository.RoutineRepository;
import com.example.fitnessapp.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoutineServiceImpl implements RoutineService {
    private RoutineRepository routineRepository;

    @Autowired
    public RoutineServiceImpl(RoutineRepository routineRepository) {
        this.routineRepository = routineRepository;
    }

    @Override
    public List<RoutineDto> getAllRoutines() {
        List<Routine> routines = routineRepository.findAll();
        return routines.stream().map(routine -> mapToDto(routine)).collect(Collectors.toList());
    }
    @Override
    public RoutineDto getRoutineById(Long id) {
        Routine routine = routineRepository.findById(id).orElseThrow(()->
                new RoutineNotFoundException("Routine could not be found"));
        return mapToDto(routine);
    }
    @Override
    public RoutineDto createRoutine(RoutineDto dto) {
        Routine routine = new Routine();
        routine.setName(dto.getName());
        routine.setDescription(dto.getDescription());
        routine.setDurationInWeeks(dto.getDurationInWeeks());
        routine.setDifficultyLevel(dto.getDifficultyLevel());
        routine.setExercises(dto.getExercises());

        Routine newRoutine = routineRepository.save(routine);

        RoutineDto response = new RoutineDto();
        response.setId(newRoutine.getId());
        response.setName(newRoutine.getName());
        response.setDescription(newRoutine.getDescription());
        response.setDurationInWeeks(newRoutine.getDurationInWeeks());
        response.setDifficultyLevel(newRoutine.getDifficultyLevel());
        response.setExercises(newRoutine.getExercises());
        return response;
    }

    @Override
    public RoutineDto updateRoutine(RoutineDto dto, Long id) {
        Routine routine = routineRepository.findById(id).orElseThrow(()->
                new RoutineNotFoundException("Routine could not be updated"));
        routine.setName(dto.getName());
        routine.setDescription(dto.getDescription());
        routine.setDurationInWeeks(dto.getDurationInWeeks());
        routine.setDifficultyLevel(dto.getDifficultyLevel());
        routine.setExercises(dto.getExercises());

        Routine updatedRoutine = routineRepository.save(routine);
        return mapToDto(updatedRoutine);
    }

    @Override
    public void deleteRoutine(Long id) {
        Routine routine = routineRepository.findById(id).orElseThrow(()->
                new RoutineNotFoundException("Routine could not be delete"));
        routineRepository.delete(routine);
    }

    private RoutineDto mapToDto(Routine routine){
        RoutineDto dto = new RoutineDto();
        dto.setId(routine.getId());
        dto.setName(routine.getName());
        dto.setDescription(routine.getDescription());
        dto.setDurationInWeeks(routine.getDurationInWeeks());
        dto.setDifficultyLevel(routine.getDifficultyLevel());
        dto.setExercises(routine.getExercises());
        return dto;
    }
}

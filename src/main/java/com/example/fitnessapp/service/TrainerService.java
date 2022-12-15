package com.example.fitnessapp.service;



import com.example.fitnessapp.dto.TrainerDto;

import java.util.List;

public interface TrainerService {
    TrainerDto createTrainer(TrainerDto dto);
    List<TrainerDto> getAllTrainers();
    TrainerDto getTrainerById(Long id);

    TrainerDto updateTrainer(TrainerDto dto, Long id);

    void deleteTrainer(Long id);
}

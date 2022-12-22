package com.example.fitnessapp.service.impl;

import com.example.fitnessapp.dto.TrainerDto;
import com.example.fitnessapp.exception.TrainerNotFoundException;
import com.example.fitnessapp.model.Trainer;
import com.example.fitnessapp.repository.TrainerRepository;
import com.example.fitnessapp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class TrainerServiceImpl implements TrainerService {

    private TrainerRepository trainerRepository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public List<TrainerDto> getAllTrainers() {
        List<Trainer> trainers = trainerRepository.findAll();
        return trainers.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public TrainerDto getTrainerById(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(()->
                new TrainerNotFoundException("Trainer could not be found"));
        return mapToDto(trainer);
    }

    @Override
    public TrainerDto createTrainer(TrainerDto dto) {
        Trainer trainer = new Trainer();
        trainer.setName(dto.getName());
        trainer.setEmail(dto.getEmail());
        trainer.setPhoneNumber(dto.getPhoneNumber());
        trainer.setKvkNumber(dto.getKvkNumber());
        trainer.setAge(dto.getAge());
        trainer.setYearsOfExperience(dto.getYearsOfExperience());
        trainer.setAthletes(dto.getAthletes());

        Trainer newTrainer = trainerRepository.save(trainer);

        TrainerDto response = new TrainerDto();
        response.setId(newTrainer.getId());
        response.setName(newTrainer.getName());
        response.setEmail(newTrainer.getEmail());
        response.setPhoneNumber(newTrainer.getPhoneNumber());
        response.setKvkNumber(newTrainer.getKvkNumber());
        response.setAge(newTrainer.getAge());
        response.setYearsOfExperience(newTrainer.getYearsOfExperience());
        response.setAthletes(newTrainer.getAthletes());
        return response;
    }
    @Override
    public TrainerDto updateTrainer(TrainerDto dto, Long id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(() ->
                new TrainerNotFoundException("Trainer could not be updated"));

        trainer.setName(dto.getName());
        trainer.setEmail(dto.getEmail());
        trainer.setPhoneNumber(dto.getPhoneNumber());
        trainer.setKvkNumber(dto.getKvkNumber());
        trainer.setAge(dto.getAge());
        trainer.setYearsOfExperience(dto.getYearsOfExperience());
        trainer.setAthletes(dto.getAthletes());

        Trainer updatedTrainer = trainerRepository.save(trainer);
        return mapToDto(updatedTrainer);
    }

    @Override
    public void deleteTrainer(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow(()->
                new TrainerNotFoundException("Trainer could not be delete"));
        trainerRepository.delete(trainer);
    }

    private TrainerDto mapToDto(Trainer trainer) {
        TrainerDto dto = new TrainerDto();
        dto.setId(trainer.getId());
        dto.setName(trainer.getName());
        dto.setEmail(trainer.getEmail());
        dto.setPhoneNumber(trainer.getPhoneNumber());
        dto.setKvkNumber(trainer.getKvkNumber());
        dto.setAge(trainer.getAge());
        dto.setYearsOfExperience(trainer.getYearsOfExperience());
        dto.setAthletes(trainer.getAthletes());
        return dto;
    }
}

package com.example.fitnessapp.service;


import com.example.fitnessapp.dto.TrainerDto;
import com.example.fitnessapp.exception.TrainerNotFoundException;
import com.example.fitnessapp.model.Trainer;
import com.example.fitnessapp.repository.TrainerRepository;
import com.example.fitnessapp.service.impl.TrainerServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceTest {



    @Mock
    private TrainerRepository trainerRepository;
    @InjectMocks
    private TrainerServiceImpl trainerService;
    @Mock
    Trainer trainer;
    TrainerDto trainerDto;



//    @BeforeEach
//    public void setup(){
//        trainer = new Trainer(1L,"Paul","paul@email.com","0678465732",94853432,32,8);
//        secondTrainer = new Trainer(2L,"Gabriel","gabriel@email.com","0676453299",93674832,21,2);
//    }

    @Test
    public void getTrainerByIdException(){
        assertThrows(TrainerNotFoundException.class, () -> trainerService.getTrainerById(50L));
    }
    @Test
    public void getTrainerById(){

        trainer = new Trainer(1L,"Paul","paul@email.com","0678465732",94853432,32,8);

        when(trainerRepository.findById(1L)).thenReturn(Optional.ofNullable(trainer));

        TrainerDto savedTrainer = trainerService.getTrainerById(1L);

        Assertions.assertThat(savedTrainer).isNotNull();
    }
    @Test
    public void createTrainer(){
        trainer = new Trainer(1L,"Paul","paul@email.com","0678465732",94853432,32,8);
        trainerDto = new TrainerDto(1L,"Paul","paul@email.com","0678465732",94853432,32,8);

        when(trainerRepository.save(Mockito.any(Trainer.class))).thenReturn(trainer);
        TrainerDto savedTrainer = trainerService.createTrainer(trainerDto);
        Assertions.assertThat(savedTrainer).isNotNull();

    }

    @Test
    public void updateTrainer(){
        trainer = new Trainer(1L,"Paul","paul@email.com","0678465732",94853432,32,8);
        trainerDto = new TrainerDto(1L,"Paul","paul@email.com","0678465732",94853432,32,8);

        when(trainerRepository.findById(1L)).thenReturn(Optional.ofNullable(trainer));
        when(trainerRepository.save(Mockito.any(Trainer.class))).thenReturn(trainer);

        TrainerDto savedTrainer = trainerService.updateTrainer(trainerDto, 1L);
        Assertions.assertThat(savedTrainer).isNotNull();
    }

    @Test
    public void deleteTrainer(){
        trainer = new Trainer(1L,"Paul","paul@email.com","0678465732",94853432,32,8);
        when(trainerRepository.findById(1L)).thenReturn(Optional.ofNullable(trainer));
        assertAll(()-> trainerService.deleteTrainer(1L));
    }


}

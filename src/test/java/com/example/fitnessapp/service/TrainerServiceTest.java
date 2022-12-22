package com.example.fitnessapp.service;


import com.example.fitnessapp.dto.TrainerDto;
import com.example.fitnessapp.exception.TrainerNotFoundException;
import com.example.fitnessapp.model.Trainer;
import com.example.fitnessapp.repository.TrainerRepository;
import com.example.fitnessapp.service.impl.TrainerServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceTest {


    @Mock
    private TrainerRepository trainerRepository;
    @InjectMocks
    private TrainerServiceImpl trainerService;
    @Mock
    Trainer trainer;
    TrainerDto trainerDto;

    @Test
    public void getTrainerById(){
        assertThrows(TrainerNotFoundException.class, ()->{trainerService.getTrainerById(50L);});
        trainer = new Trainer(1L,"Paul","paul@email.com","0678465732",94853432,32,8);

        when(trainerRepository.findById(1L)).thenReturn(Optional.ofNullable(trainer));

        TrainerDto savedTrainer = trainerService.getTrainerById(1L);

        Assertions.assertThat(savedTrainer).isNotNull();
    }

    @Test
    public void getAllTrainers(){

        when(trainerRepository.findAll())
                .thenReturn(List.of(
                        new Trainer(
                        1L,
                        "Paul",
                        "paul@email.com",
                        "0678465732",
                        94853432,
                        32,
                        8),
                        new Trainer(
                                2L,
                                "Gabriel",
                                "Gabriel@email.com",
                                "0636457323",
                                12345678,
                                21,
                                1)
                ));

        Assertions.assertThat(trainerService.getAllTrainers()).hasSize(2);

        List <TrainerDto> savedTrainers = trainerService.getAllTrainers();
        Assertions.assertThat(savedTrainers).isNotNull();

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
        assertThrows(TrainerNotFoundException.class, ()->{trainerService.updateTrainer(trainerDto,50L);});
        trainer = new Trainer(1L,"Paul","paul@email.com","0678465732",94853432,32,8);
        trainerDto = new TrainerDto(1L,"Paul","paul@email.com","0678465732",94853432,32,8);

        when(trainerRepository.findById(1L)).thenReturn(Optional.ofNullable(trainer));
        when(trainerRepository.save(Mockito.any(Trainer.class))).thenReturn(trainer);

        TrainerDto savedTrainer = trainerService.updateTrainer(trainerDto, 1L);
        Assertions.assertThat(savedTrainer).isNotNull();
    }

    @Test
    public void deleteTrainer(){
        assertThrows(TrainerNotFoundException.class, ()->{trainerService.deleteTrainer(50L);});
        trainer = new Trainer(1L,"Paul","paul@email.com","0678465732",94853432,32,8);
        when(trainerRepository.findById(1L)).thenReturn(Optional.ofNullable(trainer));
        assertAll(()-> trainerService.deleteTrainer(1L));
    }

}

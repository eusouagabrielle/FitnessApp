package com.example.fitnessapp.service;

import com.example.fitnessapp.dto.AthleteDto;
import com.example.fitnessapp.exception.AthleteNotFoundException;
import com.example.fitnessapp.model.Athlete;
import com.example.fitnessapp.repository.AthleteRepository;
import com.example.fitnessapp.service.impl.AthleteServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AthleteServiceTest {

    @Mock
    private AthleteRepository athleteRepository;
    @InjectMocks
    private AthleteServiceImpl athleteService;

    @Mock
    Athlete athlete;
    AthleteDto athleteDto;

    @Test
    public void getAthleteById(){
        assertThrows(AthleteNotFoundException.class, ()->{athleteService.getAthleteById(50L);});
        athlete = new Athlete(1L,"Tim","tim@gmail.com","0675843624",37,89,100);
        when(athleteRepository.findById(1L)).thenReturn(Optional.ofNullable(athlete));

        AthleteDto savedAthlete = athleteService.getAthleteById(1L);

        Assertions.assertThat(savedAthlete).isNotNull();
    }

    @Test
    public void getAthleteByName(){
        assertThrows(AthleteNotFoundException.class, ()->{athleteService.getAthleteByName("Gabrielle");});
        athlete = new Athlete(1L,"Gabrielle","gabrielle@gmail.com","0675843624",37,89,100);
        when(athleteRepository.findByName("Gabrielle")).thenReturn(Optional.ofNullable(athlete));

        AthleteDto savedAthlete = athleteService.getAthleteByName("Gabrielle");
        Assertions.assertThat(savedAthlete).isNotNull();
    }

    @Test
    public void getAllAthletesWithTrainersId(){

        when(athleteRepository.findByTrainerId(1L))
                .thenReturn(List.of(
                        new Athlete(
                                1L,
                                "Nick",
                                "Nick@email.com",
                                "0685749322",
                                25,
                                100,
                                80),
                        new Athlete(
                                2L,
                                "Juliana",
                                "juliana@email.com",
                                "0678564321",
                                30,
                                70,
                                65)
                ));
        Assertions.assertThat(athleteService.getAthletesByTrainersId(1L)).hasSize(2);

        List <AthleteDto> savedAthletes= athleteService.getAthletesByTrainersId(1L);
        Assertions.assertThat(savedAthletes).isNotNull();
    }

    @Test
    public void createAthlete(){
        athlete = new Athlete(1L,"Gabrielle","gabrielle@gmail.com","0675843624",24,89,100);
        athleteDto = new AthleteDto(1L,"Gabrielle","gabrielle@gmail.com","0675843624",24,89,100);

        when(athleteRepository.save(Mockito.any(Athlete.class))).thenReturn(athlete);
        AthleteDto savedAthlete = athleteService.createAthlete(athleteDto);
        Assertions.assertThat(savedAthlete).isNotNull();
    }

    @Test
    public void updateAthlete(){
        assertThrows(AthleteNotFoundException.class, ()->{athleteService.updateAthlete(athleteDto,50L);});
        athlete = new Athlete(1L,"Fernanda","Fernanda@gmail.com","0675647392",37,89,100);
        athleteDto = new AthleteDto(1L,"Fernanda","Fernanda@gmail.com","0675647392",37,89,100);

        when(athleteRepository.findById(1L)).thenReturn(Optional.ofNullable(athlete));
        when(athleteRepository.save(Mockito.any(Athlete.class))).thenReturn(athlete);

        AthleteDto savedAthlete = athleteService.updateAthlete(athleteDto, 1L);
        Assertions.assertThat(savedAthlete).isNotNull();
    }

    @Test
    public void deleteAthlete(){
        assertThrows(AthleteNotFoundException.class, ()->{athleteService.deleteAthlete(50L);});
        athlete = new Athlete(1L,"Fernanda","Fernanda@gmail.com","0675647392",37,89,100);
        when(athleteRepository.findById(1L)).thenReturn(Optional.ofNullable(athlete));
        assertAll(()-> athleteService.deleteAthlete(1L));
    }

}

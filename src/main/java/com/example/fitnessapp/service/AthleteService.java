package com.example.fitnessapp.service;



import com.example.fitnessapp.dto.AthleteDto;

import java.util.List;

public interface AthleteService {
    AthleteDto createAthlete(AthleteDto athleteDto);
    List<AthleteDto> getAthletesByTrainersId(Long id);
    AthleteDto getAthleteById(Long id);

    AthleteDto getAthleteByName(String name);

    AthleteDto updateAthlete(AthleteDto dto, Long id);

    void deleteAthlete(Long id);

}

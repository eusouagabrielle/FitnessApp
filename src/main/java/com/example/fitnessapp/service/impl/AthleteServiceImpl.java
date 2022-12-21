package com.example.fitnessapp.service.impl;


import com.example.fitnessapp.dto.AthleteDto;
import com.example.fitnessapp.exception.AthleteNotFoundException;
import com.example.fitnessapp.model.Athlete;
import com.example.fitnessapp.model.FileDocument;
import com.example.fitnessapp.repository.AthleteRepository;
import com.example.fitnessapp.repository.FileRepository;
import com.example.fitnessapp.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AthleteServiceImpl implements AthleteService {

    private AthleteRepository athleteRepository;
    private FileRepository document;

    @Autowired
    public AthleteServiceImpl(AthleteRepository athleteRepository, FileRepository document) {
        this.athleteRepository = athleteRepository;
        this.document = document;
    }

    @Override
    public List<AthleteDto> getAthletesByTrainersId(Long id) {
       List<Athlete> athletes = athleteRepository.findByTrainerId(id);
       return athletes.stream().map(athlete -> mapToDto(athlete)).collect(Collectors.toList());
    }

    @Override
    public AthleteDto getAthleteById(Long id) {

        Athlete athlete = athleteRepository.findById(id).orElseThrow(() ->
                new AthleteNotFoundException("Athlete could not be found"));
        return mapToDto(athlete);
    }

    @Override
    public AthleteDto getAthleteByName(String name) {
        Athlete athlete = athleteRepository.findByName(name).orElseThrow(()-> new AthleteNotFoundException("Athlete" +
                "could not be found"));
        return mapToDto(athlete);
    }

    @Override
    public AthleteDto createAthlete(AthleteDto dto) {
        Athlete athlete = new Athlete();
        athlete.setName(dto.getName());
        athlete.setEmail(dto.getEmail());
        athlete.setPhoneNumber(dto.getPhoneNumber());
        athlete.setAge(dto.getAge());
        athlete.setStartWeight(dto.getStartWeight());
        athlete.setTargetWeight(dto.getTargetWeight());

        Athlete newAthlete = athleteRepository.save(athlete);

        AthleteDto response = new AthleteDto();
        response.setId(newAthlete.getId());
        response.setName(newAthlete.getName());
        response.setEmail(newAthlete.getEmail());
        response.setPhoneNumber(newAthlete.getPhoneNumber());
        response.setAge(newAthlete.getAge());
        response.setStartWeight(newAthlete.getStartWeight());
        response.setTargetWeight(newAthlete.getTargetWeight());
        return response;
    }
    @Override
    public AthleteDto updateAthlete(AthleteDto dto, Long id) {
        Athlete athlete = athleteRepository.findById(id).orElseThrow(() ->
                new AthleteNotFoundException("Athlete could not be updated"));

        athlete.setName(dto.getName());
        athlete.setEmail(dto.getEmail());
        athlete.setPhoneNumber(dto.getPhoneNumber());
        athlete.setAge(dto.getAge());
        athlete.setStartWeight(dto.getStartWeight());
        athlete.setTargetWeight(dto.getTargetWeight());

        Athlete updatedAthlete = athleteRepository.save(athlete);
        return mapToDto(updatedAthlete);
    }

    @Override
    public void deleteAthlete(Long id) {
        Athlete athlete = athleteRepository.findById(id).orElseThrow(()->
                new AthleteNotFoundException("Athlete could not be delete"));
        athleteRepository.delete(athlete);
    }

    @Override
    public ResponseEntity<byte[]> fileDownload(String fileName, HttpServletRequest request) {
        FileDocument doc = document.findByFileName(fileName);
        MediaType contentType = MediaType.APPLICATION_PDF;

        String mimeType = request.getServletContext().getMimeType(doc.getFileName());
        return ResponseEntity.ok().contentType(contentType).header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;fileName=" + doc.getFileName()).body(doc.getDocFile());
    }

    private AthleteDto mapToDto (Athlete athlete){
        AthleteDto dto = new AthleteDto();
        dto.setId(athlete.getId());
        dto.setName(athlete.getName());
        dto.setEmail(athlete.getEmail());
        dto.setPhoneNumber(athlete.getPhoneNumber());
        dto.setAge(athlete.getAge());
        dto.setStartWeight(athlete.getStartWeight());
        dto.setTargetWeight(athlete.getTargetWeight());
        return dto;
    }

    private Athlete mapToEntity(AthleteDto dto){
        Athlete athlete = new Athlete();
        athlete.setName(dto.getName());
        athlete.setEmail(dto.getEmail());
        athlete.setPhoneNumber(dto.getPhoneNumber());
        athlete.setAge(dto.getAge());
        athlete.setStartWeight(dto.getStartWeight());
        athlete.setTargetWeight(dto.getTargetWeight());
        return athlete;
    }

}

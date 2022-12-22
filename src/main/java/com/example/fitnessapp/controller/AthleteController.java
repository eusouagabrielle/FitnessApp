package com.example.fitnessapp.controller;

import com.example.fitnessapp.dto.AthleteDto;
import com.example.fitnessapp.service.AthleteService;
import com.example.fitnessapp.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/workout")
public class AthleteController {

    private final AthleteService athleteService;
    private final FileService fileService;

    @Autowired
    public AthleteController(AthleteService athleteService, FileService fileService) {
        this.athleteService = athleteService;
        this.fileService = fileService;
    }

    @GetMapping("/athlete/{id}")
    @RolesAllowed({"ROLE_ATHLETE", "ROLE_TRAINER"})
    public ResponseEntity<AthleteDto> getAnAthlete(@PathVariable Long id){
       return ResponseEntity.ok(athleteService.getAthleteById(id));
    }

    @GetMapping("/athletes/{name}")
    @RolesAllowed({"ROLE_ATHLETE", "ROLE_TRAINER"})
    public ResponseEntity<AthleteDto> getAnAthleteByName(@PathVariable(value = "name")String name){
        return ResponseEntity.ok(athleteService.getAthleteByName(name));
    }

    @GetMapping("/{trainer_id}/athletes")
    @RolesAllowed({"ROLE_ATHLETE", "ROLE_TRAINER"})
    public List<AthleteDto> getAthletesByTrainerId(@PathVariable(value = "trainer_id") Long id){
        return athleteService.getAthletesByTrainersId(id);
    }

    @PostMapping("/athlete")
    public ResponseEntity<AthleteDto> createAthlete(@RequestBody AthleteDto dto){
        return new ResponseEntity<>(athleteService.createAthlete(dto), HttpStatus.CREATED);
    }

    @PutMapping("update/athlete/{id}")
    @RolesAllowed({"ROLE_ATHLETE"})
    public ResponseEntity<AthleteDto> updateAthlete(@RequestBody AthleteDto dto, @PathVariable("id") Long id){
        AthleteDto response = athleteService.updateAthlete(dto, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/athlete/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<String> deleteAthlete(@PathVariable("id") Long id){
        athleteService.deleteAthlete(id);
        return new ResponseEntity<>("Athlete delete", HttpStatus.OK);
    }

    @GetMapping("/downloads/{fileName}")
    @RolesAllowed({"ROLE_ATHLETE"})
    ResponseEntity<byte[]> downLoadProgressReport(@PathVariable String fileName, HttpServletRequest request) {

        return fileService.fileDownload(fileName, request);
    }

}

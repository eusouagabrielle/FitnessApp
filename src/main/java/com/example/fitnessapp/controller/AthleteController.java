package com.example.fitnessapp.controller;



import com.example.fitnessapp.dto.AthleteDto;
import com.example.fitnessapp.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/workout")
public class AthleteController {

    private final AthleteService athleteService;

    @Autowired
    public AthleteController(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    @GetMapping("/athlete/{id}")
    @RolesAllowed({"ROLE_ATHLETE", "ROLE_TRAINER"})
    public ResponseEntity<AthleteDto> getAnAthlete(@PathVariable Long id){
       return ResponseEntity.ok(athleteService.getAthleteById(id));
    }

    @GetMapping("/{trainerId}/athletes")
    @RolesAllowed({"ROLE_ATHLETE", "ROLE_TRAINER"})
    public List<AthleteDto> getAthletesByTrainerId(@PathVariable(value = "trainerId") Long id){
        return athleteService.getAthletesByTrainersId(id);
    }

    @PostMapping("/athlete")
    @RolesAllowed({"ROLE_ATHLETE"})
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
    @RolesAllowed({"ROLE_ATHLETE"})
    public ResponseEntity<String> deleteAthlete(@PathVariable("id") Long id){
        athleteService.deleteAthlete(id);
        return new ResponseEntity<>("Athlete delete", HttpStatus.OK);
    }
}

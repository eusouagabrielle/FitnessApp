package com.example.fitnessapp.controller;


import com.example.fitnessapp.dto.RoutineDto;
import com.example.fitnessapp.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/workout")
public class RoutineController {

    private final RoutineService routineService;

    @Autowired
    public RoutineController(RoutineService routineService) {
        this.routineService = routineService;
    }

    @GetMapping("/routines")
    @RolesAllowed({"ROLE_TRAINER", "ROLE_ATHLETE"})
    public ResponseEntity<List<RoutineDto>> getRoutines(){
        return new ResponseEntity<>(routineService.getAllRoutines(), HttpStatus.OK);
    }
    @GetMapping("/routine/calories")
    public ResponseEntity<Integer> getCalories(@PathVariable Long id){
        return ResponseEntity.ok(routineService.getRoutinesCalories(id));
    }

    @GetMapping("/routine/{id}")
    @RolesAllowed({"ROLE_TRAINER", "ROLE_ATHLETE"})
    public ResponseEntity<RoutineDto> getARoutine(@PathVariable Long id){
        return ResponseEntity.ok(routineService.getRoutineById(id));
    }

    @PostMapping("/routine")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<RoutineDto> createRoutine(@RequestBody RoutineDto dto){
        return new ResponseEntity<>(routineService.createRoutine(dto), HttpStatus.CREATED);
    }

    @PutMapping("/update/routine/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<RoutineDto> updateRoutine(@RequestBody RoutineDto dto, @PathVariable("id") Long id){
        RoutineDto response = routineService.updateRoutine(dto, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/routine/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<String> deleteRoutine(@PathVariable("id") Long id){
        routineService.deleteRoutine(id);
        return new ResponseEntity<>("Routine delete", HttpStatus.OK);
    }
}

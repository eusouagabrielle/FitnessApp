package com.example.fitnessapp.controller;


import com.example.fitnessapp.dto.RoutineDto;
import com.example.fitnessapp.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<RoutineDto>> getRoutines(){
        return new ResponseEntity<>(routineService.getAllRoutines(), HttpStatus.OK);
    }

    @GetMapping("/routine/{id}")
    public ResponseEntity<RoutineDto> getARoutine(@PathVariable Long id){
        return ResponseEntity.ok(routineService.getRoutineById(id));
    }

    @PostMapping("routine")
    public ResponseEntity<RoutineDto> createRoutine(@RequestBody RoutineDto dto){
        return new ResponseEntity<>(routineService.createRoutine(dto), HttpStatus.CREATED);
    }

    @PutMapping("/update/routine/{id}")
    public ResponseEntity<RoutineDto> updateRoutine(@RequestBody RoutineDto dto, @PathVariable("id") Long id){
        RoutineDto response = routineService.updateRoutine(dto, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/routine/{id}")
    public ResponseEntity<String> deleteRoutine(@PathVariable("id") Long id){
        routineService.deleteRoutine(id);
        return new ResponseEntity<>("Routine delete", HttpStatus.OK);
    }
}

package com.example.fitnessapp.controller;


import com.example.fitnessapp.dto.FileUploadResponse;
import com.example.fitnessapp.dto.TrainerDto;
import com.example.fitnessapp.model.FileDocument;
import com.example.fitnessapp.service.FileService;
import com.example.fitnessapp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/workout")
public class TrainerController {

    private final TrainerService trainerService;
    private final FileService fileService;

    @Autowired
    public TrainerController(TrainerService trainerService, FileService fileService) {
        this.trainerService = trainerService;
        this.fileService = fileService;
    }


    @GetMapping("/trainers")
    @RolesAllowed({"ROLE_TRAINER", "ROLE_ATHLETE"})
    public ResponseEntity<List<TrainerDto>> getTrainers(){
        return new ResponseEntity<>(trainerService.getAllTrainers(), HttpStatus.OK);
    }

    @GetMapping("/trainer/{id}")
    @RolesAllowed({"ROLE_TRAINER", "ROLE_ATHLETE"})
    public ResponseEntity<TrainerDto> getATrainer(@PathVariable Long id){
        return ResponseEntity.ok(trainerService.getTrainerById(id));
    }

    @PostMapping("/trainer")
    public ResponseEntity<TrainerDto> createTrainer(@RequestBody TrainerDto dto){
        return new ResponseEntity<>(trainerService.createTrainer(dto), HttpStatus.CREATED);
    }

    @PutMapping("/update/trainer/{id}")
    @RolesAllowed({"ROLE_TRAINER"})
    public ResponseEntity<TrainerDto> updateTrainer(@RequestBody TrainerDto dto, @PathVariable("id") Long id){
        TrainerDto response = trainerService.updateTrainer(dto, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/trainer/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<String> deleteTrainer(@PathVariable("id") Long id){
        trainerService.deleteTrainer(id);
        return new ResponseEntity<>("Trainer delete", HttpStatus.OK);
    }

    @PostMapping("/fileUpload")
    @RolesAllowed({"ROLE_TRAINER"})
    public FileUploadResponse fileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        FileDocument fileDocument = fileService.uploadFileDocument(file);
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloads/")
                .path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();

        String contentType = file.getContentType();

        return new FileUploadResponse(fileDocument.getFileName(), url, contentType );
    }
}

package com.example.fitnessapp.service;



import com.example.fitnessapp.dto.TrainerDto;
import com.example.fitnessapp.model.FileDocument;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TrainerService {
    TrainerDto createTrainer(TrainerDto dto);
    List<TrainerDto> getAllTrainers();
    TrainerDto getTrainerById(Long id);

    TrainerDto updateTrainer(TrainerDto dto, Long id);

    void deleteTrainer(Long id);

    FileDocument uploadFileDocument(MultipartFile file) throws IOException;
}

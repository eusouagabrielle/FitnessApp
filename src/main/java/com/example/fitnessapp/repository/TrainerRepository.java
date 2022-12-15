package com.example.fitnessapp.repository;


import com.example.fitnessapp.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
}

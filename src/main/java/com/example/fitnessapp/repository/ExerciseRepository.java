package com.example.fitnessapp.repository;


import com.example.fitnessapp.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
}

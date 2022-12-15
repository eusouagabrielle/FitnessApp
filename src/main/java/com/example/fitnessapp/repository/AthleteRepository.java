package com.example.fitnessapp.repository;


import com.example.fitnessapp.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    List<Athlete> findByTrainerId(Long trainerId);
    Optional<Athlete> findByName(String name);
}

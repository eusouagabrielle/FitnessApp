package com.example.fitnessapp.repository;


import com.example.fitnessapp.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineRepository extends JpaRepository<Routine, Long> {
}

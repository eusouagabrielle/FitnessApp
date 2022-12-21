package com.example.fitnessapp.repository;


import com.example.fitnessapp.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RoutineRepository extends JpaRepository<Routine, Long> {
    @Query(value = "select sum(e.calorie) from exercises as e inner join routine_exercises re on e.id = re.exercise_id " +
            "where re.routine_id = ?1", nativeQuery = true)
    Integer findRoutineCalories(Long id);
}


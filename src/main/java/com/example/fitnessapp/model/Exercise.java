package com.example.fitnessapp.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String targetMuscle;
    @Column(nullable = false)
    private int sets;
    @Column(nullable = false)
    private int repetitions;
    @Column(nullable = false)
    private int weight;
    @Column
    private int calorie;
    @JsonIgnore
    @ManyToMany(mappedBy = "exercises",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Routine> routines;
}

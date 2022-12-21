package com.example.fitnessapp.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "trainers")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @Column(nullable = false, unique = true)
    private Integer kvkNumber;
    @Column(nullable = false)
    private Integer age;
    private Integer yearsOfExperience;
    @OneToMany(mappedBy = "trainer",
            cascade = CascadeType.ALL)
    private List<Athlete> athletes;

    public Trainer(Long id,
                   String name,
                   String email,
                   String phoneNumber,
                   Integer kvkNumber,
                   Integer age,
                   Integer yearsOfExperience) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.kvkNumber = kvkNumber;
        this.age = age;
        this.yearsOfExperience = yearsOfExperience;
    }
}
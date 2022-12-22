package com.example.fitnessapp.dto;

import com.example.fitnessapp.model.Athlete;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class TrainerDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Integer kvkNumber;
    private Integer age;
    private Integer yearsOfExperience;
    private List<Athlete> athletes = new ArrayList<Athlete>();

    public TrainerDto(Long id,
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

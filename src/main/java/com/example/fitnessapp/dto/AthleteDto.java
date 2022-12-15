package com.example.fitnessapp.dto;


import lombok.Data;

@Data
public class AthleteDto {

    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private int age;

    private double startWeight;

    private double targetWeight;
}

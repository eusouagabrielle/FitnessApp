package com.example.fitnessapp.dto;

import com.example.fitnessapp.model.Authority;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank
    public String username;
    @NotBlank
    public String password;
    @JsonSerialize
    public Set<Authority> authorities;
}

package com.example.fitnessapp.service;

import com.example.fitnessapp.dto.UserDto;
import com.example.fitnessapp.model.Authority;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<UserDto> getUsers();
    UserDto getUser(String username);
    String createUser(UserDto userDto);
    void updateUser(String username, UserDto dto);
    Set<Authority> getAuthorities(String username);
    void addAuthority(String username, String authority);
    void removeAuthority(String username, String authority);
    void deleteUser(String username);
}

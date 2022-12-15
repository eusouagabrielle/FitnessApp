package com.example.fitnessapp.repository;

import com.example.fitnessapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}

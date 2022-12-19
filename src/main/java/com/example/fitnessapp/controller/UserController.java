package com.example.fitnessapp.controller;

import com.example.fitnessapp.dto.UserDto;
import com.example.fitnessapp.exception.AuthorityException;
import com.example.fitnessapp.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workout")
public class UserController {

    private final UserServiceImpl userServiceImpl;


    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping(value = "")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List <UserDto> userDtos = userServiceImpl.getUsers();
        return ResponseEntity.ok().body(userDtos);
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDto> getUserByID(@PathVariable("username") String username){
        UserDto optionalUser = userServiceImpl.getUser(username);

        return ResponseEntity.ok().body(optionalUser);
    }

    @PostMapping(value = "/users")
    public ResponseEntity <UserDto> creatUser (@RequestBody UserDto user){

        String newUsername = userServiceImpl.createUser(user);
        userServiceImpl.addAuthority(newUsername, "ROLE_DEFAULT");

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/update/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username, @RequestBody UserDto dto) {

        userServiceImpl.updateUser(username, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/{username}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userServiceImpl.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/authorities/{username}")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(userServiceImpl.getAuthorities(username));
    }

    @PostMapping(value = "/authorities/{username}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            userServiceImpl.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        }
        catch (AuthorityException ex) {
            throw new AuthorityException("Unable to add authorities");
        }
    }

    @DeleteMapping(value = "/deleteAuthorities/{username}/{authority}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        userServiceImpl.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }
}

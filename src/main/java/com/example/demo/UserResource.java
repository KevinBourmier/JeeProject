package com.example.demo;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.stream.Location;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }



    // Get DTO Users
    @GetMapping
    public List<UserDTO> getUsersDTO() {
        List<User> users = userService.getUsers();
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    // Get special User by UserName
    @GetMapping(path = "/{userName}")
    public ResponseEntity<?> getUsers(@PathVariable("userName") String userName) {
        return userService.getUsers()
                .stream()
                .filter(user -> userName.equals(user.getFirstName()))
                .findFirst()
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    @PostMapping
    public ResponseEntity createUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {
        User storedUser = userService.createUser(user.getFirstName());
        user.setId(UUID.randomUUID().toString());

        String uri = uriComponentsBuilder.path("/users/{userId}").buildAndExpand(storedUser.getId()).toUriString();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, uri )
                .build();
    }

    private UserDTO toDto(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        return userDTO;
    }

}

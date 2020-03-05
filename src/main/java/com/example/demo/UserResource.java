package com.example.demo;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.stream.Location;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }


    // Get DTO Users
    /* @GetMapping
    public List<UserDTO> getUsersDTO() {
        return userService.getUsers();
    }
     */

    // Get special User
    /*
    @GetMapping
    public ResponseEntity<?> getUsers(@PathVariable("userId") String userId) {
        return userService.getUsers()
                .stream()
                .filter(user -> userId.equals(user.getId()))
                .findFirst()
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build())
    }
     */

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {
        User storedUser = userService.createUser(user.getFirstName());

        String uri = uriComponentsBuilder.path("/users/{userId}").buildAndExpand(storedUser.getId()).toUriString();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, uri )
                .build();
    }

}

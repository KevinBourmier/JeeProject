package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {
    private final UserStore userStore;
    private final UserConfig userConfig;

    @Autowired
    public UserService(UserStore userStore, UserConfig userConfig){
        this.userStore = userStore;
        this.userConfig = userConfig;
    }

    public User createUser(String firstName){

        if(userStore.size() + 1 > userConfig.getMaxUsers()){
            throw new IllegalStateException("Could not add more users");
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setId(UUID.randomUUID().toString());
        userStore.addUser(user);
        System.out.println("Max users is : " + userConfig.getMaxUsers());

        return user;
    }

    public List<User> getUsers() {
        return userStore.getDb();
    }
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserStore userStore;
    private final UserConfig userConfig;

    @Autowired
    public UserService(UserStore userStore, UserConfig userConfig){
        this.userStore = userStore;
        this.userConfig = userConfig;
    }

    public void createUser(String firstName){

        if(userStore.size() + 1 > userConfig.getMaxUsers()){
            throw new IllegalStateException("Could not add more users");
        }

        User user = new User("Kevin");
        userStore.addUser(user);
        System.out.println("Max users is : " + userConfig.getMaxUsers());

    }

    public List<User> getUsers() {
        return userStore.getDb();
    }
}

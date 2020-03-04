package com.example.demo;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class UserStore {
    List<User> db;

    public List<User> getDb() {
        return db;
    }

    private static UserStore instance = new UserStore();

    public static UserStore getInstance(){
        return instance;
    }

    public UserStore(){
        this.db = new ArrayList<>();
    }

    public void addUser(User user){
        db.add(user);
    }

    public int size(){
        return db.size();
    }
}

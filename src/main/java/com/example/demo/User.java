package com.example.demo;

import lombok.Data;

@Data
public class User {

    String firstName;

    public User(String f){
        this.firstName = f;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}

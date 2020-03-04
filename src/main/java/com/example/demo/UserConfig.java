package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class UserConfig {
    private int maxUsers;

    public UserConfig(int maxUsers){
        this.maxUsers = maxUsers;
    }

}

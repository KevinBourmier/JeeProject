package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Setter
@Getter
@ConfigurationProperties(prefix = "app")
public class UserConfig {

    private int maxUsers = 1;

    /* public UserConfig(@Value("${app.max.users:10}") int maxUsers){
        this.maxUsers = maxUsers;
    }
     */

}

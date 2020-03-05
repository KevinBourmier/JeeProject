package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class UserServiceTest {
    private UserService userService;
    private UserStore userStore;
    private UserConfig userConfig;

    @BeforeEach
    void setup(){
        userStore = Mockito.mock(UserStore.class);
        userConfig = Mockito.mock(UserConfig.class);
        userService = new UserService(userStore, userConfig);
    }

    @Test
    void should_create_a_new_(){
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        BDDMockito.given(userStore.size()).willReturn(0);
        BDDMockito.given(userConfig.getMaxUsers()).willReturn(1);

        userService.createUser("one");

        BDDMockito.verify(userStore).addUser(userCaptor.capture());
        assertThat(userCaptor.getValue().getFirstName()).isEqualTo("one");

    }

    @Test
    void should_create_a_new(){

        userService.createUser("one");
        List<User> users = userService.getUsers();

        assertThat(users)
                .hasSize(1)
                .extracting(User::getFirstName)
                .contains("one");

    }

    @Test
    void should_throw_exception_when_max_user_reached(){
        userConfig = new UserConfig();
        userService = new UserService(userStore, userConfig);

        try {
            userService.createUser("one");
            fail("Should have thrown IllegalStateException");
        } catch (IllegalStateException ex){
            assertThat(ex.getMessage()).isEqualTo("");
        }
    }
}

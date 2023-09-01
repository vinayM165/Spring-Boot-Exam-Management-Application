package com.example.quizspringboot.Controller;

import com.example.quizspringboot.Controllers.UserController;
import com.example.quizspringboot.Model.User;
import com.example.quizspringboot.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestUserController {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetAllUsers() {
        List<User> users = List.of(new User(), new User());
        when(userService.getAllUsers()).thenReturn(users);

        List<User> response = userController.getAllUsers();

        assertThat(response).isEqualTo(users);
    }

    @Test
    public void testGetUserById() {
        Long id = 1L;
        User user = new User();
        when(userService.getUserById(id)).thenReturn(new ResponseEntity<>(user, HttpStatus.OK));

        ResponseEntity<User> response = userController.getUserById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(user);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        when(userService.createUser(user)).thenReturn(user);

        User response = userController.createUser(user);

        assertThat(response).isEqualTo(user);
    }

    @Test
    public void testUpdateUser() {
        Long id = 1L;
        User user = new User();
        when(userService.updateUser(id, user)).thenReturn(new ResponseEntity<>(user, HttpStatus.OK));

        ResponseEntity<User> response = userController.updateUser(id, user);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(user);
    }

    @Test
    public void testDeleteUser() {
        Long id = 1L;
        when(userService.deleteUser(id)).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        ResponseEntity<Void> response = userController.deleteUser(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}

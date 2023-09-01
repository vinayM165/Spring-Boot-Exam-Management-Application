package com.example.quizspringboot.Service;

import com.example.quizspringboot.DAO.UserRepository;
import com.example.quizspringboot.Model.User;
import com.example.quizspringboot.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestUserService {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setUser_id(1L);
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");
        user1.setPassword("password1");

        User user2 = new User();
        user2.setUser_id(2L);
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");
        user2.setPassword("password2");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        verify(userRepository, times(1)).findAll();
        assertThat(result).isEqualTo(users);
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setUser_id(1L);
        user.setUsername("user");
        user.setEmail("user@example.com");
        user.setPassword("password");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        var result = userService.getUserById(1L);

        verify(userRepository, times(1)).findById(1L);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(user);
    }

    @Test
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<User> result = userService.getUserById(1L);

        verify(userRepository, times(1)).findById(1L);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("user");
        user.setEmail("user@example.com");
        user.setPassword("password");

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User savedUser = invocation.getArgument(0);
            savedUser.setUser_id(1L);
            return savedUser;
        });

        User result = userService.createUser(user);

        verify(userRepository, times(1)).save(any(User.class));
        assertThat(result.getUser_id()).isEqualTo(1L);
        assertThat(result.getUsername()).isEqualTo(user.getUsername());
        assertThat(result.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testUpdateUser() {
        User existingUser = new User();
        existingUser.setUser_id(1L);
        existingUser.setUsername("existingUser");
        existingUser.setEmail("existingUser@example.com");
        existingUser.setPassword("password");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        User updatedUser = new User();
        updatedUser.setUsername("updatedUser");
        updatedUser.setEmail("updatedUser@example.com");

        ResponseEntity<User> result = userService.updateUser(1L, updatedUser);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(existingUser);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getUsername()).isEqualTo(updatedUser.getUsername());
        assertThat(result.getBody().getEmail()).isEqualTo(updatedUser.getEmail());
    }

    @Test
    public void testUpdateUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User updatedUser = new User();
        updatedUser.setUsername("updatedUser");
        updatedUser.setEmail("updatedUser@example.com");

        ResponseEntity<User> result = userService.updateUser(1L, updatedUser);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(0)).save(any(User.class));
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUser_id(1L);
        user.setUsername("user");
        user.setEmail("user@example.com");
        user.setPassword("password");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<Void> result = userService.deleteUser(1L);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(user);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    public void testDeleteUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> result = userService.deleteUser(1L);

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(0)).delete(any(User.class));
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    }



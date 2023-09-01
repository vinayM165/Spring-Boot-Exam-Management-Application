package com.example.quizspringboot.repository;

import com.example.quizspringboot.DAO.UserRepository;
import com.example.quizspringboot.Model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestUserRepo {

    @Autowired
    UserRepository userRepository;
    @Test
    void TestCreateUser(){
        Set<User.Role> roles = EnumSet.of(User.Role.ADMIN, User.Role.USER);
        User user = new User(1L, "username","password",true,"123@email.com",roles);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getUser_id()).isNotNull();
        assertThat(savedUser.getUsername()).isEqualTo("username");
        assertThat(savedUser.getPassword()).isEqualTo("password");
        assertThat(savedUser.getEmail()).isEqualTo("123@email.com");
    }
    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setUsername("John Doe");
        user.setEmail("john.doe@example.com");

        User savedUser = userRepository.save(user);
        Long userId = savedUser.getUser_id();

        userRepository.deleteById(userId);

        User deletedUser = userRepository.findById(userId).orElse(null);

        assertThat(deletedUser).isNull();
    }
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setUsername("John Doe");
        user.setEmail("john.doe@example.com");

        User savedUser = userRepository.save(user);
        Long userId = savedUser.getUser_id();

        User foundUser = userRepository.findById(userId).orElse(null);
        assert foundUser != null;
        foundUser.setUsername("Jane Dip");
        userRepository.save(foundUser);

        User updatedUser = userRepository.findById(userId).orElse(null);

        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getUser_id()).isEqualTo(userId);
        assertThat(updatedUser.getUsername()).isEqualTo("Jane Dip");
        assertThat(updatedUser.getEmail()).isEqualTo("john.doe@example.com");
    }
    @Test
    public void testFindUserById() {
        User user = new User();
        user.setUsername("John Doe");
        user.setEmail("john.doe@example.com");

        User savedUser = userRepository.save(user);
        Long userId = savedUser.getUser_id();

        User foundUser = userRepository.findById(userId).orElse(null);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUser_id()).isEqualTo(userId);
        assertThat(foundUser.getUsername()).isEqualTo("John Doe");
        assertThat(foundUser.getEmail()).isEqualTo("john.doe@example.com");
    }



}

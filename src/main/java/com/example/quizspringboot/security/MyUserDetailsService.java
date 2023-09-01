package com.example.quizspringboot.security;

import com.example.quizspringboot.DAO.UserRepository;
import com.example.quizspringboot.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository repo;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            Optional<User> user = repo.findByUsernameOrEmail(s,s);
            return new MyUserDetails(user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + user)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new MyUserDetails();
    }
}

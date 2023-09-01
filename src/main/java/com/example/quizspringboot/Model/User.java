package com.example.quizspringboot.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean active;
    private String email;
    // user roles
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    public enum Role {
        USER, ADMIN
    }
    // if user is admin
    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }
}

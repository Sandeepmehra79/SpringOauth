package com.userService.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Entity(name = "USER")
@Getter
@Setter
@Component
public class User extends BaseModel{
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "username", unique = true, columnDefinition = "varchar(255)")
    private String username;
    @Column(name = "password", unique = false, columnDefinition = "varchar(255)")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Column(name = "roles")
    private List<Role> roles;
}

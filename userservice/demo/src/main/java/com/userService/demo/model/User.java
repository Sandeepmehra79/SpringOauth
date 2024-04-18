package com.userService.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "USER")
@Getter
@Setter
@Component
@JsonDeserialize(as = User.class)
public class User extends BaseModel{
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "username", unique = true, columnDefinition = "varchar(255)")
    private String username;
    @Column(name = "password", unique = false, columnDefinition = "varchar(255)")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
}

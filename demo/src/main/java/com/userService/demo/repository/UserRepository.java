package com.userService.demo.repository;

import com.userService.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    public User save(User user);
    public Optional<User> findById(UUID id);

    public Optional<User> findByEmail (String email);

    public List<User> findAll();

    public Optional<User> findByUsername(String username);

}

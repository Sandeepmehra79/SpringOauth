package com.userService.demo.repository;
import com.userService.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    public Role save(Role role);
    public Optional<Role> findById(UUID id);
    public Optional<Role> findRoleByName(String role);
}

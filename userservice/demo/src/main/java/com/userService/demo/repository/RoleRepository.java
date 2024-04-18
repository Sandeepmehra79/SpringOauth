package com.userService.demo.repository;
import com.userService.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    public Role save(Role role);
    public Optional<Role> findById(UUID id);
    public Optional<Role> findRoleByName(String role);

    List<Role> findAllByIdIn(List<Long> roleIds);
}

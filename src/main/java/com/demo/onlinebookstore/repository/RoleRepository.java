package com.demo.onlinebookstore.repository;

import com.demo.onlinebookstore.entity.Role;
import com.demo.onlinebookstore.util.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}

package com.example.bankcards.repository;


import com.example.bankcards.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    boolean existsByName(String name);
    Role findByName(String name);
}

package com.example.bankcards.repository;

import com.example.bankcards.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepo extends JpaRepository<UserLogin, Long> {
    UserLogin findByLogin(String login);
}

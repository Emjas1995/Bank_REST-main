package com.example.bankcards.service;

import com.example.bankcards.entity.User;
import com.example.bankcards.entity.UserLogin;
import com.example.bankcards.repository.UserLoginRepo;
import com.example.bankcards.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserLoginRepo userLoginRepo;
    private final UserRepo userRepo;

    CustomUserDetailsService(UserLoginRepo userLoginRepo, UserRepo userRepo){
        this.userLoginRepo = userLoginRepo;
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin login = userLoginRepo.findByLogin(username);
        User user = login.getUser();
        return org.springframework.security.core.userdetails.User.builder()
                .username(login.getLogin())
                .password(login.getPassword())
                .roles(user.getRole().getName()) // Роль из сущности User
                .build();
    }
}

package com.example.bankcards.controller;

import com.example.bankcards.dto.AuthRequest;
import com.example.bankcards.entity.User;
import com.example.bankcards.entity.UserLogin;
import com.example.bankcards.repository.UserLoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserLoginRepo userRepository;

    @Autowired
    AuthController(AuthenticationManager authenticationManager, UserLoginRepo userRepository){
        this.authenticationManager =authenticationManager;
        this.userRepository = userRepository;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        // Аутентификация
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
        );
        UserLogin login = userRepository.findByLogin(request.getLogin());
        User user = login.getUser();
        // Получение роли пользователя

        // Редирект или возврат данных
        String redirectUrl = switch (user.getRole().getName()) {
            case "ADMIN" -> "/api/admin/dashboard";
            case "USER" -> "/api/user/profile";
            default -> "/";
        };

        return ResponseEntity.ok().header("Location", redirectUrl).build();
    }
}

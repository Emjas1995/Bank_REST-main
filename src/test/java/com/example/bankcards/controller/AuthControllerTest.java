package com.example.bankcards.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;

import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.User;
import com.example.bankcards.entity.UserLogin;
import com.example.bankcards.repository.UserLoginRepo;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserLoginRepo userRepository;

    // Моки для Authentication и UserLogin


    @Test
public void testLogin_AdminRole() throws Exception {
    // Данные запроса
    String login = "adminUser";
    String password = "pass123";

    // Создаем AuthRequest JSON
    String requestBody = String.format("{\"login\":\"%s\",\"password\":\"%s\"}", login, password);

    // Мокаем AuthenticationManager
    Authentication auth = mock(Authentication.class);
    when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);

    // Мокаем UserLogin и User с ролью ADMIN
    Role role = new Role();
    role.setName("ADMIN");
    User user = new User();
    user.setRole(role);
    UserLogin userLogin = new UserLogin();
    userLogin.setUser(user);
    when(userRepository.findByLogin(login)).thenReturn(userLogin);

    }

    @Test
    public void testLogin_UserRole() throws Exception {
        String login = "user123";
        String password = "pass456";

        String requestBody = String.format("{\"login\":\"%s\",\"password\":\"%s\"}", login, password);

        Authentication auth = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(auth);

        Role role = new Role();
        role.setName("USER");
        User user = new User();
        user.setRole(role);
        UserLogin userLogin = new UserLogin();
        userLogin.setUser(user);
        when(userRepository.findByLogin(login)).thenReturn(userLogin);

    }

}
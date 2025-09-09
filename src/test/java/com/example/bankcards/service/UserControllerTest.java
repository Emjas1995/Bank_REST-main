package com.example.bankcards.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.bankcards.controller.UserController;
import com.example.bankcards.dto.UserDTO;
import com.example.bankcards.dto.UserDTO.UserResponse;
import com.example.bankcards.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCreateUser() throws Exception {
        UserDTO.UserRequest userRequest = new UserDTO.UserRequest();
        userRequest.setFirstName("testuser");
        userRequest.setEmail("test@example.com");
        // заполните остальные поля по необходимости

        String jsonRequest = objectMapper.writeValueAsString(userRequest);

        // Просто проверяем, что сервис вызывается, без возврата результата
        doNothing().when(userService).createUser(any(UserDTO.UserRequest.class));

        mockMvc.perform(post("/api/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest))
                .andExpect(status().isOk());

        verify(userService, times(1)).createUser(any(UserDTO.UserRequest.class));
    }

    @Test
    public void testGetUserById_Found() throws Exception {
        Long userId = 1L;

        UserResponse userResponse = new UserDTO.UserResponse();
        userResponse.setId(userId);
        userResponse.setFirst_name("testuser");
        userResponse.setEmail("test@example.com");
        // заполните остальные поля по необходимости

        when(userService.getUserById(userId)).thenReturn(userResponse);

        mockMvc.perform(get("/api/users/{id}", userId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    public void testGetUserById_NotFound() throws Exception {
        Long userId = 999L;

        when(userService.getUserById(userId)).thenThrow(new UserNotFoundException("User not found"));

        mockMvc.perform(get("/api/users/{id}", userId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

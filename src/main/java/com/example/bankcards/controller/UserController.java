package com.example.bankcards.controller;

import com.example.bankcards.dto.UserDTO;
import com.example.bankcards.entity.User;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Пользователи")
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(
            summary = "Создать пользователя",
            description = "Создает нового пользователя с указанными данными",
    responses = {
        @ApiResponse(responseCode = "200", description = "Пользователь добавлен")
    }
    )
    @PostMapping("/")
    public void createUser(@RequestBody UserDTO.UserRequest userRequest) {
        userService.createUser(userRequest);
    }

    @Operation(
            summary = "Получить пользователя по id",
            description = "Возвращает пользователя с указанным id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь найден"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
            }
    )
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(
            summary = "Получить всех пользователей",
            description = "Возвращает список всех пользователей в системе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователи найдены"),
                    @ApiResponse(responseCode = "404", description = "Пользователи не найдены")
            }
    )
    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(
            summary = "Обновить пользователя с указанным id",
            description = "Обновляет пользователя с указанным id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь обновлен"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
            }
    )
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody UserDTO.UserRequest userRequest) {
        return userService.updateUser(id, userRequest);
    }

    @Operation(
            summary = "Удалить пользователя с указанным id",
            description = "Удаляет пользователя с указанным id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Пользователь удален"),
                    @ApiResponse(responseCode = "404", description = "Пользователь не найден")
            }
    )
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Обработка исключения
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFound(UserNotFoundException ex) {
        return ex.getMessage();
    }
}

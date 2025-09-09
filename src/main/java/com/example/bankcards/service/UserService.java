package com.example.bankcards.service;


import com.example.bankcards.dto.UserDTO;
import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.User;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.repository.RoleRepo;
import com.example.bankcards.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepository, RoleRepo roleRepo) {
        this.userRepo = userRepository;
        this.roleRepo = roleRepo;
    }

    @Transactional
    public User createUser(UserDTO.UserRequest userRequest) {
        Role user_role = roleRepo.findByName(userRequest.getRoleName());
        User newUser = new User();
        newUser.setFirst_name(userRequest.getFirstName());
        newUser.setSecond_name(userRequest.getSecondName());
        newUser.setMiddle_name(userRequest.getMiddleName());
        newUser.setRole(user_role);
        return userRepo.save(newUser);
    }

    // Read (by ID)
    @Transactional()
    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    // Read (all)
    @Transactional()
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Update
    @Transactional
    public User updateUser(Long id, UserDTO.UserRequest userRequest) {
        User existingUser = getUserById(id);
        existingUser.setFirst_name(userRequest.getFirstName());
        existingUser.setSecond_name(userRequest.getSecondName());
        existingUser.setMiddle_name(userRequest.getMiddleName());

        // Обновление роли (если нужно)
        if (userRequest.getRoleName() != null) {
            Role role = roleRepo.findByName(userRequest.getRoleName());
            existingUser.setRole(role);
        }

        return userRepo.save(existingUser);
    }

    // Delete
    @Transactional
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}

package com.example.bankcards.service;


import com.example.bankcards.dto.RoleDTO;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.Role;
import com.example.bankcards.exception.CardNotFoundException;
import com.example.bankcards.exception.RoleAlreadyExistsException;

import com.example.bankcards.repository.RoleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    private final RoleRepo roleRepository;


    @Autowired
    public RoleService(RoleRepo roleRepository) {
        this.roleRepository = roleRepository;
    }

    // Создание роли
    @Transactional
    public void createRole(RoleDTO.RoleRequest request) {
        if (roleRepository.existsByName(request.getName())) {
            throw new RoleAlreadyExistsException("Роль '" + request.getName() + "' уже существует");
        }

        Role role = new Role();
        role.setName(request.getName());
        roleRepository.save(role);
    }

    @Transactional
    public void updateRole(Long id, RoleDTO.RoleRequest roleRequest) throws RoleNotFoundException {
        Role existing_role = getRoleById(id);
        existing_role.setName(roleRequest.getName());
        roleRepository.save(existing_role);
    }

    // Получение роли по ID
    @Transactional()
    public Role getRoleById(Long id) throws RoleNotFoundException {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Роль не найдена"));
    }

    // Получение всех ролей
    @Transactional()
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    // Удаление роли
    @Transactional
    public void deleteRole(Long id) throws RoleNotFoundException {
        if (!roleRepository.existsById(id)) {
            throw new RoleNotFoundException("Роль не найдена");
        }
        roleRepository.deleteById(id);
    }
}

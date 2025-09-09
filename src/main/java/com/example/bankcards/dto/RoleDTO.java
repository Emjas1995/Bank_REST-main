package com.example.bankcards.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
public class RoleDTO {
    @NoArgsConstructor
    public static class RoleRequest {
        @NotBlank(message = "Название роли обязательно")
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @NoArgsConstructor
    public static class RoleResponse {
        private Long id;
        private String name;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

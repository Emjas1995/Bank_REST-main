package com.example.bankcards.dto;

import jakarta.validation.constraints.NotNull;

public class CardStatusDTO {

    public static class Request{
        @NotNull
        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public class Response{
        @NotNull
        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

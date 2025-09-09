package com.example.bankcards.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


public class CardDTO {




    @NoArgsConstructor
    public static class CardRequest {

        @Positive(message = "Баланс должен быть положительным")
        private int balance;
        @NotNull(message = "Дата")
        private LocalDate date_out;
        @NotNull(message = "Cтатус карты")
        private String status;
        @NotNull(message = "user_id")
        private  Long user_id;


        public Long getUser_id() {
            return user_id;
        }

        public void setUser_id(Long user_id) {
            this.user_id = user_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public LocalDate getDate_out() {
            return date_out;
        }

        public void setDate_out(LocalDate date_out) {
            this.date_out = date_out;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int initialBalance) {
            this.balance = initialBalance;
        }


    }


}

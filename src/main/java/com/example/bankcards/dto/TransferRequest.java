package com.example.bankcards.dto;

import java.math.BigDecimal;

public class TransferRequest {
    private Long fromCardId; // ID карты-отправителя
    private Long toCardId;   // ID карты-получателя
    private int amount; // Сумма перевода

    public Long getFromCardId() {
        return fromCardId;
    }

    public void setFromCardId(Long fromCardId) {
        this.fromCardId = fromCardId;
    }

    public Long getToCardId() {
        return toCardId;
    }

    public void setToCardId(Long toCardId) {
        this.toCardId = toCardId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
// Геттеры и сеттеры
}

package com.example.bankcards.entity;


import jakarta.persistence.*;

import java.time.LocalDate;



@Entity @Table(name = "card")
public class Card implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number", unique = true, nullable = false)
    private String number;
    @Column(name = "dateOut", nullable = false)
    private LocalDate date_out;
    @Column(name = "balance", nullable = false)
    private int balance;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private CardStatus status;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;



    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    public CardStatus getStatus() {
        return status;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

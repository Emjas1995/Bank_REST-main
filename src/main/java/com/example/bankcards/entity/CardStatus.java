package com.example.bankcards.entity;


import jakarta.persistence.*;

@Entity @Table(name = "cardStatus")
public class CardStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "statusName", nullable = false, unique = true)
    private String name;



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

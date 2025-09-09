package com.example.bankcards.repository;


import com.example.bankcards.entity.CardStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardStatusRepo extends JpaRepository<CardStatus, Long> {
    CardStatus findByName(String name);
}

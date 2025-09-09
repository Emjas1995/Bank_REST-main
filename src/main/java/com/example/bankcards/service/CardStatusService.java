package com.example.bankcards.service;


import com.example.bankcards.dto.CardStatusDTO;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.CardStatus;
import com.example.bankcards.exception.StatusNotFoundException;
import com.example.bankcards.repository.CardStatusRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardStatusService {
    private final CardStatusRepo cardStatusRepo;


    @Autowired
    CardStatusService(CardStatusRepo cardStatusRepo){
        this.cardStatusRepo = cardStatusRepo;
    }

    @Transactional
    public void createStatus(CardStatusDTO.Request request){
        CardStatus newStatus = new CardStatus();
        newStatus.setName(request.getName());
        cardStatusRepo.save(newStatus);
    }

    @Transactional
    public CardStatus getStatusById(Long id){
        return cardStatusRepo.findById(id)
                .orElseThrow(() -> new StatusNotFoundException("Status not found with id: " + id));
    }

    @Transactional
    public List<CardStatus> getAllStatuses(){
        return cardStatusRepo.findAll();
    }

    @Transactional
    public void updateCardStatus(Long id, CardStatusDTO.Request request){
        CardStatus existingStatus = getStatusById(id);
        existingStatus.setName(request.getName());
        cardStatusRepo.save(existingStatus);
    }

    @Transactional
    public void deleteCardStatus(Long id){
        cardStatusRepo.deleteById(id);
    }
}

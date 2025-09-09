package com.example.bankcards.controller;



import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.dto.CardStatusDTO;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.CardStatus;
import com.example.bankcards.exception.CardNotFoundException;
import com.example.bankcards.exception.StatusNotFoundException;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.service.CardStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "api/cardStatuses")
public class CardStatusController {

    private final CardStatusService cardStatusService;

    @Autowired
    CardStatusController(CardStatusService cardStatusService){
        this.cardStatusService = cardStatusService;
    }

    @Operation(
            summary = "Создать статус",
            description = "Создает новый статус с указанными данными",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Статус добавлен")
            }
    )
    @PostMapping("/")
    public void createStatus(
            @Valid @RequestBody CardStatusDTO.Request cardRequest
    ) {
        cardStatusService.createStatus(cardRequest);
    }

    @Operation(
            summary = "Получить статус по id",
            description = "Возвращает статус с указанным id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Статус найден"),
                    @ApiResponse(responseCode = "404", description = "Статус не найден")
            }
    )
    @GetMapping("/{id}")
    public CardStatus getStatusById(@PathVariable Long id) {
        return cardStatusService.getStatusById(id);
    }

    @Operation(
            summary = "Получить все статусы",
            description = "Возвращает список статусов в системе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Статусы найдены"),
                    @ApiResponse(responseCode = "404", description = "Статусы не найдены")
            }
    )
    @GetMapping("/")
    public List<CardStatus> getAllStatuses() {
        return cardStatusService.getAllStatuses();
    }

    @Operation(
            summary = "Обновить статус с указанным id",
            description = "Обновляет статус с указанным id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Статус обновлен"),
                    @ApiResponse(responseCode = "404", description = "Статус не найден")
            }
    )
    @PutMapping("/{id}")
    public void updateCard(@PathVariable Long id, @RequestBody CardStatusDTO.Request cardRequest) {
       cardStatusService.updateCardStatus(id, cardRequest);
    }

    @Operation(
            summary = "Удалить статус с указанным id",
            description = "Удаляет статус с указанным id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Статус удален"),
                    @ApiResponse(responseCode = "404", description = "Статус не найден")
            }
    )
    @DeleteMapping("/{id}")
    public void deleteCardStatus(@PathVariable Long id) {
        cardStatusService.deleteCardStatus(id);
    }

    // Обработка исключения
    @ExceptionHandler(StatusNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCardNotFound(StatusNotFoundException ex) {
        return ex.getMessage();
    }
}

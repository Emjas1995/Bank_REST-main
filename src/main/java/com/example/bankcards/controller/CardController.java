package com.example.bankcards.controller;

import com.example.bankcards.dto.CardDTO;
import com.example.bankcards.dto.TransferRequest;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import com.example.bankcards.exception.CardNotFoundException;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @Operation(
            summary = "Перевод между своими картами",
            description = "Переводит деньги с одной карты на другую",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Перевод прошел"),
                    @ApiResponse(responseCode = "404", description = "Карта не найдена"),
                    @ApiResponse(responseCode = "500", description = "Недостаточно средств")
            }
    )
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferRequest request) {
        cardService.transferBetweenCards(request);
        return ResponseEntity.ok("Перевод выполнен успешно");
    }

    @Operation(
            summary = "Создать карту",
            description = "Создает новую карту с указанными данными",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Карта добавлена")
            }
    )
    @PostMapping("/")
    public void createCard(
            @Valid @RequestBody CardDTO.CardRequest cardRequest
    ) {
        Card createdCard = cardService.createCard(cardRequest);
    }

    @Operation(
            summary = "Получить карту по id",
            description = "Возвращает карту с указанным id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Карта найдена"),
                    @ApiResponse(responseCode = "404", description = "Карта не найдена")
            }
    )
    @GetMapping("/{id}")
    public Card getCardById(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @Operation(
            summary = "Получить все карты",
            description = "Возвращает список карт пользователей в системе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Карты найдены"),
                    @ApiResponse(responseCode = "404", description = "Карты не найдены")
            }
    )
    @GetMapping("/")
    public List<Card> getAllCards() throws CloneNotSupportedException {
        return cardService.getAllCards();
    }

    @Operation(
            summary = "Обновить карту с указанным id",
            description = "Обновляет карту с указанным id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Карта обновлена"),
                    @ApiResponse(responseCode = "404", description = "Карта не найдена")
            }
    )
    @PutMapping("/{id}")
    public Card updateCard(@PathVariable Long id, @RequestBody CardDTO.CardRequest cardRequest) {
        return cardService.updateCard(id, cardRequest);
    }

    @Operation(
            summary = "Удалить карту с указанным id",
            description = "Удаляет карту с указанным id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Карта удалена"),
                    @ApiResponse(responseCode = "404", description = "Карта не найдена")
            }
    )
    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
    }

    // Обработка исключения
    @ExceptionHandler(CardNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCardNotFound(CardNotFoundException ex) {
        return ex.getMessage();
    }
}

package com.josh.aitutor.controllers;

import com.josh.aitutor.models.Deck;
import com.josh.aitutor.services.DeckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Signals to call this method at HTTP endpoint and return JSON
@RestController
@RequestMapping("/api/decks")
public class DeckController {
    private final DeckService deckService;

    public DeckController(DeckService deckService){
        this.deckService = deckService;
    }

    @GetMapping("/{userId}/study/{topic}")
    public ResponseEntity<Deck> getDeck(@PathVariable Long userId, @RequestParam String topic){
        Deck deck = deckService.getDeckForTopic(userId, topic);
        return ResponseEntity.ok(deck);
    }

    @PostMapping("/{userId}/generate")
    public ResponseEntity<Deck> postGeneratedDeck(@PathVariable Long userId, @RequestParam String topic){
        Deck deckToStudy = deckService.getDeckForTopic(userId, topic);
        return ResponseEntity.ok(deckToStudy);
    }
}

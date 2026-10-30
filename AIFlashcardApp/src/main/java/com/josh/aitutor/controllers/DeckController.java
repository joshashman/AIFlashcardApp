package com.josh.aitutor.controllers;

import com.josh.aitutor.models.Deck;
import com.josh.aitutor.services.DeckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/decks")
public class DeckController {
    private final DeckService deckService;

    public DeckController(DeckService deckService){
        this.deckService = deckService;
    }

    @GetMapping("/{topic}")
    public Deck getDeck(@PathVariable String topic){
        Deck requestedDeck = deckService.getDeck(topic);
        return requestedDeck;
    }
}

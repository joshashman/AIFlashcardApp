package com.josh.aitutor.controllers;

import com.josh.aitutor.models.Deck;
import com.josh.aitutor.services.DeckService;
import org.springframework.web.bind.annotation.*;

// Signals to call this method at HTTP endpoint and return JSON to display
@RestController
@RequestMapping("/api/decks")
public class DeckController {
    private final DeckService deckService;

    public DeckController(DeckService deckService){
        this.deckService = deckService;
    }

    @GetMapping("/{topic}")
    public Deck getDeck(@PathVariable String topic, @RequestParam Long userId){ // change requestParam once security is set up
        Deck requestedDeck = deckService.getDeckForTopic(userId, topic);
        return requestedDeck;
    }
}

package com.josh.aitutor.services;

import com.josh.aitutor.models.Deck;
import com.josh.aitutor.models.Flashcard;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeckService {
    private Map<String, Deck> database;

    private final AISupplier aiSupplier;

    public DeckService(AISupplier aiSupplier) {
        this.database = new HashMap<>();
        this.aiSupplier = aiSupplier;
    }

    public Deck getDeck(String topic) {
        if (database.containsKey(topic)) {
            System.out.println("Found " + topic + " in database! Loading...");
            return database.get(topic);
        } else {
            System.out.println("No " + topic + " deck found. Calling AI Supplier...");

            List<Flashcard> newCards = aiSupplier.generateCards(topic);

            Deck newDeck = new Deck(topic, newCards);

            database.put(topic, newDeck);

            return newDeck;
        }
    }
}

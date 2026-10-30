package com.josh.aitutor.services;

import com.josh.aitutor.models.Deck;
import com.josh.aitutor.models.Flashcard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckService {
    private Map<String, Deck> database = new HashMap<>();

    private AISupplier aiSupplier = new AISupplier();

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

package com.josh.aitutor.services;

import com.josh.aitutor.models.Flashcard;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class AISupplier {

    public List<Flashcard> generateCards(String topic){
        List<Flashcard> newFlashcards = new ArrayList<>();

        // AI will generate new flashcards based on the topic.

        Flashcard fakeCard = new Flashcard("Äpple", "Apple", "Jag äter ett äpple.");
        newFlashcards.add(fakeCard);

        return newFlashcards;
    }

}

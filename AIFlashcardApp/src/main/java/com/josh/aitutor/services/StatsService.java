package com.josh.aitutor.services;

import com.josh.aitutor.models.Deck;
import com.josh.aitutor.models.Flashcard;
import com.josh.aitutor.models.User;
import com.josh.aitutor.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.josh.aitutor.dtos.StatsDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {

    private final UserRepository userRepository;

    public StatsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public StatsDTO getUserStats(Long userId){
        // get user from DB
        User user = userRepository.findById(userId).orElseThrow();

        int totalDecks = user.getStudiedDecks().size();
        int totalMistakes = user.getMistakeCards().size();

        // count studied cards
        int totalCards = 0;
        for (Deck deck : user.getStudiedDecks()){
            totalCards += deck.getFlashcardList().size();
        }

        // calculate user accuracy
        int accuracy = 0;
        if (totalCards > 0) {
            int correctCards = totalCards - totalMistakes;
            accuracy = (int) (((double) correctCards / totalCards) * 100);
        }

        return new StatsDTO(totalDecks, totalCards, totalMistakes, accuracy);
    }

    @Transactional
    public Deck getMistakeDeck(Long userId){
        // get user from DB
        User user = userRepository.findById(userId).orElseThrow();

        // create deck to hold mistake cards
        Deck virtualMistakeDeck = new Deck();
        virtualMistakeDeck.setTopicName("Mistakes Review");

        // load mistake cards to new deck
        List<Flashcard> mistakes = new ArrayList<>(user.getMistakeCards());
        virtualMistakeDeck.setFlashcards(mistakes);

        return virtualMistakeDeck;
    }
}

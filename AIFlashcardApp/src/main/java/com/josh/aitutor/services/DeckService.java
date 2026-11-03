package com.josh.aitutor.services;

import com.josh.aitutor.models.Deck;
import com.josh.aitutor.models.Flashcard;
import com.josh.aitutor.models.User;
import com.josh.aitutor.repositories.DeckRepository;
import com.josh.aitutor.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Spring boot will create one DeckService and use it elsewhere when called
@Service
public class DeckService {
    private final DeckRepository deckRepository;
    private final UserRepository userRepository;
    private final AISupplier aiSupplier;

    // Dependency injection constructor
    public DeckService(DeckRepository deckRepository, UserRepository userRepository, AISupplier aiSupplier) {
        this.deckRepository = deckRepository;
        this.userRepository = userRepository;
        this.aiSupplier = aiSupplier;
    }

    // TO DO !!!!! UNFINISHED
    // Finds required deck by topic, if exists will load, if not will call AISupplier to create a deck
    public Deck getDeck(Long userId, String topic) {
        User user = userRepository.findById(userId).orElseThrow();

        List<Long> exclusionList = new ArrayList<>();
        for (Deck deck : user.getStudiedDecks()) {
            exclusionList.add(deck.getId());
        }

        Optional<Deck> possibleDeck = deckRepository.findFirstByTopicAndIdNotInOrderByIdAsc(topic, exclusionList);

        if (possibleDeck.isPresent()) {
            return possibleDeck.get();
        } else {
            List<String> excludedWords = new ArrayList<>();
            for (Deck deck : user.getStudiedDecks()){
                for (Flashcard card : deck.getFlashcardList()){
                    excludedWords.add(card.getSwedishWord());
                }
            }

            String promptExclusionString = String.join(", ", excludedWords);
            return createNewDeckUsingAI(topic, promptExclusionString);
        }
    }
}

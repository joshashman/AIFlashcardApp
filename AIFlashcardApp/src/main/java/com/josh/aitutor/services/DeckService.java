package com.josh.aitutor.services;

import com.josh.aitutor.models.Deck;
import com.josh.aitutor.models.Flashcard;
import com.josh.aitutor.models.User;
import com.josh.aitutor.repositories.DeckRepository;
import com.josh.aitutor.repositories.UserRepository;
import jakarta.transaction.Transactional;
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

    public Deck getDeckForTopic(Long userId, String topic){
        User user = userRepository.findById(userId).orElseThrow();

        List<Long> excludedDecks = new ArrayList<>();
        for (Deck deck : user.getStudiedDecks()){
            excludedDecks.add(deck.getId());
        }

        Optional<Deck> possibleDeck = deckRepository.findFirstByTopicAndIdNotInOrderByIdAsc(topic, excludedDecks);

        if (possibleDeck.isEmpty()){
            return generateDeckForTopic(userId, topic);
        }
        return possibleDeck.get();
    }

    @Transactional
    public Deck generateDeckForTopic(Long userId, String topic) {
        if (!topic.matches("^[a-zA-Z\\s]{2,30}$")) { // make sure entered topic is only made up of lower or uppercase English characters between 2 and 30 characters long.
            throw new IllegalArgumentException("Topic must be a short English word or phrase.");
        }

        String[] words = topic.trim().split("\\s+"); // trim accidental spaces and split into words
        StringBuilder formattedTopicBuilder = new StringBuilder();

        for (String word : words) {
            if (!word.isEmpty()) {
                String firstLetter = word.substring(0, 1).toUpperCase(); // capitalise first letter
                String restOfWord = word.substring(1).toLowerCase(); // rest of word lower case

                formattedTopicBuilder.append(firstLetter).append(restOfWord).append(" "); // add to topic builder with a space
            }
        }

        String standardisedTopic = formattedTopicBuilder.toString().trim(); // trim off final trailing space

        String promptExclusionString = buildTopicExclusionString(standardisedTopic);

        List<Flashcard> newCards = aiSupplier.generateFlashcards(standardisedTopic, promptExclusionString);

        Deck newDeck = new Deck();
        newDeck.setTopicName(standardisedTopic);

        for (Flashcard card : newCards) {
            card.setDeck(newDeck);
        }
        newDeck.setFlashcards(newCards);

        User creator = userRepository.findById(userId).orElseThrow();
        newDeck.setCreator(creator);

        return deckRepository.save(newDeck);
    }

    // Build exclusion containing only words of the topic
    private String buildTopicExclusionString(String topic){
        List<String> excludedWords = deckRepository.findExistingWordsByTopic(topic);
        return String.join(", ", excludedWords);
    }
}

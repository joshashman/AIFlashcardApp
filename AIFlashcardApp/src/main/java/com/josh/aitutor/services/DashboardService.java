package com.josh.aitutor.services;

import com.josh.aitutor.dtos.TopicSummaryDTO;
import com.josh.aitutor.models.Deck;
import com.josh.aitutor.models.User;
import com.josh.aitutor.repositories.DeckRepository;
import com.josh.aitutor.repositories.UserRepository;

public class DashboardService {

    private final DeckRepository deckRepository;
    private final UserRepository userRepository;

    // DI constructor
    public DashboardService(DeckRepository deckRepository, UserRepository userRepository) {
        this.deckRepository = deckRepository;
        this.userRepository = userRepository;
    }

    public TopicSummaryDTO getTopicSummary(Long userId, String topic){
        User user = userRepository.findById(userId).orElseThrow();

        int totalDecks = deckRepository.countByTopic(topic);

        int studiedCount = 0;
        for (Deck deck : user.getStudiedDecks()){
            if (deck.getTopic().equals(topic)){
                studiedCount++;
            }
        }

        int decksLeftToStudy = totalDecks - studiedCount;

        return new TopicSummaryDTO(topic, totalDecks, decksLeftToStudy);
    }
}

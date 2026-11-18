package com.josh.aitutor.services;

import com.josh.aitutor.dtos.MistakeGraduationDTO;
import com.josh.aitutor.dtos.SessionCompletionDTO;
import com.josh.aitutor.models.Deck;
import com.josh.aitutor.models.Flashcard;
import com.josh.aitutor.models.User;
import com.josh.aitutor.repositories.DeckRepository;
import com.josh.aitutor.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudySessionService {

    private final UserRepository userRepository;
    private final DeckRepository deckRepository;

    // DI constructor
    public StudySessionService(UserRepository userRepository, DeckRepository deckRepository) {
        this.userRepository = userRepository;
        this.deckRepository = deckRepository;
    }

    @Transactional // this ensures if any line fails, none of the changes happen
    public void completedStudySession (Long userId, SessionCompletionDTO dto) {
        // Get user and deck from db
        User user = userRepository.findById(userId).orElseThrow();
        Deck completedDeck = deckRepository.findById(dto.deckId()).orElseThrow();

        // Mark deck as studied
        if (!user.getStudiedDecks().contains(completedDeck)){ // just makes sure it isn't saved twice
            user.getStudiedDecks().add(completedDeck);
        }

        // Find incorrect cards from the deck just studied
        if (dto.incorrectCardIds() != null && !dto.incorrectCardIds().isEmpty()){ // first check if there were any incorrect cards
            List<Flashcard> incorrectCards = new ArrayList<>();

            for (Flashcard card : completedDeck.getFlashcardList()){
                if (dto.incorrectCardIds().contains(card.getId())){
                    incorrectCards.add(card);
                }
            }

            user.getMistakeCards().addAll(incorrectCards);
        }

        userRepository.save(user); // save changes to DB
    }

    @Transactional
    public void removeCorrectedMistakes(Long userId, MistakeGraduationDTO dto) {
        // get user from DB
        User user = userRepository.findById(userId).orElseThrow();

        if (dto.correctedCardIds() != null && !dto.correctedCardIds().isEmpty()) {
            List<Flashcard> cardsToRemove = new ArrayList<>();

            // loop through mistakes pile
            for (Flashcard card : user.getMistakeCards()){
                // if it has been corrected, add to removal list
                if (dto.correctedCardIds().contains(card.getId())){
                    cardsToRemove.add(card);
                }
            }

            // remove corrected cards
            user.getMistakeCards().removeAll(cardsToRemove);

            // save to user
            userRepository.save(user);
        }
    }
}

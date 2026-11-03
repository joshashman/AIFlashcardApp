package com.josh.aitutor.dtos;

import java.util.List;

public record DeckResponseDTO(
        Long id,
        String topic,
        int flashcardCount,
        List<FlashcardDTO> flashcards
) {}


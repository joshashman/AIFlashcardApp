package com.josh.aitutor.dtos;

import java.util.List;

public record SessionCompletionDTO(
        Long deckId,
        List<Long> incorrectCardIds
) {
}

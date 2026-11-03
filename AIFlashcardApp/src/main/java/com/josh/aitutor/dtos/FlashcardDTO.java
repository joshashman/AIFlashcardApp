package com.josh.aitutor.dtos;

public record FlashcardDTO(
   Long id,
   String swedishWord,
   String partOfSpeech,
   String englishWord
) {}

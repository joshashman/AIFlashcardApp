package com.josh.aitutor.dtos;

public record StatsDTO(
        int totalDecksStudied,
        int totalCardsStudied,
        int totalMistakeCards,
        int overallAccuracyPercentage
) {}

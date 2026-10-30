package com.josh.aitutor.models;

import java.util.List;

public class Deck {
    private String topicName;
    private List<Flashcard> flashcardList;

    public Deck(String topicName, List<Flashcard> flashcardList) {
        this.topicName = topicName;
        this.flashcardList = flashcardList;
    }

    public String getTopicName(){
        return topicName;
    }
    public List<Flashcard> getFlashcardList(){
        return flashcardList;
    }
}

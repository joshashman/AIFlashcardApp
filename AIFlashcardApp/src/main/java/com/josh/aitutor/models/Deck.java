package com.josh.aitutor.models;

import java.util.List;

public class Deck {
    private String topicName;
    private List<Flashcard> flashcardList;

    // constructor
    public Deck(String topicName, List<Flashcard> flashcardList) {
        this.topicName = topicName;
        this.flashcardList = flashcardList;
    }

    // getters
    public String getTopicName() {
        return topicName;
    }

    public List<Flashcard> getFlashcardList() {
        return flashcardList;
    }

    // setters
    public void setTopicName(String newName){
        this.topicName = newName;
    }

}

package com.josh.aitutor.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Deck {

    // Auto generated PK id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private boolean isDefault;

    // Database link handled by deck variable in flashcard table.
    // Cascade binds the card to the deck, and auto inserts them.
    // If a flashcard is deleted from Deck Java list, it is auto deleted from the database.
    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flashcard> flashcardList;

    // constructor
    public Deck(String topic, List<Flashcard> flashcardList) {
        this.topic = topic;
        this.flashcardList = flashcardList;
    }

    // getters
    public Long getId() { return id; }

    public String getTopic() {
        return topic;
    }

    public List<Flashcard> getFlashcardList() {
        return flashcardList;
    }

    public boolean getIsDefault() { return isDefault; }

    // setters
    public void setTopicName(String newName){
        this.topic = newName;
    }

    public void setIsDefault(boolean newDefault) { this.isDefault = newDefault; }

}

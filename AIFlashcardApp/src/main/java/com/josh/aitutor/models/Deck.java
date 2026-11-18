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

    // empty no-args constructor
    public Deck(){

    }

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    // getters
    public Long getId() { return id; }

    public String getTopic() {
        return topic;
    }

    public List<Flashcard> getFlashcardList() {
        return flashcardList;
    }

    public User getCreator() { return creator;}

    // setters
    public void setTopicName(String newName){
        this.topic = newName;
    }

    public void setFlashcards(List<Flashcard> newCards){
        this.flashcardList = newCards;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}

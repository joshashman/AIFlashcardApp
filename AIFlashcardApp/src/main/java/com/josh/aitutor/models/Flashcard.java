package com.josh.aitutor.models;

import jakarta.persistence.*;

@Entity
public class Flashcard {

    // Auto generated PK id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String swedishWord;
    private String englishWord;
    private String sampleSentence;

    // Establishes child relationship
    // Each flashcard has a deck_id FK for where it belongs
    @ManyToOne
    @JoinColumn(name = "deck_id")
    private Deck deck;

    // constructor
    public Flashcard(String swedishWord, String englishWord, String sampleSentence) {
        this.swedishWord = swedishWord;
        this.englishWord = englishWord;
        this.sampleSentence = sampleSentence;
    }

    // empty no args constructor
    public Flashcard(){

    }

    // getters
    public String getSwedishWord(){
        return swedishWord;
    };

    public String getEnglishWord(){
        return englishWord;
    };

    public String getSampleSentence(){
        return sampleSentence;
    };

    public Long getId() { return id; }

    public Deck getDeck(){ return this.deck;}

    // setters
    public void setSwedishWord(String newWord){
        this.swedishWord = newWord;
    }

    public void setEnglishWord(String newWord){
        this.englishWord = newWord;
    }

    public void setSampleSentence(String newSentence){
        this.sampleSentence = newSentence;
    }

    public void setDeck(Deck deck){ this.deck = deck;}
}

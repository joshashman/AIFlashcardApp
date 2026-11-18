package com.josh.aitutor.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    // constructor
    public User(String email){
        this.email = email;
    }

    // empty no args constructor
    public User(){

    }

    // Establishes many to many db relationship
    // joinColumns represents FK for this model - user
    // inverseJoinColumns represents FK from target class - deck

    @ManyToMany
    @JoinTable(
            name = "user_decks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "deck_id")
    )
    private Set<Deck> studiedDecks = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_mistake_cards",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "flashcard_id")
    )
    private List<Flashcard> mistakeCards = new ArrayList<>();

    @OneToMany(mappedBy = "creator")
    private List<Deck> createdDecks = new ArrayList<>();

    // getters
    public String getEmail(){
        return email;
    }

    public Set<Deck> getStudiedDecks(){
        return studiedDecks;
    }

    public List<Flashcard> getMistakeCards() { return mistakeCards; }

    public List<Deck> getCreatedDecks() {return createdDecks;}

    // setters
    public void setEmail(String email){
        this.email = email;
    }

    public void setCreatedDecks(List<Deck> createdDecks) {
        this.createdDecks = createdDecks;
    }
}

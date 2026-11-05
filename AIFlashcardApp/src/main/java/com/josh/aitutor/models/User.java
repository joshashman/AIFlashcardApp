package com.josh.aitutor.models;

import jakarta.persistence.*;

import java.util.HashSet;
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

    // getters
    public String getEmail(){
        return email;
    }

    public Set<Deck> getStudiedDecks(){
        return studiedDecks;
    }
}

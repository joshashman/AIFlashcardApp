package com.josh.aitutor.exceptions;

public class DeckNotFoundException extends RuntimeException {
    public DeckNotFoundException(Long id){
        super("Deck with ID " + id + " not found.");
    }
}

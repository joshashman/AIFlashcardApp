package com.josh.aitutor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiFlashcardApp {
    public static void main(String[] args) {
        SpringApplication.run(AiFlashcardApp.class, args);


        /* earlier test

        DeckService tutorSystem = new DeckService();

        Deck firstAttempt = tutorSystem.getDeck("Food");
        printDeckContents(firstAttempt);

        Deck secondAttempt = tutorSystem.getDeck("Food");
        printDeckContents(secondAttempt);

        Deck customAttempt = tutorSystem.getDeck("Bouldering");
        printDeckContents(customAttempt);
    }

        private static void printDeckContents(Deck deck){
        System.out.println("Successfully loaded deck: " + deck.getTopicName());

        for (Flashcard card : deck.getFlashcardList()){
            System.out.println("Card: " + card.getSwedishWord() + " " + card.getEnglishWord());
        } */
    }
}
package com.josh.aitutor.models;

public class Flashcard {

    private String swedishWord;
    private String englishWord;
    private String sampleSentence;

    public Flashcard(String swedishWord, String englishWord, String sampleSentence) {
        this.swedishWord = swedishWord;
        this.englishWord = englishWord;
        this.sampleSentence = sampleSentence;
    }

    public String getSwedishWord(){
        return swedishWord;
    };

    public String getEnglishWord(){
        return englishWord;
    };

    public String getSampleSentence(){
        return sampleSentence;
    };
}

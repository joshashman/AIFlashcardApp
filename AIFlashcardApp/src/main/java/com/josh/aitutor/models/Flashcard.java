package com.josh.aitutor.models;

public class Flashcard {

    private String swedishWord;
    private String englishWord;
    private String sampleSentence;

    // constructor
    public Flashcard(String swedishWord, String englishWord, String sampleSentence) {
        this.swedishWord = swedishWord;
        this.englishWord = englishWord;
        this.sampleSentence = sampleSentence;
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
}

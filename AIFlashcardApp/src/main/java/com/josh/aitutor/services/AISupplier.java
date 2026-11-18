package com.josh.aitutor.services;

import com.josh.aitutor.models.Flashcard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

// Spring boot will create only one supplier and use it if called elsewhere
@Service
public class AISupplier {

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public AISupplier(){
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public List<Flashcard> generateFlashcards(String topic, String exclusionString){
        String prompt = String.format(
                "You are a strict Swedish language tutor for a user who is studying A1 Swedish. The user has requested 10 flashcards for the topic: '%s'. " +
                "Rule 1: If the topic if graphic, violent, sexually explicit, highly offensive, or nonsense gibberish, you MUST return this exact JSON: {\"error\": \"inappropriate_topic\"}. " +
                "Rule 2: You MUST NOT generate flashcards for any of the following excluded words: [&s]. " +
                "Rule 3: If the topic is safe, return exact JSON {\"flashcards\": [{\"english\": \"word\", \"swedish\": \"word\"}]} " +
                "Do not include markdown formatting or any other text.", topic, exclusionString
        );

        Map<String, Object> requestBody = buildGeminiRequest(prompt);

        try {
            String fullUrl = apiUrl + apiKey;
            String responseJson = restTemplate.postForObject(fullUrl, requestBody, String.class);

            return parseGeminiResponse(responseJson);

        } catch (Exception e) {
            throw new RuntimeException("Failed to communicate with AI: " + e.getMessage());
        }
    }

    //helper methods
    private Map<String, Object> buildGeminiRequest(String prompt){
        Map<String, Object> textPart = Map.of("text", prompt);
        Map<String, Object> partsNode = Map.of("parts", List.of(textPart));

        Map<String, Object> generationConfig = Map.of("responseMimeType", "application/json");

        return Map.of(
                "contents", List.of(partsNode),
                "generationConfig", generationConfig
        );
    }

    private List<Flashcard> parseGeminiResponse(String rawResponse) throws Exception {
        JsonNode rootNode = objectMapper.readTree(rawResponse);
        String aiGeneratedText = rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();

        JsonNode myJson = objectMapper.readTree(aiGeneratedText);

        if (myJson.has("error")){
            throw new IllegalArgumentException("The AI rejected this topic for being inappropriate or nonsense.");
        }

        List<Flashcard> generatedCards = new ArrayList<>();
        JsonNode flashcardsArray = myJson.path("flashcards");

        for (JsonNode node : flashcardsArray){
            Flashcard card = new Flashcard();

            card.setEnglishWord(node.path("english").asText());
            card.setSwedishWord(node.path("swedish").asText());
            generatedCards.add(card);
        }

        return generatedCards;
    }
}

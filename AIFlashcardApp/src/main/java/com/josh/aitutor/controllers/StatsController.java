package com.josh.aitutor.controllers;

import com.josh.aitutor.dtos.StatsDTO;
import com.josh.aitutor.models.Deck;
import com.josh.aitutor.services.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    // DI controller
    public StatsController(StatsService statsService){
        this.statsService = statsService;
    }

    // GET : http://localhost:8080/api/stats/1
    @GetMapping("/{userId}")
    public ResponseEntity<StatsDTO> getUserStats(@PathVariable Long userId) {
        StatsDTO stats = statsService.getUserStats(userId);

        // return the dto as a 200 ok JSON response
        return ResponseEntity.ok(stats);
    }

    // GET: http://localhost:8080/api/stats/1/mistake-deck
    @GetMapping("/{userId}/mistake-deck")
    public ResponseEntity<Deck> getMistakeDeck(@PathVariable Long userId){
        Deck mistakeDeck = statsService.getMistakeDeck(userId);
        return ResponseEntity.ok(mistakeDeck);
    }
}

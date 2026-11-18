package com.josh.aitutor.controllers;

import com.josh.aitutor.dtos.MistakeGraduationDTO;
import com.josh.aitutor.dtos.SessionCompletionDTO;
import com.josh.aitutor.services.StudySessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
public class StudySessionController {

    private final StudySessionService studySessionService;

    public StudySessionController(StudySessionService studySessionService) {
        this.studySessionService = studySessionService;
    }

    @PostMapping("/{userId}/complete")
    public ResponseEntity<Void> postCompletedSession(@PathVariable Long userId, @RequestBody SessionCompletionDTO dto){
        studySessionService.completedStudySession(userId, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{userId}/graduate-mistakes")
    public ResponseEntity<Void> postGraduatedMistakes(@PathVariable Long userId, @RequestBody MistakeGraduationDTO dto){
        studySessionService.removeCorrectedMistakes(userId, dto);
        return ResponseEntity.ok().build();
    }
}
package com.josh.aitutor.dtos;

import java.util.List;

public record MistakeGraduationDTO(
        List<Long> correctedCardIds
) {
}

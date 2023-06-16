package com.shahian.allergytracker.model.dto;

import lombok.Data;

@Data
public class SubstituteAllergyRelationDTO {
    private Long substituteId;
    private String substituteName;
    private Long allergyId;
    private String allergyName;
}

package com.shahian.allergytracker.model.dto;

import lombok.Data;

@Data
public class FoodAllergyRelationDTO {
    private Long foodId;
    private String foodName;
    private Long allergyId;
    private String allergyName;
}

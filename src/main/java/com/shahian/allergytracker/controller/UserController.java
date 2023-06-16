package com.shahian.allergytracker.controller;

import com.shahian.allergytracker.model.dto.FoodAllergyRelationDTO;
import com.shahian.allergytracker.model.dto.SubstituteAllergyRelationDTO;
import com.shahian.allergytracker.model.entity.Allergy;
import com.shahian.allergytracker.model.entity.Food;
import com.shahian.allergytracker.model.entity.Substitute;
import com.shahian.allergytracker.model.entity.User;
import com.shahian.allergytracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/v1/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    @GetMapping("/v1/user")
    public ResponseEntity<?> getUserById(@RequestParam Long userId) {
         User   user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/v1/foodAllergy")
    public ResponseEntity<List<FoodAllergyRelationDTO>> getFoodAllergyRelation() {
        List<FoodAllergyRelationDTO> relations = userService.getFoodAllergyRelations();
        return ResponseEntity.ok(relations);
    }
    @GetMapping("/v1/substituteAllergy")
    public ResponseEntity<List<SubstituteAllergyRelationDTO>> getSubstituteAllergyRelation() {
        List<SubstituteAllergyRelationDTO> relations = userService.getSubstituteRelations();
        return ResponseEntity.ok(relations);
    }
    @GetMapping("/v1/foodInquiryUser")
    public ResponseEntity<?> userFoodInquiry(@RequestParam Long userId) {

        List<Allergy> allergicFoods = userService.getAllergies(userId);
        List<Substitute> suggestedSubstitutes = userService.suggestSubstitutes(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("allergicFoods", allergicFoods);
        response.put("suggestedSubstitutes", suggestedSubstitutes);
        return ResponseEntity.ok(response);

    }

}
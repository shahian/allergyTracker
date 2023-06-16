package com.shahian.allergytracker.service;

import com.shahian.allergytracker.exception.UserNotFoundException;
import com.shahian.allergytracker.model.dto.FoodAllergyRelationDTO;
import com.shahian.allergytracker.model.dto.SubstituteAllergyRelationDTO;
import com.shahian.allergytracker.model.entity.Allergy;
import com.shahian.allergytracker.model.entity.Food;
import com.shahian.allergytracker.model.entity.Substitute;
import com.shahian.allergytracker.model.entity.User;
import com.shahian.allergytracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AllergyRepository allergyRepository;
    private final FoodRepository foodRepository;
    private final SubstituteRepository substituteRepository;

    @Autowired
    public UserService(UserRepository userRepository, AllergyRepository allergyRepository, FoodRepository foodRepository, SubstituteRepository substituteRepository) {
        this.userRepository = userRepository;
        this.allergyRepository = allergyRepository;
        this.foodRepository = foodRepository;
        this.substituteRepository = substituteRepository;
    }
    public List<Allergy> getAllergies(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        List<Allergy> userAllergies = user.getAllergies();
        return userAllergies;
    }

    public List<Substitute> suggestSubstitutes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
        List<Allergy> userAllergies = user.getAllergies();
        List<Substitute> suggestedSubstitutes = new ArrayList<>();
        for (Allergy allergy : userAllergies) {
            List<Substitute> substitutes = substituteRepository.findAllByAllergies(allergy);
            suggestedSubstitutes.addAll(substitutes);
        }
        return suggestedSubstitutes;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    public List<FoodAllergyRelationDTO> getFoodAllergyRelations() {
        List<FoodAllergyRelationDTO> relations = new ArrayList<>();

        List<Food> foods = foodRepository.findAll();
        for (Food food : foods) {
            for (Allergy allergy : food.getAllergies()) {
                FoodAllergyRelationDTO relation = new FoodAllergyRelationDTO();
                relation.setFoodId(food.getId());
                relation.setFoodName(food.getFoodName());
                relation.setAllergyId(allergy.getId());
                relation.setAllergyName(allergy.getAllergyName());
                relations.add(relation);
            }
        }

        return relations;
    }
    public List<SubstituteAllergyRelationDTO> getSubstituteRelations() {
        List<SubstituteAllergyRelationDTO> relations = new ArrayList<>();

        List<Substitute> substitutes = substituteRepository.findAll();
        for (Substitute  substitute : substitutes) {
            for (Allergy allergy : substitute.getAllergies()) {
                SubstituteAllergyRelationDTO relation = new SubstituteAllergyRelationDTO();
                relation.setSubstituteId(substitute.getId());
                relation.setSubstituteName(substitute.getName());
                relation.setAllergyId(allergy.getId());
                relation.setAllergyName(allergy.getAllergyName());
                relations.add(relation);
            }
        }

        return relations;
    }
    public List<Food> checkAllergies(Long userId, Food food) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        List<Allergy> userAllergies = user.getAllergies();
        List<Food> allergicFoods = new ArrayList<>();

        for (Allergy allergy : userAllergies) {
            if (food.getAllergies().contains(allergy)) {
                allergicFoods.add(food);
                break;
            }
        }
        return allergicFoods;
    }

}
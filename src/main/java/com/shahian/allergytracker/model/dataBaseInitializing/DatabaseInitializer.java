package com.shahian.allergytracker.model.dataBaseInitializing;

import com.shahian.allergytracker.model.entity.Allergy;
import com.shahian.allergytracker.model.entity.Food;
import com.shahian.allergytracker.model.entity.Substitute;
import com.shahian.allergytracker.model.entity.User;
import com.shahian.allergytracker.repository.AllergyRepository;
import com.shahian.allergytracker.repository.FoodRepository;
import com.shahian.allergytracker.repository.SubstituteRepository;
import com.shahian.allergytracker.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final AllergyRepository allergyRepository;
    private final FoodRepository foodRepository;
    private final SubstituteRepository substituteRepository;

    public DatabaseInitializer(UserRepository userRepository, AllergyRepository allergyRepository, FoodRepository foodRepository, SubstituteRepository substituteRepository) {
        this.userRepository = userRepository;
        this.allergyRepository = allergyRepository;
        this.foodRepository = foodRepository;
        this.substituteRepository = substituteRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0 && allergyRepository.count() == 0 && foodRepository.count() == 0 && substituteRepository.count() == 0) {
            // Create users
            User user1 = new User();
            user1.setFullName("hamidreza shahian");
            User user2 = new User();
            user2.setFullName("ali ahmadi");

            // Create allergies
            Allergy garlicAllergy = new Allergy();
            garlicAllergy.setAllergyName("garlic");
            Allergy blackPepperAllergy = new Allergy();
            blackPepperAllergy.setAllergyName("Black pepper");
            Allergy thymeAllergy = new Allergy();
            thymeAllergy.setAllergyName("Thyme");
            Allergy turmericAllergy = new Allergy();
            turmericAllergy.setAllergyName("Turmeric");

            // Create foods
            Food pizza = new Food();
            pizza.setFoodName("Pizza");
            Food pasta = new Food();
            pasta.setFoodName("Pasta");
            Food soup = new Food();
            soup.setFoodName("Soup");

            // Associate allergies with foods
            pizza.setAllergies(Arrays.asList(garlicAllergy, thymeAllergy));
            pasta.setAllergies(List.of(blackPepperAllergy));
            soup.setAllergies(List.of(turmericAllergy));

            // Create substitutes
            Substitute substitute1 = new Substitute();
            substitute1.setName("Thyme-free Pizza");
            Substitute substitute2 = new Substitute();
            substitute2.setName("garlic-free Pasta");
            Substitute substitute3 = new Substitute();
            substitute3.setName("Black pepper-free  Soup");
            // Associate allergies with substitutes
            substitute1.setAllergies(Arrays.asList(thymeAllergy));
            substitute2.setAllergies(List.of(garlicAllergy));
            substitute3.setAllergies(List.of(blackPepperAllergy));

            // Associate allergies with users
            user1.setAllergies(Arrays.asList(garlicAllergy, thymeAllergy));
            user2.setAllergies(Arrays.asList(blackPepperAllergy, turmericAllergy));

            // Set user for each allergy
            garlicAllergy.setUser(user1);
            thymeAllergy.setUser(user1);
            blackPepperAllergy.setUser(user2);
            turmericAllergy.setUser(user2);

            // Save entities to the database
            userRepository.saveAll(Arrays.asList(user1, user2));
            allergyRepository.saveAll(Arrays.asList(garlicAllergy, thymeAllergy, blackPepperAllergy, turmericAllergy));
            foodRepository.saveAll(Arrays.asList(pizza, pasta, soup));
            substituteRepository.saveAll(Arrays.asList(substitute1, substitute2, substitute3));
        }
    }
}

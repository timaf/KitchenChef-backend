package at.refugeesCode.kitchenchefbackend.controller;

import at.refugeesCode.kitchenchefbackend.persistence.model.Ingredient;
import at.refugeesCode.kitchenchefbackend.persistence.model.Meal;
import at.refugeesCode.kitchenchefbackend.persistence.repository.MealRepository;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MealController {

    private MealRepository mealRepository;

    public MealController(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<Ingredient> showIngredients(String id) {
        Meal meal = mealRepository.findById(id).get();
        return meal.getIngredients();
    }
}

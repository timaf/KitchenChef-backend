package at.refugeesCode.kitchenchefbackend.controller;

import at.refugeesCode.kitchenchefbackend.persistence.repository.MealRepository;
import org.springframework.stereotype.Controller;

@Controller
public class MealController {

    private MealRepository mealRepository;

    public MealController(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }


}

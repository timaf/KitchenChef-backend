package at.refugeesCode.kitchenchefbackend.endpoint;

import at.refugeesCode.kitchenchefbackend.persistence.model.Meal;
import at.refugeesCode.kitchenchefbackend.persistence.repository.MealRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cook")
public class CookEndpoint {

    private MealRepository mealRepository;

    public CookEndpoint(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    @PostMapping
    Meal createMael(@RequestBody Meal meal){
        mealRepository.save(meal);
        return meal;
    }

    @GetMapping
    List<Meal> getAllMeals(){
        return mealRepository.findAll();
    }

}

package at.refugeesCode.kitchenchefbackend.endpoint;

import at.refugeesCode.kitchenchefbackend.persistence.model.Meal;
import at.refugeesCode.kitchenchefbackend.persistence.repository.MealRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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

        LocalDate dateOfEvent = LocalDate.of(meal.getYear(), meal.getMonth(), meal.getDay());
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd LLLL yyyy");

        String format = dateFormat.format(dateOfEvent);
        meal.setDateTime(format);

        LocalTime startTime = meal.getStartTime();
        LocalTime cookTime = meal.getCookTime();
        long between = ChronoUnit.MINUTES.between(startTime, cookTime);
        meal.setPreparationTime(between);

        mealRepository.save(meal);
        return meal;
    }

    @GetMapping
    List<Meal> getAllMeals(){
        return mealRepository.findAll();
    }

    @PutMapping("/{id}")
    void deleteMeal(@PathVariable("id") String id){
        mealRepository.deleteById(id);
    }
}

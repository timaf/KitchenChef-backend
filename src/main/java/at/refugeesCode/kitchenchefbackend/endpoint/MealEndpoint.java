package at.refugeesCode.kitchenchefbackend.endpoint;

import at.refugeesCode.kitchenchefbackend.controller.MealController;
import at.refugeesCode.kitchenchefbackend.persistence.model.Ingredient;
import at.refugeesCode.kitchenchefbackend.persistence.model.Meal;
import at.refugeesCode.kitchenchefbackend.persistence.repository.MealRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class MealEndpoint {

    private MealRepository mealRepository;
    private MealController mealController;

    public MealEndpoint(MealRepository mealRepository, MealController mealController) {
        this.mealRepository = mealRepository;
        this.mealController = mealController;
    }

    @PostMapping("/meals")
    Meal createMeal(@RequestBody Meal meal) {

        LocalDate dateOfEvent = LocalDate.of(meal.getYear(), meal.getMonth(), meal.getDay());
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd LLLL yyyy");

        String format = dateFormat.format(dateOfEvent);
        meal.setDateTime(format);

        LocalTime startCookingTime = meal.getStartCookingTime();
        Long preparationTime = meal.getPreparationTime();

        LocalTime startEatingTime = startCookingTime.plusMinutes(preparationTime);
        meal.setStartEatingTime(startEatingTime);
        mealRepository.save(meal);
        return meal;
    }

    @GetMapping("/meals")
    List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }


    @GetMapping("/mealdetail/{id}")
    Meal detailPage(@PathVariable("id") String id) {
        return mealRepository.findById(id).get();

    }

    @GetMapping("/mealdetail/ingredients/{id}")
    List<Ingredient> showIngredients(@PathVariable("id") String id) {
        return mealController.showIngredients(id);
    }


    @PutMapping("/delete/{id}")
    void deleteMeal(@PathVariable("id") String id) {
        mealRepository.deleteById(id);
    }

    @PutMapping("/edit/{id}")
    Meal editMeal(@PathVariable("id") String id, String cookName, String mealName, String mealDescription,
                  List<Ingredient> ingredients, int year, int month, int day, int numberOfPeople, LocalTime startTime, LocalTime cookTime,
                  Long preparationTime, String dateTime) {
        Optional<Meal> mealEdit = mealRepository.findById(id);
        if (mealEdit.isPresent()) {
            mealEdit.get().setCookName(cookName);
            mealEdit.get().setMealName(mealName);
            mealEdit.get().setMealDescription(mealDescription);
            mealEdit.get().setIngredients(ingredients);
            mealEdit.get().setYear(year);
            mealEdit.get().setMonth(month);
            mealEdit.get().setDay(day);
            mealEdit.get().setNumberOfPeople(numberOfPeople);
            mealEdit.get().setStartCookingTime(startTime);
            mealEdit.get().setStartEatingTime(cookTime);
            mealEdit.get().setPreparationTime(preparationTime);
            mealEdit.get().setDateTime(dateTime);
        }
        return mealEdit.get();
    }

}

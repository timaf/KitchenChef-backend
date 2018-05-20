package at.refugeesCode.kitchenchefbackend.endpoint;

import at.refugeesCode.kitchenchefbackend.persistence.model.AppUser;
import at.refugeesCode.kitchenchefbackend.persistence.model.Meal;
import at.refugeesCode.kitchenchefbackend.persistence.repository.MealRepository;
import at.refugeesCode.kitchenchefbackend.persistence.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class CookEndpoint {

    private MealRepository mealRepository;
    private UserRepository userRepository;

    public CookEndpoint(MealRepository mealRepository, UserRepository userRepository) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/meals")
    Meal createMael(@RequestBody Meal meal){

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
    List<Meal> getAllMeals(){
        return mealRepository.findAll();
    }

    @GetMapping("/users")
    List<AppUser> getAllusers(){
        return userRepository.findAll();
    }

    @GetMapping("/mealdetail/{id}")
    Meal detailPage(@PathVariable("id") String id) {
        return mealRepository.findById(id).get();

    }
    // Meal model should be changed to be able to write, calculate and show ingredients in detail.
    @GetMapping("/mealdetail/shoppinglist/{id}")
    String showMealIngredients(@PathVariable("id") String id) {
        Meal meal = mealRepository.findById(id).get();
        return meal.getIngredients();
    }

    @PostMapping("/newuser")
    AppUser addUser(@RequestBody AppUser newUser){
        return userRepository.save(newUser);
    }

    @PutMapping("/delete/{id}")
    void deleteMeal(@PathVariable("id") String id){
        mealRepository.deleteById(id);
    }

    @PutMapping("/edit/{id}")
    Meal editMeal(@PathVariable("id") String id, String nameCook, String mealName, String mealDescription,
                  String ingredients, int year, int month, int day, int numberOfPeople, LocalTime startTime, LocalTime  cookTime,
                  Long preparationTime, String dateTime){
        Optional<Meal> mealEdit = mealRepository.findById(id);
        if (mealEdit.isPresent()){
            mealEdit.get().setCookName(nameCook);
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

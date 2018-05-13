package at.refugeesCode.kitchenchefbackend.endpoint;

import at.refugeesCode.kitchenchefbackend.persistence.model.AppUser;
import at.refugeesCode.kitchenchefbackend.persistence.model.Assistants;
import at.refugeesCode.kitchenchefbackend.persistence.model.Meal;
import at.refugeesCode.kitchenchefbackend.persistence.repository.AssistantsRepository;
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
    private AssistantsRepository assistantsRepository;

    public CookEndpoint(MealRepository mealRepository, UserRepository userRepository, AssistantsRepository assistantsRepository) {
        this.mealRepository = mealRepository;
        this.userRepository = userRepository;
        this.assistantsRepository = assistantsRepository;
    }

    @PostMapping
    Meal createMael(@RequestBody Meal meal, @RequestParam Assistants assistant){

        LocalDate dateOfEvent = LocalDate.of(meal.getYear(), meal.getMonth(), meal.getDay());
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd LLLL yyyy");

        String format = dateFormat.format(dateOfEvent);
        meal.setDateTime(format);

        LocalTime startTime = meal.getStartTime();
        LocalTime cookTime = meal.getCookTime();
        long between = ChronoUnit.MINUTES.between(startTime, cookTime);
        meal.setPreparationTime(between);

        mealRepository.save(meal);
        assistantsRepository.save(assistant);
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

    @PostMapping("/newuser")
    AppUser addUser(@RequestBody AppUser newUser){
        return userRepository.save(newUser);
    }

    @PutMapping("/delete/{id}")
    void deleteMeal(@PathVariable("id") String id){
        mealRepository.deleteById(id);
    }

    @PutMapping("/edit/{id}")
    Meal editMeal(@PathVariable("id") String id, @RequestParam String nameCook, String mealName, String mealDescription,
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
            mealEdit.get().setStartTime(startTime);
            mealEdit.get().setCookTime(cookTime);
            mealEdit.get().setPreparationTime(preparationTime);
            mealEdit.get().setDateTime(dateTime);
        }
        return mealEdit.get();

    }

    @PutMapping("/assistant/{mealId}/{userId}/{assistant}")
    Meal assistants(@PathVariable("assistant") String assistantId, @PathVariable("userId") String userId,
                    @PathVariable("mealId") String mealId, @RequestParam String assistant){
        Optional<AppUser> user_Id = userRepository.findById(userId);
        Optional<Meal> meal_Id = mealRepository.findById(mealId);
        if(user_Id.isPresent()){
            String Name = user_Id.get().getUsername();
            if(meal_Id.isPresent()){
                Optional<Assistants> assistant_id = assistantsRepository.findById(assistantId);
                assistant_id.get().setAssistant(assistant);
            }
            return meal_Id.get();
        }
        return meal_Id.get();
    }
}

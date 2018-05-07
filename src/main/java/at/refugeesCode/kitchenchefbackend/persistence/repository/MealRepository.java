package at.refugeesCode.kitchenchefbackend.persistence.repository;

import at.refugeesCode.kitchenchefbackend.persistence.model.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MealRepository extends MongoRepository<Meal, String> {
}

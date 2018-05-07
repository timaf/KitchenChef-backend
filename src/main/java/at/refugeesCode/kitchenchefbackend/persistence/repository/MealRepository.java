package at.refugeesCode.kitchenchefbackend.persistence.repository;

import at.refugeesCode.kitchenchefbackend.persistence.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<Food, String> {
}

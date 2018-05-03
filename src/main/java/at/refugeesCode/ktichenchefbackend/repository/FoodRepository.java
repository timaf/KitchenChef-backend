package at.refugeesCode.ktichenchefbackend.repository;

import at.refugeesCode.ktichenchefbackend.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<Food, String> {
}

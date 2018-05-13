package at.refugeesCode.kitchenchefbackend.persistence.repository;

import at.refugeesCode.kitchenchefbackend.persistence.model.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MealRepository extends MongoRepository<Meal, String> {

    @Override
    Optional<Meal> findById(String s);

    @Override
    void deleteById(String s);
}

package at.refugeesCode.kitchenchefbackend.persistence.repository;

import at.refugeesCode.kitchenchefbackend.persistence.model.Assistants;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssistantsRepository extends MongoRepository<Assistants, String> {

}

package at.refugeesCode.ktichenchefbackend.repository;

import at.refugeesCode.ktichenchefbackend.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<AppUser, String> {
}

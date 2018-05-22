package at.refugeesCode.kitchenchefbackend.endpoint;

import at.refugeesCode.kitchenchefbackend.persistence.model.AppUser;
import at.refugeesCode.kitchenchefbackend.persistence.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserEndpoint {

    private UserRepository userRepository;

    public UserEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/newuser")
    AppUser addUser(@RequestBody AppUser newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<AppUser> getAllusers() {
        return userRepository.findAll();
    }
}

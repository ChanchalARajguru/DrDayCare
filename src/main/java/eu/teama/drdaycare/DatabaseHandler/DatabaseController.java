package eu.teama.drdaycare.DatabaseHandler;

import eu.teama.drdaycare.UserTypes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class DatabaseController {
    @Autowired
    private static UserRepository userRepository;

    public static void insertUser(User user){
        userRepository.save(user);
    }

    public static Optional<User> getSingleUser(int id){
        return userRepository.findById(id);
    }

    public static Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public static Iterable<User> getUsersByRole(String userRole) {return userRepository.userByRole(userRole);}

    public static void deleteUser(int id){
        userRepository.deleteById(id);
    }

    public static void updateUser(User user){
        userRepository.save(user);
    }
}

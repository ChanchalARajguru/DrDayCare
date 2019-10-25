package eu.teama.drdaycare.Login;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import eu.teama.drdaycare.Login.jsonData.LoginRequest;
import eu.teama.drdaycare.Login.jsonData.LoginResponse;
import eu.teama.drdaycare.UserTypes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.sql.SQLException;

@Component
public class LoginManager {

    private final DatabaseController databaseController;

    @Autowired
    public  LoginManager( final DatabaseController databaseController){
        this.databaseController =databaseController;
    }

    public LoginResponse checkLogin(final LoginRequest loginRequest) throws SQLException {
        Iterable<User> allUsers = DatabaseController.getAllUsers();

        for (User user: allUsers){
            if (loginRequest.getName().equals(user.getName()) && loginRequest.getName().equals(user.getName()))
                return new LoginResponse(true, user);
        }

        return new LoginResponse(false, null);
    }
}

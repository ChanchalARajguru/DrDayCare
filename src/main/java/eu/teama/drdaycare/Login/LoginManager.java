package eu.teama.drdaycare.Login;


import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import eu.teama.drdaycare.Login.jsonData.LoginRequest;
import eu.teama.drdaycare.Login.jsonData.LoginResponse;
import eu.teama.drdaycare.UserTypes.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLException;


@Service
public class LoginManager {

    Logger logger = LoggerFactory.getLogger(LoginManager.class);

    private final DatabaseController databaseController;
    @Autowired
    public LoginManager( final DatabaseController databaseController){this.databaseController =databaseController;}
    public LoginResponse checkLogin(LoginRequest loginRequest) throws SQLException {

        Iterable<User> allUsers = databaseController.getAllUsers();

        for (User user: allUsers){
            if (loginRequest.getName().equals(user.getName()) && loginRequest.getName().equals(user.getName()))
                return new LoginResponse(true, user);
        }

        return new LoginResponse(false, null);
    }
}

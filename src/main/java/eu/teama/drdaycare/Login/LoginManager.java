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

    @Autowired
    private final DatabaseController databaseController;

    public LoginManager( final DatabaseController databaseController){this.databaseController =databaseController;}

    //Method uses an instance of databaseController to get a list of all users. Then the input user is compared against
    // each user in the database, one at a time and if the user is found it is returned.
    public LoginResponse checkLogin(LoginRequest loginRequest) throws SQLException { ;

        logger.info("Trying to find user with name " + loginRequest.getName() );

        Iterable<User> allUsers = databaseController.getAllUsers();

        for (User user: allUsers){
            if (loginRequest.getName().equals(user.getName()) && loginRequest.getName().equals(user.getName())) {
                logger.info("User found with name " + user.getName());
                return new LoginResponse(true, user);
            }
        }

        logger.info("Could not find user with name " + loginRequest.getName() );
        return new LoginResponse(false, null);
    }
}

package eu.teama.drdaycare.Login;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import eu.teama.drdaycare.Login.jsonData.LoginRequest;
import eu.teama.drdaycare.Login.jsonData.LoginResponse;
import eu.teama.drdaycare.UserTypes.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
class LoginManagerTest {

    @InjectMocks
    private LoginManager loginManager;

    @Mock
    private DatabaseController databaseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void checkLoginWithValidUser() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        User validUser = new User(1,"Valid User",1,"email@ait.ie","Password");
        users.add(validUser);
        LoginRequest validLoginRequest = new LoginRequest("Valid User","Password");
        LoginResponse expectedValidResponse = new LoginResponse(true, validUser);

        Mockito.when(databaseController.getAllUsers()).thenReturn(users);

        LoginResponse responseFromManager = loginManager.checkLogin(validLoginRequest);
        assertThat(responseFromManager).isEqualToComparingFieldByField(expectedValidResponse);
    }

    @Test
    void checkLoginWithInvalidUser() throws SQLException {
        ArrayList<User> users = new ArrayList<User>();
        User validUser = new User(1,"Valid User",1,"email@ait.ie","Password");
        users.add(validUser);
        LoginRequest invalidLoginRequest = new LoginRequest("Invalid User","Password");
        LoginResponse expectedInvalidResponse = new LoginResponse(false, null);

        Mockito.when(databaseController.getAllUsers()).thenReturn(users);

        LoginResponse responseFromManager = loginManager.checkLogin(invalidLoginRequest);
        assertThat(responseFromManager).isEqualToComparingFieldByField(expectedInvalidResponse);
    }
}
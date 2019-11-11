package eu.teama.drdaycare.HttpClient;

import eu.teama.drdaycare.Login.jsonData.LoginRequest;
import eu.teama.drdaycare.Login.jsonData.LoginResponse;
import eu.teama.drdaycare.Login.LoginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class HttpController
{
    Logger logger = LoggerFactory.getLogger(HttpController.class);

    @Autowired
    private LoginManager loginManager;

    //Takes a POST request over at address $System_IP/login (ie http://localhost:8080/login if run on local system) with a JSON login request in the body
    //Method takes in a loginRequest, gives information to LoginManager and then returns the loginResponse it receives from the manager.
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:9000")
    public LoginResponse checkLogin(@RequestBody LoginRequest loginRequest) throws SQLException {
        logger.info("HTTP client received Login Request");
        return loginManager.checkLogin(loginRequest);
    }
}

package eu.teama.drdaycare.HttpClient;

import eu.teama.drdaycare.Login.jsonData.LoginRequest;
import eu.teama.drdaycare.Login.jsonData.LoginResponse;
import eu.teama.drdaycare.Login.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class HttpController
{
    @Autowired
    private LoginManager loginManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse checkLogin(@RequestBody LoginRequest loginRequest) throws SQLException {
        return loginManager.checkLogin(loginRequest);
    }
}

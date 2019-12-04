package eu.teama.drdaycare.IntegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.teama.drdaycare.Login.jsonData.LoginRequest;
import eu.teama.drdaycare.Login.jsonData.LoginResponse;
import eu.teama.drdaycare.UserTypes.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @Sql(scripts = {"classpath:dataForTests/login-h2.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"classpath:dataForTests/loginCleanup-h2.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testLoginWithValidUser() throws Exception {
        LoginRequest validRequest = new LoginRequest("User", "Password");
        String validRequestJson = mapper.writeValueAsString(validRequest);
        User validUser = new User(1, "User", 1 , "user@ait.ie", "Password" );
        LoginResponse expectedResponse = new LoginResponse(true, validUser);
        String expectedJsonResponse = mapper.writeValueAsString(expectedResponse);

        ResultActions resultActions = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestJson))
                .andExpect(status().is2xxSuccessful());

        MvcResult result = resultActions.andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        assertEquals(expectedJsonResponse, jsonResponse);
    }

    @Test
    public void testLoginWithoutValidUser() throws Exception {
        LoginRequest invalidRequest = new LoginRequest("User", "Password");
        String validRequestJson = mapper.writeValueAsString(invalidRequest);
        LoginResponse expectResponse = new LoginResponse(false, null);
        String expectedJsonResponse = mapper.writeValueAsString(expectResponse);

        ResultActions resultActions = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestJson))
                .andExpect(status().is2xxSuccessful());


        MvcResult result = resultActions.andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        assertEquals(expectedJsonResponse, jsonResponse);
    }

    @Test
    public void checkLoginWithoutUser() throws Exception{
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().is4xxClientError());
    }
}



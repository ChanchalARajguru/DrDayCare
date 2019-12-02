package eu.teama.drdaycare.IntegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.teama.drdaycare.Login.jsonData.LoginRequest;
import eu.teama.drdaycare.additionalDetails.AdditionalDetails;
import org.testng.annotations.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdditionalDetailsTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @Sql(scripts = {"classpath:dataForTests/additionalDetails-h2.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"classpath:dataForTests/additionalDetailsCleanup-h2.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetPatientAdditionalDetailsWithValidId() throws Exception{
        String patientId = "1";

        ArrayList<AdditionalDetails> expectedResponse = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            AdditionalDetails additionalDetails = new AdditionalDetails();
            expectedResponse.add(additionalDetails);
        }

        String expectedJsonResponse = mapper.writeValueAsString(expectedResponse);

        ResultActions resultActions = mockMvc.perform(get("doctor/getPatientAdditionalDetails/{patientId}", patientId))
                .andExpect(status().is2xxSuccessful());

        MvcResult result = resultActions.andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        assertEquals(expectedJsonResponse, jsonResponse);
    }

    @Test
    @Sql(scripts = {"classpath:dataForTests/additionalDetails-h2.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"classpath:dataForTests/additionalDetailsCleanup-h2.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetPatientAdditionalDetailsWithInvalidId() throws Exception{
        String patientId = "3";

        ArrayList<AdditionalDetails> expectResponse = new ArrayList<>();

        String expectJsonResponse = mapper.writeValueAsString(expectResponse);

        ResultActions resultActions = mockMvc.perform(get("doctor/getPatientAdditionalDetails/{patientId}", patientId))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        assertEquals(jsonResponse, expectJsonResponse);
    }
}

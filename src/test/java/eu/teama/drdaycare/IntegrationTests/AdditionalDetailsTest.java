package eu.teama.drdaycare.IntegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.teama.drdaycare.additionalDetails.AdditionalDetails;
import eu.teama.drdaycare.additionalDetails.AdditionalDetailsRequest;
import org.junit.Assert;
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

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        String patientId = "2"; int creatorId = 1;

        ArrayList<AdditionalDetails> expectedResponse = new ArrayList<>();
        for (int i = 1; i <= 5; i++){
            AdditionalDetails additionalDetails = new AdditionalDetails(i,  creatorId, Integer.parseInt(patientId), "This is comment number " + i );
            expectedResponse.add(additionalDetails);
        }

        String expectedJsonResponse = mapper.writeValueAsString(expectedResponse);

        ResultActions resultActions = mockMvc.perform(get("/doctor/patientAdditionalDetails/{patientId}", patientId));

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

        ResultActions resultActions = mockMvc.perform(get("/doctor/patientAdditionalDetails/{patientId}", patientId))
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        assertEquals(jsonResponse, expectJsonResponse);
    }

    @Test
    @Sql(scripts = {"classpath:dataForTests/additionalDetailsCleanup-h2.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Sql(scripts = {"classpath:dataForTests/additionalDetailsCleanup-h2.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testAddValidAdditionalDetail() throws Exception {
        int patientId = 1; int creatorId = 2; String commentText = "This is our detail";
        AdditionalDetails additionalDetail = new AdditionalDetails(1,creatorId,patientId, commentText);

        ArrayList<AdditionalDetails> expectedResponseBefore = new ArrayList<>();
        String expectJsonResponseBefore = mapper.writeValueAsString(expectedResponseBefore);

        AdditionalDetailsRequest additionalDetailsRequest = new AdditionalDetailsRequest(creatorId, patientId, commentText);
        String validRequestJson = mapper.writeValueAsString(additionalDetailsRequest);

        ArrayList<AdditionalDetails> expectedResponseAfter = new ArrayList<>();
        expectedResponseAfter.add(additionalDetail);
        String expectJsonResponseAfter = mapper.writeValueAsString(expectedResponseAfter);

        //Use to check it is empty
        ResultActions resultActionBefore = mockMvc.perform(get("/doctor/patientAdditionalDetails/{patientId}", patientId))
                .andExpect(status().isOk());

        MvcResult resultBefore = resultActionBefore.andReturn();
        String jsonResponseBefore = resultBefore.getResponse().getContentAsString();

        assertEquals(expectJsonResponseBefore, jsonResponseBefore);

        //Endpoint under test
        mockMvc.perform(post("/doctor/patientAdditionalDetails/{patientId}", patientId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestJson))
                .andExpect(status().isOk());

        //Check to see if previous endpoint worked
        ResultActions resultActionAfter = mockMvc.perform(post("/doctor/patientAdditionalDetails/{patientId}", patientId))
                .andExpect(status().isOk());

        MvcResult resultAfter = resultActionAfter.andReturn();
        String jsonResponseAfter = resultAfter.getResponse().getContentAsString();

        assertEquals(jsonResponseAfter, expectJsonResponseAfter);
    }

    @Test
    @Sql(scripts = {"classpath:dataForTests/additionalDetails-h2.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"classpath:dataForTests/additionalDetailsCleanup-h2.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testDeletePresentDetail() throws Exception {
        //Setup Variables
        int patientId = 1; int creatorId = 2; String commentText = "This is our detail"; int detailId = 1;
        AdditionalDetails additionalDetail = new AdditionalDetails(detailId,creatorId,patientId, commentText);

        ArrayList<AdditionalDetails> expectedResponseBefore = new ArrayList<>();
        expectedResponseBefore.add(additionalDetail);
        String expectJsonResponseBefore = mapper.writeValueAsString(expectedResponseBefore);

        ArrayList<AdditionalDetails> expectedResponseAfter = new ArrayList<>();
        String expectJsonResponseAfter = mapper.writeValueAsString(expectedResponseAfter);

        //Check there is a object
        ResultActions resultActionBefore = mockMvc.perform(get("/doctor/patientAdditionalDetails/{patientId}", patientId))
                .andExpect(status().isOk());

        MvcResult resultBefore = resultActionBefore.andReturn();
        String jsonResponseBefore = resultBefore.getResponse().getContentAsString();

        assertEquals(expectJsonResponseBefore, jsonResponseBefore);

        //Run endpoint
        mockMvc.perform(get("/doctor/patientAdditionalDetails/{patientId}/{detailId}", patientId, detailId))
                .andExpect(status().isOk());

        //Check that object is gone
        ResultActions resultActionAfter = mockMvc.perform(post("/doctor/patientAdditionalDetails/{patientId}", patientId));

        MvcResult resultAfter = resultActionAfter.andReturn();
        String jsonResponseAfter = resultAfter.getResponse().getContentAsString();

        assertEquals(jsonResponseAfter, expectJsonResponseAfter);
    }

    @Test
    @Sql(scripts = {"classpath:dataForTests/additionalDetails-h2.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"classpath:dataForTests/additionalDetailsCleanup-h2.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testEditValidDetail() throws Exception {
        //Setup Variables
        int patientId = 1; int creatorId = 2; String originalCommentText = "This is our detail"; String newCommentText= "This is the new detail"; int detailId = 1;
        AdditionalDetails additionalDetail = new AdditionalDetails(detailId,creatorId,patientId, originalCommentText);

        ArrayList<AdditionalDetails> expectedResponseBefore = new ArrayList<>();
        expectedResponseBefore.add(additionalDetail);
        String expectJsonResponseBefore = mapper.writeValueAsString(expectedResponseBefore);

        ArrayList<AdditionalDetails> expectedResponseAfter = new ArrayList<>();
        additionalDetail.setCommentText(newCommentText);
        expectedResponseAfter.add(additionalDetail);
        String expectJsonResponseAfter = mapper.writeValueAsString(expectedResponseAfter);

        //Check that object equals expectation
        ResultActions resultActionBefore = mockMvc.perform(get("/doctor/patientAdditionalDetails/{patientId}", patientId));

        MvcResult resultBefore = resultActionBefore.andReturn();
        String jsonResponseBefore = resultBefore.getResponse().getContentAsString();

        assertEquals(jsonResponseBefore, expectJsonResponseBefore);

        //Run endpoint
        mockMvc.perform(post("/doctor/patientAdditionalDetails/{patientId}/{detailId}", patientId, detailId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newCommentText))
                .andExpect(status().isOk());

        //Check that has changed
        ResultActions resultActionAfter = mockMvc.perform(post("/doctor/patientAdditionalDetails/{patientId}", patientId))
                .andExpect(status().isOk());

        MvcResult resultAfter = resultActionAfter.andReturn();
        String jsonResponseAfter = resultAfter.getResponse().getContentAsString();

        assertEquals(jsonResponseAfter, expectJsonResponseAfter);
    }
}

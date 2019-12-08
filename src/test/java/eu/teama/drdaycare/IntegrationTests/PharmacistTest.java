package eu.teama.drdaycare.IntegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.teama.drdaycare.comment.Comment;
import eu.teama.drdaycare.comment.CommentRequest;
import org.junit.Test;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PharmacistTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    @Sql(scripts = {"classpath:dataForTests/comment-h2.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"classpath:dataForTests/commentCleanup-h2.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetAllCommentsForPatientWithValidId() throws Exception {
        int patientId = 3; int creatorId = 2;

        ArrayList<Comment> expectedResponse = new ArrayList<>();
        for (int i = 1; i <= 5; i++){
            Comment comment = new Comment(i, patientId,  creatorId, "This is comment " + i, true);
            expectedResponse.add(comment);
        }

        String expectedJsonResponse = mapper.writeValueAsString(expectedResponse);

        ResultActions resultActions = mockMvc.perform(get("/pharmacist/comment/{patientId}", patientId));

        MvcResult result = resultActions.andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        assertEquals(expectedJsonResponse, jsonResponse);
    }

    @Test
    @Sql(scripts = {"classpath:dataForTests/comment-h2.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"classpath:dataForTests/commentCleanup-h2.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetAllCommentsForPatientWithoutValidId() throws Exception {
        String patientId = "4"; int creatorId = 1;

        ArrayList<Comment> expectedResponse = new ArrayList<>();

        String expectedJsonResponse = mapper.writeValueAsString(expectedResponse);

        ResultActions resultActions = mockMvc.perform(get("/pharmacist/comment/{patientId}", patientId));

        MvcResult result = resultActions.andReturn();
        String jsonResponse = result.getResponse().getContentAsString();

        assertEquals(expectedJsonResponse, jsonResponse);
    }

    @Test
    @Sql(scripts = {"classpath:dataForTests/commentCleanup-h2.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"classpath:dataForTests/commentCleanup-h2.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testAddComment() throws Exception {
        //Setup Variables
        int patient_id = 3; int creator_id = 2; String comment_text = "This is a comment"; boolean visible_to_patient = true; int commentId = 0;
        Comment comment = new Comment(patient_id, creator_id, comment_text, visible_to_patient);

        ArrayList<Comment> expectedResponseBefore = new ArrayList<>();
        String expectJsonResponseBefore = mapper.writeValueAsString(expectedResponseBefore);

        CommentRequest commentRequest = new CommentRequest(patient_id, creator_id, comment_text, visible_to_patient);
        String validRequestJson = mapper.writeValueAsString(commentRequest);

        ArrayList<Comment> expectedResponseAfter = new ArrayList<>();
        expectedResponseAfter.add(comment);
        String expectJsonResponseAfter = mapper.writeValueAsString(expectedResponseAfter);

        //Check that object equals expectation
        ResultActions resultActionBefore = mockMvc.perform(get("/pharmacist/comment/{patientId}", patient_id))
                .andExpect(status().isOk());

        MvcResult resultBefore = resultActionBefore.andReturn();
        String jsonResponseBefore = resultBefore.getResponse().getContentAsString();

        assertEquals(expectJsonResponseBefore, jsonResponseBefore);

        //Run Endpoint
        mockMvc.perform(post("/pharmacist/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestJson))
                .andExpect(status().isOk());

        //Check that change has happened
        ResultActions resultActionAfter = mockMvc.perform(get("/pharmacist/comment/{patientId}", patient_id))
                .andExpect(status().isOk());

        MvcResult resultAfter = resultActionAfter.andReturn();
        String jsonResponseAfter = resultAfter.getResponse().getContentAsString();

        assertEquals(expectJsonResponseAfter, jsonResponseAfter);
    }

    @Test
    @Sql(scripts = {"classpath:dataForTests/singleComment-h2.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"classpath:dataForTests/commentCleanup-h2.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testDeleteComment() throws Exception {
        //Setup Variables
        int comment_id = 0; int patient_id = 3; int creator_id = 2; String comment_text = "This is a single comment"; boolean visible_to_patient = true;
        Comment comment = new Comment(patient_id, creator_id, comment_text, visible_to_patient);

        ArrayList<Comment> expectedResponseBefore = new ArrayList<>();
        expectedResponseBefore.add(comment);
        String expectJsonResponseBefore = mapper.writeValueAsString(expectedResponseBefore);

        ArrayList<Comment> expectedResponseAfter = new ArrayList<>();
        String expectJsonResponseAfter = mapper.writeValueAsString(expectedResponseAfter);

        //Check that object equals expectation
        ResultActions resultActionBefore = mockMvc.perform(get("/pharmacist/comment/{patientId}", patient_id))
                .andExpect(status().isOk());

        MvcResult resultBefore = resultActionBefore.andReturn();
        String jsonResponseBefore = resultBefore.getResponse().getContentAsString();

        assertEquals(jsonResponseBefore, expectJsonResponseBefore);

        //Run Endpoint
        mockMvc.perform(delete("/pharmacist/comment/{patientId}/{commentId}", 3, 0))
                .andExpect(status().isOk());

        //Check that change has happened
        ResultActions resultActionAfter = mockMvc.perform(get("/pharmacist/comment/{patientId}", patient_id))
                .andExpect(status().isOk());

        MvcResult resultAfter = resultActionAfter.andReturn();
        String jsonResponseAfter = resultAfter.getResponse().getContentAsString();

        assertEquals(expectJsonResponseAfter, jsonResponseAfter);
    }

    @Test
    @Sql(scripts = {"classpath:dataForTests/singleComment-h2.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {"classpath:dataForTests/commentCleanup-h2.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testEditComment() throws Exception {
        //Setup Variables
        int comment_id = 1; int patient_id = 3; int creator_id = 2; String comment_text = "This is a single comment"; String newCommentText = "This is a new commetn"; boolean visible_to_patient = true;
        Comment comment = new Comment(patient_id, creator_id, comment_text, visible_to_patient);

        ArrayList<Comment> expectedResponseBefore = new ArrayList<>();
        expectedResponseBefore.add(comment);
        String expectJsonResponseBefore = mapper.writeValueAsString(expectedResponseBefore);

        ArrayList<Comment> expectedResponseAfter = new ArrayList<>();
        comment.setCommentText(comment_text);
        expectedResponseAfter.add(comment);
        String expectJsonResponseAfter = mapper.writeValueAsString(expectedResponseAfter);

        //Check that object equals expectation
        ResultActions resultActionBefore = mockMvc.perform(get("/pharmacist/comment/{patientId}", patient_id))
                .andExpect(status().isOk());

        MvcResult resultBefore = resultActionBefore.andReturn();
        String jsonResponseBefore = resultBefore.getResponse().getContentAsString();

        assertEquals(expectJsonResponseBefore, jsonResponseBefore);

        //Run Endpoint
        mockMvc.perform(post("/pharmacist/comment/{patientId}/{commentId}", patient_id, comment_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newCommentText))
                .andExpect(status().isOk());

        //Check that change has happened
        ResultActions resultActionAfter = mockMvc.perform(get("/pharmacist/comment/{patientId}", patient_id))
                .andExpect(status().isOk());

        MvcResult resultAfter = resultActionAfter.andReturn();
        String jsonResponseAfter = resultAfter.getResponse().getContentAsString();

        assertEquals(expectJsonResponseAfter, jsonResponseAfter);
    }
}

package eu.teama.drdaycare.IntegrationTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.teama.drdaycare.comment.CommentRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testAddCommentWithValidComment() throws Exception {
        CommentRequest validCommentRequest = new CommentRequest(1,2, "This is a comment", true);
        String validCommentRequestJson = mapper.writeValueAsString(validCommentRequest);

        mockMvc.perform(post("/pharmacist/addComment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validCommentRequestJson))
                .andExpect(status().is2xxSuccessful());

        //To do when get comment method is implemented check it comment is created.
    }

    @Test
    public void testAddCommentWithNoComment() throws Exception {

        mockMvc.perform(post("/pharmacist/addComment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().is4xxClientError());
    }
}

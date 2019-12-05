package eu.teama.drdaycare.comment;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
public class CommentManagerTest {

    @InjectMocks
    private CommentManager commentManager;

    @Mock
    private DatabaseController databaseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkAddCommentWithValidComment()throws SQLException{
        int patientId = 1,creatorId = 2;
        CommentRequest commentRequest = new CommentRequest(patientId,creatorId, "This is a comment", true);
        Comment commentInDatabaseHandler = new Comment(patientId,creatorId, "This is a comment", true);

        Mockito.doNothing().when(databaseController).addComment(commentInDatabaseHandler);

        commentManager.addComment(commentRequest);
    }

    @Test
    void deleteComment(){
        Comment comment = new Comment(1,2, 3, "This is our comment", true);

        Mockito.doNothing().when(databaseController).deleteComment(comment);

        commentManager.deleteComment("" + comment.getId());
    }

    @Test
    void editComment(){
        Comment comment = new Comment(1,2, 3, "This is our new comment", true);

        Mockito.doNothing().when(databaseController).editComment(comment);

        commentManager.editComment("" + comment.getId(), "This is our new comment");
    }
}
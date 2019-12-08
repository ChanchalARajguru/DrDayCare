package eu.teama.drdaycare.comment;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import eu.teama.drdaycare.additionalDetails.AdditionalDetails;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
    public void checkGetAllCommentsForPatientWithValidId(){
        int patientId = 1,creatorId = 2;
        String stringPatientId = "1";

        ArrayList<Comment> mockedCommentsList = new ArrayList<>();
        for (int i =1; i <= 5; i++) {
            String comment = "This is comment number " + i;
            mockedCommentsList.add(new Comment(i, patientId, creatorId, comment, true));
        }

        ArrayList<Comment> expectedCommentsList = new ArrayList<>();
        for (int i =1; i <= 5; i++) {
            String comment = "This is comment number " + i;
            expectedCommentsList.add(new Comment(i, patientId, creatorId, comment, true));
        }

        Mockito.when(databaseController.getCommentsForPatient(patientId)).thenReturn(mockedCommentsList);

        List<Comment> commentsList = commentManager.getAllCommentsForPatientId(stringPatientId);

        for (int i =0; i < commentsList.size();i++){
            assertThat(commentsList.get(i)).isEqualToComparingFieldByField(expectedCommentsList.get(i));
        }
    }

    @Test
    public void checkGetAllCommentsForPatientWithoutValidId(){
        String stringPatientId = "3";

        ArrayList <Comment> mockedCommentsList = new ArrayList<>();

        Mockito.when(databaseController.getCommentsForPatient(3)).thenReturn(mockedCommentsList);

        List<Comment> commentsList = commentManager.getAllCommentsForPatientId(stringPatientId);

        assertThat(commentsList).isEmpty();
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
    public void deleteComment(){
        Comment comment = new Comment(1,2, 3, "This is our comment", true);

        Mockito.doNothing().when(databaseController).deleteComment(comment);

        commentManager.deleteComment("" + comment.getId());
    }

    @Test
    public void editComment(){
        Comment comment = new Comment(1,2, 3, "This is our new comment", true);

        Mockito.doNothing().when(databaseController).editComment(comment);

        commentManager.editComment("" + comment.getId(), "This is our new comment");
    }
}
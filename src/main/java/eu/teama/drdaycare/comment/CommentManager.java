package eu.teama.drdaycare.comment;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentManager {

    Logger logger = LoggerFactory.getLogger(CommentManager.class);

    @Autowired
    private final DatabaseController databaseController;

    public CommentManager(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    public void addComment(CommentRequest commentRequest) {
        logger.info("Trying to add comment, made by " + commentRequest.getCreatorId() + " written about " + commentRequest.getPatientId());
        Comment comment = new Comment(commentRequest.getCreatorId(),commentRequest.getPatientId(), commentRequest.getCommentText(), commentRequest.isVisibleToPatient());
        databaseController.addComment(comment);
    }
}

package eu.teama.drdaycare.comment;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Comment comment = new Comment(commentRequest.getCreatorId(), commentRequest.getPatientId(), commentRequest.getCommentText(), commentRequest.isVisibleToPatient());
        logger.info(comment.getComment_text() + " " + comment.isVisible_to_patient());
        logger.info(commentRequest.getCommentText() + " " + commentRequest.isVisibleToPatient());
        databaseController.addComment(comment);
    }

    public List<Comment> getAllCommentsForPatientId(String patientId) {
        logger.info("Attempting to get comments for " + patientId);

        Iterable<Comment> comments = databaseController.getCommentsForPatient(Integer.parseInt(patientId));
        List<Comment> commentsList = turnToList(comments);

        if (commentsList.size() > 0) {
            logger.info("Returning comments for " + patientId);
            logger.info("" + commentsList.size());
            return commentsList;
        }

        logger.info("No comments returned");
        return commentsList;
    }

    public void editComment(String commentId, String commentText) {
        logger.info("Attempting to edit comment with id: " + commentId);
        Optional<Comment> optionalComment = databaseController.getComment(Integer.parseInt(commentId));

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            logger.info("Comment is present");
            comment.setCommentText(commentText);
            databaseController.editComment(comment);
        }
        logger.info("Comment is not present");
    }

    public void deleteComment(String commentId) {
        logger.info("Attempting to delete Comment with id: " + commentId);

        Iterable<Comment> beforeComments = databaseController.getAllComments();
        List <Comment> beforeList = turnToList(beforeComments);

        for (Comment comment: beforeList)
            logger.info(comment.printInfo());

        logger.info("Comment size:" + beforeList.size());

        Optional<Comment> optionalComment = databaseController.getComment(Integer.parseInt(commentId));
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            logger.info("Comment is present");
            databaseController.deleteComment(comment);
        }

        Iterable<Comment> afterComments = databaseController.getAllComments();
        List <Comment> afterList = turnToList(afterComments);

        for (Comment comment: afterList)
            logger.info(comment.printInfo());

    }

    private List<Comment> turnToList(Iterable <Comment> iterable){
        List list = new ArrayList();

        for (Comment object: iterable)
            list.add(object);

        return list;
    }
}

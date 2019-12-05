package eu.teama.drdaycare.DatabaseHandler;

import eu.teama.drdaycare.additionalDetails.AdditionalDetails;
import eu.teama.drdaycare.comment.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository <Comment, Integer> {

    @Query("SELECT c FROM Comment c where c.patient_id = :patient_id")
    Iterable<Comment> commentsForPatient(@Param("patient_id") int patient_id);
}

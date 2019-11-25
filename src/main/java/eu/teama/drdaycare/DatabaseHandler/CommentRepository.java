package eu.teama.drdaycare.DatabaseHandler;

import eu.teama.drdaycare.comment.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository <Comment, Integer> {
}

package eu.teama.drdaycare.DatabaseHandler;

import eu.teama.drdaycare.UserTypes.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    @Query("SELECT u FROM User u where u.userRole = :userRole")
    Iterable<User> userByRole(@Param("userRole") String userRole);
}

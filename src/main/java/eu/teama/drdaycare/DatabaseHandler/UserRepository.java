package eu.teama.drdaycare.DatabaseHandler;

import eu.teama.drdaycare.UserTypes.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    //Example of custom made method query, which gets all users with corresponding userRole
    @Query("SELECT u FROM User u where u.userRole = :userRole")
    Iterable<User> userByRole(@Param("userRole") int userRole);
}

package eu.teama.drdaycare.DatabaseHandler;

import eu.teama.drdaycare.UserTypes.Patient;
import eu.teama.drdaycare.UserTypes.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


    @Repository
    public interface PatientRepository extends CrudRepository<Patient,Integer> {
        @Query("SELECT u FROM Patient u where u.userId = :userId")
        Iterable<User> userId(@Param("userId") int userRole);
    }

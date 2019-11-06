package eu.teama.drdaycare.DatabaseHandler;

import eu.teama.drdaycare.UserTypes.Prescription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription,Integer> {
}


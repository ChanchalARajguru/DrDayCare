package eu.teama.drdaycare.DatabaseHandler;

import eu.teama.drdaycare.UserTypes.User;
import eu.teama.drdaycare.additionalDetails.AdditionalDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalDetailsRepository extends CrudRepository<AdditionalDetails, Integer> {

    @Query("SELECT ad FROM AdditionalDetails ad where ad.patientId = :patientId")
    Iterable<AdditionalDetails> additionalDetailsForPatient(@Param("patientId") int patientId);

}

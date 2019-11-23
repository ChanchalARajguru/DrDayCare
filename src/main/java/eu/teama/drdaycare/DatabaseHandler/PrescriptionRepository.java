package eu.teama.drdaycare.DatabaseHandler;
import eu.teama.drdaycare.UserTypes.Prescription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends CrudRepository<Prescription, Integer> {
    @Query(value = "SELECT u FROM Prescription u where u.patient_id = :patient_id")
    Iterable<Prescription> findPrescriptionsByPatient_id(@Param("patient_id") int patient_id);

}

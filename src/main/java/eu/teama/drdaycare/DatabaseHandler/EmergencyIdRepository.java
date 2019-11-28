package eu.teama.drdaycare.DatabaseHandler;
import eu.teama.drdaycare.UserTypes.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyIdRepository extends CrudRepository<Patient, Integer> {
    @Query(value = "SELECT u FROM Patient u where u.patient_id = :patient_id")
    Iterable<Patient> findEmergencyId(@Param("patient_id") int patient_id);
}

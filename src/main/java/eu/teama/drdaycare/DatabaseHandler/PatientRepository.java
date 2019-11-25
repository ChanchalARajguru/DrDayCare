package eu.teama.drdaycare.DatabaseHandler;

import eu.teama.drdaycare.UserTypes.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


    @Repository
    public interface PatientRepository extends CrudRepository<Patient,Integer> {
}

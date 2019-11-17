package eu.teama.drdaycare.EmergencyId;
import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import eu.teama.drdaycare.EmergencyId.json.EmergencyIdRequest;
import eu.teama.drdaycare.EmergencyId.json.EmergencyIdResponse;
import eu.teama.drdaycare.UserTypes.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Method uses an instance of databaseController to get a list of all emergencyIds. Then the patient_id is compared against
// each patient_id in the database, one at a time and if emergencyId is found it is returned.
@Service
public class EmergencyIdManager {
    Logger logger = LoggerFactory.getLogger(EmergencyIdManager.class);

    @Autowired
    private final DatabaseController databaseController;

    public EmergencyIdManager(final DatabaseController databaseController) {this.databaseController = databaseController;}

    public EmergencyIdResponse getEmergencyId(EmergencyIdRequest emergencyIdRequest) {
        Iterable<Patient> allPatients = databaseController.getAllEmergencyIds();
        for (Patient patient: allPatients){
            if (emergencyIdRequest.getPatient_id() == (patient.getPatient_id()))
                return new EmergencyIdResponse(true, patient);
        }

        logger.info("Could not find Emergency Id");
        return new EmergencyIdResponse (false ,null);
    }

}

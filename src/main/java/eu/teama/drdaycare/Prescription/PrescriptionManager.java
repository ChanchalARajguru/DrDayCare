package eu.teama.drdaycare.Prescription;
import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import eu.teama.drdaycare.Prescription.jsonData.PrescriptionRequest;
import eu.teama.drdaycare.Prescription.jsonData.PrescriptionResponse;
import eu.teama.drdaycare.UserTypes.Prescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PrescriptionManager {
    Logger logger = LoggerFactory.getLogger(PrescriptionManager.class);

    @Autowired
    private final DatabaseController databaseController;

    public PrescriptionManager( final DatabaseController databaseController){this.databaseController = databaseController;}

    public PrescriptionResponse getPrescription(PrescriptionRequest prescriptionRequest) {
        Iterable<Prescription> allPrescriptions = databaseController.getAllPrescriptions();

        for (Prescription prescription: allPrescriptions){
            if (prescriptionRequest.getPatientid() == prescription.getPatient_id()) {
                return new PrescriptionResponse(prescription);
            }
        }
        logger.info("Could not find Prescription " );
        return new PrescriptionResponse( null);
    }


}
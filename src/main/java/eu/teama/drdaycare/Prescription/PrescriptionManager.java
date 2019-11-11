package eu.teama.drdaycare.Prescription;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import eu.teama.drdaycare.Prescription.jsonData.PrescriptionRequest;
import eu.teama.drdaycare.Prescription.jsonData.PrescriptionResponse;
import eu.teama.drdaycare.UserTypes.Prescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

///Method uses an instance of databaseController to get a list of all prescriptions. Then the patient_id is compared against
// each patient_id in the database, one at a time and if prescription is found it is returned.
@Service
public class PrescriptionManager {
    Logger logger = LoggerFactory.getLogger(PrescriptionManager.class);

    @Autowired
    private final DatabaseController databaseController;

    public PrescriptionManager( final DatabaseController databaseController) {this.databaseController = databaseController;}

    public PrescriptionResponse getPrescription(PrescriptionRequest prescriptionRequest) {
        Iterable<Prescription> allPrescriptions = databaseController.getAllPrescriptions();
        for (Prescription prescription: allPrescriptions){
          if (prescriptionRequest.getPatient_id() == (prescription.getPatient_id()))
              return new PrescriptionResponse(true, prescription);
             }

        logger.info("Could not find Prescription");
        return new PrescriptionResponse (false , null);
    }


}
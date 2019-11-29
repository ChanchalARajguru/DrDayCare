package eu.teama.drdaycare.Prescription;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import eu.teama.drdaycare.Prescription.jsonData.PrescriptionRequest;
import eu.teama.drdaycare.Prescription.jsonData.PrescriptionResponse;
import eu.teama.drdaycare.UserTypes.Prescription;
import eu.teama.drdaycare.UserTypes.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    public List<PrescriptionDetail> getAllPrescriptionDetails() {
        Iterable<Prescription> prescriptions = databaseController.getAllPrescriptions();
        ArrayList<PrescriptionDetail> prescriptionDetailsList = new ArrayList<>();

        Iterable<User> users = databaseController.getAllUsers();
        ArrayList<User> usersList = new ArrayList<>();

        for (User user : users)
            usersList.add(user);

        for(Prescription prescription: prescriptions){
            PrescriptionDetail prescriptionDetail = new PrescriptionDetail(prescription.getPatient_id(), prescription.getPrescriber_id(), prescription.getPrescription_details());
            String patientName = findUserNameById(usersList, prescription.getPatient_id());
            String prescriberName = findUserNameById(usersList, prescription.getPrescriber_id());
            prescriptionDetail.setPatientName(patientName);
            prescriptionDetail.setPrescriberName(prescriberName);

            prescriptionDetailsList.add(prescriptionDetail);
        }

        if (prescriptionDetailsList.size() > 0) {
            logger.info("Returning prescriptions list");
            return prescriptionDetailsList;
        }

        logger.info("Could not find any prescriptions");
        return null;
    }

    private String findUserNameById(ArrayList<User> users, int id){
        for (User user : users)
            if (user.getId() == id)
                return user.getName();

        return null;
    }
}
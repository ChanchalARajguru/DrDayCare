package eu.teama.drdaycare.Patient;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import eu.teama.drdaycare.UserTypes.Patient;
import eu.teama.drdaycare.UserTypes.User;
import eu.teama.drdaycare.Patient.PatientManager;
import eu.teama.drdaycare.Patient.PatientListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Service
public class PatientManager {

    Logger logger = LoggerFactory.getLogger(PatientManager.class);


    @Autowired
    private final DatabaseController databaseController;

    public PatientManager( final DatabaseController databaseController) {this.databaseController = databaseController;}

    public PatientListResponse getAllPatients() {
        Iterable<Patient> users = databaseController.getAllPatients();
        ArrayList<Patient> list  = new ArrayList();
        for (Patient patient : users) {
            list.add(patient);
        }
        Patient patient = new Patient();
        list.add(patient);
        logger.info("Could not find Users");
        return new PatientListResponse (true , list);
    }

    public void addPatient(Patient patient) {
        patient.createEmergencyId();
        databaseController.insertPatient(patient);
    }
    @GetMapping("/doctor/edit")
    @ResponseBody public void editPatient(@RequestBody Patient patient,@RequestParam String userid) {
        databaseController.editpatient(patient,Integer.parseInt(userid));
    }

    @GetMapping("/doctor/delete")
    @ResponseBody public void deletePatient(@RequestParam String userid) {
        databaseController.deletepatient(Integer.parseInt(userid));
    }
}



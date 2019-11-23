package eu.teama.drdaycare.medicalstaff;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import eu.teama.drdaycare.UserTypes.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MedicalStaffManager {

    @Autowired
    private final DatabaseController databaseController;
    public MedicalStaffManager( final DatabaseController databaseController){this.databaseController =databaseController;}
    //Look at  Login Manager, create similar to method in there
    public ArrayList<Patient> getPatientList() {
        Iterable<Patient> patients = databaseController.getAllPatients();

        ArrayList<Patient> patientList = new ArrayList<>();

        for (Patient patient : patients)
            patientList.add(patient);

        return  patientList;
    }
}

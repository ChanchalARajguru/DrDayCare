package eu.teama.drdaycare.Patient;

import eu.teama.drdaycare.UserTypes.Patient;
import eu.teama.drdaycare.UserTypes.User;

import java.util.ArrayList;

public class PatientListResponse extends Patient {

    private final ArrayList<Patient> list;
    private final boolean valid;
    public PatientListResponse(boolean valid , ArrayList<Patient> list) {
        this.valid = valid;
        this.list = list;
    }
    public boolean isValid() {
        return valid;
    }
    public ArrayList getList() {
        return list;
    }
}

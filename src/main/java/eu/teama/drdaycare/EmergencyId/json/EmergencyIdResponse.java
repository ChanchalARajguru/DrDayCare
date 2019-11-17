package eu.teama.drdaycare.EmergencyId.json;

import eu.teama.drdaycare.UserTypes.Patient;

public class EmergencyIdResponse {

    private final Patient patient;
    private final boolean valid;
    public EmergencyIdResponse(boolean valid , Patient patient) {
        this.valid = valid;
        this.patient= patient;
    }
    public boolean isValid() {
        return valid;
    }
    public Patient getEmergencyId() {
        return patient;
    }

}

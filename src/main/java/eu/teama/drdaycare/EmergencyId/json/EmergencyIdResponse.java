package eu.teama.drdaycare.EmergencyId.json;

public class EmergencyIdResponse {

    private final Integer patient;
    private final boolean valid;
    public EmergencyIdResponse(boolean valid , Integer patient) {
        this.valid = valid;
        this.patient= patient;
    }
    public boolean isValid() {
        return valid;
    }
    public Integer getEmergencyId() {
        return patient;
    }

}

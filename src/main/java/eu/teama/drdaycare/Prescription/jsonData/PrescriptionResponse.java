package eu.teama.drdaycare.Prescription.jsonData;

import eu.teama.drdaycare.UserTypes.Prescription;

public class PrescriptionResponse {

    private final Prescription prescription;
    private final boolean valid;
    public PrescriptionResponse(boolean valid , Prescription prescription) {
        this.valid = valid;
        this.prescription = prescription;

    }
    public boolean isValid() {
        return valid;
    }
    public Prescription getPrescription() {
        return prescription;
    }
}
package eu.teama.drdaycare.Prescription.jsonData;

import eu.teama.drdaycare.UserTypes.Prescription;

public class PrescriptionResponse {

    private final Prescription prescription_details;
    private final boolean valid;
    public PrescriptionResponse(boolean valid , Prescription prescription_details) {
        this.prescription_details = prescription_details;
        this.valid = valid;
    }
    public boolean isValid() {
        return valid;
    }
    public Prescription getPrescription_details() {
        return prescription_details;
    }
}
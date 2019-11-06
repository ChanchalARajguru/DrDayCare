package eu.teama.drdaycare.Prescription.jsonData;

import eu.teama.drdaycare.UserTypes.Prescription;

public class PrescriptionResponse {

    private final Prescription prescription_details;

    public PrescriptionResponse( Prescription prescription_details) {
        this.prescription_details = prescription_details;
    }

    public Prescription getPrescription_details() {
        return prescription_details;}
}

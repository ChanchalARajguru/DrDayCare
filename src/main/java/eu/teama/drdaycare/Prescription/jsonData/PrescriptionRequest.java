package eu.teama.drdaycare.Prescription.jsonData;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PrescriptionRequest {
    private final Integer patient_id;

    public PrescriptionRequest(Integer patient_id) {
    this.patient_id = patient_id;

}

    public Integer getPatientid(){
        return patient_id;
    }

}


package eu.teama.drdaycare.EmergencyId.json;

public class EmergencyIdRequest {

    private Integer patient_id;

    public EmergencyIdRequest(Integer patient_id) {

        this.patient_id = patient_id;
    }

    public EmergencyIdRequest(){}

    public Integer getPatient_id() {
        return patient_id;
    }

}

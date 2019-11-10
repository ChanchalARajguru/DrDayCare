package eu.teama.drdaycare.Prescription.jsonData;


public class PrescriptionRequest {

    private final Integer patient_id;

    public PrescriptionRequest(Integer patient_id) {

        this.patient_id = patient_id;
    }

    public Integer getPatient_id() {
      return patient_id;
      //return 23;
    }

}
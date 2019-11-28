package eu.teama.drdaycare.ViewPatientDetails;

public class PatientDetails {
    private Integer patient_id;
    private String name;
    private String Email;

    public PatientDetails(Integer patient_id, String name, String Email) {

        this.patient_id = patient_id;
        this.name = name;
        this.Email = Email;

    }


    public PatientDetails() {
    }


    public Integer getPatientId() {
        return patient_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return Email;

    }
}

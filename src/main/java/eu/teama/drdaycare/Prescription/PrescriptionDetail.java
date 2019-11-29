package eu.teama.drdaycare.Prescription;

public class PrescriptionDetail {

    private int patientId;

    private int prescriberId;

    private String prescriptionDetails;

    private String prescriberName;

    private String patientName;



    public PrescriptionDetail(int patientId, int prescriberId, String prescriptionDetails){
        this.patientId = patientId;
        this.prescriberId = prescriberId;
        this.prescriptionDetails = prescriptionDetails;
    }

    public void setPrescriberName(String prescriberName) {
        this.prescriberName = prescriberName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getPrescriberId() {
        return prescriberId;
    }

    public String getPrescriberName() {
        return prescriberName;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPrescriptionDetails() {
        return prescriptionDetails;
    }

}

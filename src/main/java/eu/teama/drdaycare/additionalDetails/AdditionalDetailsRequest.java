package eu.teama.drdaycare.additionalDetails;

public class AdditionalDetailsRequest {

    private int creatorId;

    private int patientId;

    private String details;

    public AdditionalDetailsRequest(int creatorId, int patientId, String details){
        this.creatorId = creatorId;
        this.patientId = patientId;
        this.details = details;
    }

    public int getCreatorId(){
        return creatorId;
    }

    public int getPatientId(){
        return patientId;
    }

    public String getDetails(){
        return details;
    }
}

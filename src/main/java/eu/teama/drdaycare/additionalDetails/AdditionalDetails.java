package eu.teama.drdaycare.additionalDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AdditionalDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int creatorId;

    private int patientId;

    private String details;

    public AdditionalDetails(){   }

    public AdditionalDetails(int creatorId, int patientId, String details){
        this.creatorId = creatorId;
        this.patientId = patientId;
        this.details = details;
    }

    public AdditionalDetails(int id, int creatorId, int patientId, String details){
        this.id = id;
        this.creatorId = creatorId;
        this.patientId = patientId;
        this.details = details;
    }

    public int getId(){
        return id;
    }

    public int getCreatorId(){
        return creatorId;
    }

    public int getPatientId(){
        return patientId;
    }

    public String getDetailsText(){
        return details;
    }

    public void setCreatorId(int creatorId){
        this.creatorId = creatorId;
    }

    public void setPatientId(int patientId){
        this.patientId = patientId;
    }

    public void setCommentText(String commentText){
        this.details = commentText;
    }

}

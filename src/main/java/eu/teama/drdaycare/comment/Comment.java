package eu.teama.drdaycare.comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int patient_id;
    private int creator_id;
    private String comment_text;
    private boolean visible_to_patient;

    public Comment (){

    }

    public Comment (int patient_id, int creator_id, String comment_text, boolean visible_to_patient){
        this.patient_id = patient_id;
        this.creator_id = creator_id;
        this.comment_text = comment_text;
        this.visible_to_patient = visible_to_patient;
    }

    public Comment (int id, int patient_id, int creator_id, String comment_text, boolean visible_to_patient){
        this.id = id;
        this.patient_id = patient_id;
        this.creator_id = creator_id;
        this.comment_text = comment_text;
        this.visible_to_patient = visible_to_patient;
    }

    public void setCommentText(String comment_text){
        this.comment_text = comment_text;
    }

    public int getId() {
        return id;
    }

    public String getComment_text(){
        return comment_text;
    }

    public boolean isVisible_to_patient(){
        return visible_to_patient;
    }

    public int getPatient_id(){
        return patient_id;
    }

    public int getCreator_id(){
        return creator_id;
    }

    public String printInfo() {
        String info = "Id: " + id + " patientId " + patient_id;
        return info;
    }
}

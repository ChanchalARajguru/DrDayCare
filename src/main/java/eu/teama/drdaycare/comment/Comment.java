package eu.teama.drdaycare.comment;

import jdk.nashorn.internal.objects.annotations.Setter;

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
}

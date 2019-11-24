package eu.teama.drdaycare.UserTypes;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer patient_id;

    private int prescription_id;

    private int prescriber_id;

    private Date start_date;

    private Date end_date;

    private String prescription_details;

    private boolean prescription_claimed;
    public Integer getPatient_id() {return patient_id; }

      public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }
    public int getPrescription_id() {
        return prescription_id;
    }
    public void setPrescription_id(int prescription_id) {
        this.prescription_id = prescription_id;
    }

    public int getPrescriber_id() {
        return prescriber_id;
    }
    public void setPrescriber_id(int prescriber_id) {
        this.prescriber_id = prescriber_id;
    }

    public Date getStart_date() {
        return start_date;
    }
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }
    public Date getEnd_date() {
        return end_date;
    }
    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
   public String getPrescription_details() { return prescription_details; }
    public void setPrescription_details(String prescription_details) {
      this.prescription_details = prescription_details;
    }
    public boolean isPrescription_claimed() {
        return prescription_claimed;
    }
    public void setPrescription_claimed(boolean prescription_claimed) {
        this.prescription_claimed = prescription_claimed;
    }


}

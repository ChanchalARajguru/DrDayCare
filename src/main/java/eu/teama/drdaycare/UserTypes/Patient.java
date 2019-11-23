package eu.teama.drdaycare.UserTypes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Patient {
    @Id
private int userId;

    public int getUserId() {
        return userId;
    }

    private int emergencyId;

    public int getEmergencyId() {
        return emergencyId;
    }

    private int age;

    public int getAge() {
        return age;
    }
    private String address;
    public String getAddress() {
        return address;
    }


private String extraNotes;

    public String getExtraNotes() {
        return extraNotes;
    }
}

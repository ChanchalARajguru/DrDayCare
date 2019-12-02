package eu.teama.drdaycare.UserTypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Patient(){}

    public Patient(int userId, String name, int emergencyId, int age, String address, String extraNotes){
        this.userId = userId;
        this.name = name;
        this.emergencyId = emergencyId;
        this.age = age;
        this.address = address;
        this.extraNotes = extraNotes;
    }

    private int emergencyId;
    public void createEmergencyId(){
        emergencyId = (int)Math.random() * 50000 + 1000;
        System.out.println("emergencyId: "+ emergencyId);
    }

    public int getEmergencyId() {
        return emergencyId;
    }

    public void setEmergencyId(int emergencyId) {
        this.emergencyId = emergencyId;
    }

    private int age;
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String address;
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String extraNotes;
    public String getExtraNotes() {
        return extraNotes;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "userId=" + userId +
                ", Name='" + name + '\'' +
                ", emergencyId=" + emergencyId +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
package eu.teama.drdaycare.Patient;

public class PatientRequest {
    private final String Name;

    public PatientRequest(String name, String password){
        this.Name = name;
    }

    public String getName(){
        return Name;
    }


}

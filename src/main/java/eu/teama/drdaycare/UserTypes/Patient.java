package eu.teama.drdaycare.UserTypes;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patient {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer patient_id;
        private int emergencyId;
        private int age;
        private String address;
        private String extraNotes;

        public Integer getPatient_id() {
                return patient_id;
        }

        public void setPatient_id(Integer patient_id) {
                this.patient_id = patient_id;
        }

        public int getEmergencyId() {
                return emergencyId;
        }

        public void setEmergencyId(int emergencyId) {
                this.emergencyId = emergencyId;
        }

        public int getAge() {
                return age;
        }

        public void setAge(int age) {
                this.age = age;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public String getExtraNotes() {
                return extraNotes;
        }

        public void setExtraNotes(String extraNotes) {
                this.extraNotes = extraNotes;
        }
}

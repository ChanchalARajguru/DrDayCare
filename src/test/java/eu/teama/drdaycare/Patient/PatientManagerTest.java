package eu.teama.drdaycare.Patient;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import eu.teama.drdaycare.Login.LoginManager;
import eu.teama.drdaycare.UserTypes.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PatientManagerTest {

    @InjectMocks
    private PatientManager patientManager;

    @Mock
    private DatabaseController databaseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddPatientWithValidPatient(){
        Patient validPatient = new Patient(1, "User", 1111, 23, "Athlone", "");

        Mockito.doNothing().when(databaseController).insertPatient(validPatient);

        patientManager.addPatient(validPatient);
    }
}
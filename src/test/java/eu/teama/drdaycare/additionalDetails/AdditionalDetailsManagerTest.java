package eu.teama.drdaycare.additionalDetails;


import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
class AdditionalDetailsManagerTest {

    @InjectMocks
    private AdditionalDetailsManager additionalDetailsManager;

    @Mock
    private DatabaseController databaseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void checkGetAdditionalDetailsForPatientWithValidId(){
        int patientId = 1,creatorId = 2;
        String stringPatientId = "1";

        ArrayList <AdditionalDetails> mockedAdditionalDetails = new ArrayList<>();
        for (int i =1; i <= 5; i++) {
            String comment = "This is comment number " + i;
            mockedAdditionalDetails.add(new AdditionalDetails(i, patientId, creatorId, comment));
        }

        List<AdditionalDetails> expectedAdditionalDetailsList = new ArrayList<>();
        for (int i =1; i <= 5; i++) {
            String comment = "This is comment number " + i;
            expectedAdditionalDetailsList.add(new AdditionalDetails(1, patientId, creatorId, comment));
        }

        Mockito.when(databaseController.getAdditionalDetailsForPatient(patientId)).thenReturn(mockedAdditionalDetails);

        List<AdditionalDetails> additionalDetailsList = additionalDetailsManager.getAdditionalDetailsForPatient(stringPatientId);

        for (int i =0; i < additionalDetailsList.size();i++){
            assertThat(additionalDetailsList.get(i)).isEqualToComparingFieldByField(expectedAdditionalDetailsList.get(i));
        }
    }

    @Test
    void checkGetAdditionalDetailsForPatientWithoutValidId(){
        String stringPatientId = "3";

        ArrayList <AdditionalDetails> mockedAdditionalDetails = new ArrayList<>();

        Mockito.when(databaseController.getAdditionalDetailsForPatient(3)).thenReturn(mockedAdditionalDetails);

        List<AdditionalDetails> additionalDetailsList = additionalDetailsManager.getAdditionalDetailsForPatient(stringPatientId);

        assertThat(additionalDetailsList).isNull();
}
}
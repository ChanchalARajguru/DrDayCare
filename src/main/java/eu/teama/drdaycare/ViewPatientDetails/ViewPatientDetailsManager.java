package eu.teama.drdaycare.ViewPatientDetails;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import eu.teama.drdaycare.UserTypes.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ViewPatientDetailsManager {

        Logger logger = LoggerFactory.getLogger(eu.teama.drdaycare.ViewPatientDetails.ViewPatientDetailsManager.class);

        @Autowired
        private final DatabaseController databaseController;

        public ViewPatientDetailsManager( final DatabaseController databaseController) {this.databaseController = databaseController;}

        public PatientDetails getViewPatientdetails(int id) {
            Optional<User> optionalUser = databaseController.getSingleUser(id);

            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                PatientDetails patientDetails = new PatientDetails(user.getId(), user.getName(), user.getEmail());

                return patientDetails;
            }
            return null;
        }

    }

package eu.teama.drdaycare.admin;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import eu.teama.drdaycare.Prescription.PrescriptionManager;
import eu.teama.drdaycare.Prescription.jsonData.PrescriptionRequest;
import eu.teama.drdaycare.Prescription.jsonData.PrescriptionResponse;
import eu.teama.drdaycare.UserTypes.Prescription;
import eu.teama.drdaycare.UserTypes.User;

@Service
public class AdminManager {

	Logger logger = LoggerFactory.getLogger(AdminManager.class);

	
	@Autowired
    private final DatabaseController databaseController;

    public AdminManager( final DatabaseController databaseController) {this.databaseController = databaseController;}

    public UserListResponse getAllUsers() {
        Iterable<User> users = databaseController.getAllUsers();
        ArrayList<User> list  = new ArrayList();
        for (User user : users) {
        	list.add(user);
        }
        User user = new User();
    	user.setName("John");
    	list.add(user);
        logger.info("Could not find Users");
        return new UserListResponse (true , list);
    }
    
    public void addUser() {
    	User user = new User();
    	user.setName("John");
    	databaseController.insertUser(user);
    }
    
    public void deleteUser(int id) {
    	databaseController.deleteUser(id);
    }
}

package eu.teama.drdaycare.HttpClient;

import eu.teama.drdaycare.Login.jsonData.LoginRequest;
import eu.teama.drdaycare.Login.jsonData.LoginResponse;
import eu.teama.drdaycare.Login.LoginManager;
import eu.teama.drdaycare.Prescription.PrescriptionDetail;
import eu.teama.drdaycare.UserTypes.Prescription;
import eu.teama.drdaycare.medicalstaff.MedicalStaffManager;
import eu.teama.drdaycare.UserTypes.Patient;

import eu.teama.drdaycare.Prescription.jsonData.PrescriptionRequest;
import eu.teama.drdaycare.Prescription.jsonData.PrescriptionResponse;
import eu.teama.drdaycare.UserTypes.User;
import eu.teama.drdaycare.additionalDetails.AdditionalDetails;
import eu.teama.drdaycare.additionalDetails.AdditionalDetailsManager;
import eu.teama.drdaycare.admin.AdminManager;
import eu.teama.drdaycare.admin.UserListResponse;
import eu.teama.drdaycare.Prescription.PrescriptionManager;

import eu.teama.drdaycare.comment.CommentManager;
import eu.teama.drdaycare.comment.CommentRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class HttpController {
    Logger logger = LoggerFactory.getLogger(HttpController.class);

    @Autowired
    private LoginManager loginManager;

    @Autowired
    private MedicalStaffManager medicalStaffManager;
  
    @Autowired
    private PrescriptionManager prescriptionManager;

    @Autowired
    private AdminManager adminManager;

    @Autowired
    private CommentManager commentManager;

    @Autowired
    private AdditionalDetailsManager additionalDetailsManager;

    //Takes a POST request over at address $System_IP/login (ie http://localhost:8080/login if run on local system) with a JSON login request in the body
    //Method takes in a loginRequest, gives information to LoginManager and then returns the loginResponse it receives from the manager.
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse checkLogin(@RequestBody LoginRequest loginRequest) throws SQLException {
        logger.info("HTTP client received Login Request" + loginRequest.getName() + " " + loginRequest.getPassword());
        return loginManager.checkLogin(loginRequest);
    }

    @RequestMapping(value = "/MedicalStaff/allPatientdetails", method = RequestMethod.GET)
    @CrossOrigin(origins = "http://localhost:63342")
    public List<Patient> getAllPatientDetails() throws SQLException {
        logger.info("HTTP client received Login Request");
        return medicalStaffManager.getPatientList();}

    //Takes a POST request over at address $System_IP/login (ie http://localhost:8080/prescription if run on local system) with a JSON prescription request in the body
    //Method takes in a Prescription Request, gives information to PrescriptionManager and then returns the loginResponse it receives from the manager.
    @RequestMapping(value = "/prescription", method = RequestMethod.POST)
    public PrescriptionResponse getPrescription(@RequestBody PrescriptionRequest prescriptionRequest) throws SQLException {
        logger.info("HTTP client received Prescription Request" + prescriptionRequest.getPatient_id());
        return prescriptionManager.getPrescription(prescriptionRequest);
    }
    
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public ResponseEntity<UserListResponse> getUsers() {
    	 logger.info("HTTP client received AllUsers Request");
    	 HttpHeaders responseHeaders = new HttpHeaders();
    	   responseHeaders.set("GetUsers", "Valid");

    	return new ResponseEntity<UserListResponse>(adminManager.getAllUsers(), responseHeaders, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> addUser(@RequestBody Map<String, Object> payload) {
        String id = (String)payload.get("id");
        String name = (String )payload.get("name");
        String userRole = (String) payload.get("type");

        String email = (String) payload.get("email");
        String password = "test";
        User user = new User(Integer.parseInt(id), name, Integer.parseInt(userRole), email, password);
    	adminManager.addUser(user);
    	Map<String, String> map = new HashMap<>();
    	map.put("result", "success");
    	return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void deleteUser(@RequestParam(name = "id") String id) {
    	 logger.info("HTTP client received Delete-User Request");
    	adminManager.deleteUser(Integer.parseInt(id));
    }

    @RequestMapping(value = "/pharmacist/getAllPrescriptions", method = RequestMethod.GET)
    public List<PrescriptionDetail> getAllPrescriptions() {
        logger.info("HTTP client received request to get all prescriptions");
        List<PrescriptionDetail> prescriptionDetailsList = prescriptionManager.getAllPrescriptionDetails();
        logger.info("Returning list");
        return prescriptionDetailsList;
    }

    @RequestMapping(value = "/pharmacist/addComment", method = RequestMethod.POST)
    public void addComment(@RequestBody CommentRequest commentRequest) {
        logger.info("HTTP client received request to add comment");
        commentManager.addComment(commentRequest);
    }

    @RequestMapping(value = "/doctor/getPatientAdditionalDetails/{patientId}", method = RequestMethod.GET)
    public List<AdditionalDetails> getPatientDetails(@PathVariable ("patientId") final String patientId) {
        logger.info("HTTP client received request to get additional details for patient with id: " + patientId);
        List<AdditionalDetails> additionalDetailsList = additionalDetailsManager.getAdditionalDetailsForPatient(patientId);
        logger.info("" + additionalDetailsList.size());

        return additionalDetailsList;
    }
}



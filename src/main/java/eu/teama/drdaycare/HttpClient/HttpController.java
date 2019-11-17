package eu.teama.drdaycare.HttpClient;

import eu.teama.drdaycare.EmergencyId.EmergencyIdManager;
import eu.teama.drdaycare.EmergencyId.json.EmergencyIdRequest;
import eu.teama.drdaycare.EmergencyId.json.EmergencyIdResponse;

import eu.teama.drdaycare.Login.jsonData.LoginRequest;
import eu.teama.drdaycare.Login.jsonData.LoginResponse;
import eu.teama.drdaycare.Login.LoginManager;

import eu.teama.drdaycare.Prescription.jsonData.PrescriptionRequest;
import eu.teama.drdaycare.Prescription.jsonData.PrescriptionResponse;
import eu.teama.drdaycare.Prescription.PrescriptionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class HttpController {
    Logger logger = LoggerFactory.getLogger(HttpController.class);
    private final String crossOrigin = "http://localhost:63342";

    @Autowired
    private LoginManager loginManager;

    @Autowired
    private PrescriptionManager prescriptionManager;

    @Autowired
    private EmergencyIdManager emergencyIdManager;

    //Takes a POST request over at address $System_IP/login (ie http://localhost:8080/login if run on local system) with a JSON login request in the body
    //Method takes in a loginRequest, gives information to LoginManager and then returns the loginResponse it receives from the manager.
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @CrossOrigin(origins = crossOrigin)
    public LoginResponse checkLogin(@RequestBody LoginRequest loginRequest) throws SQLException {
        logger.info("HTTP client received Login Request" + loginRequest.getName() + " " + loginRequest.getPassword());
        return loginManager.checkLogin(loginRequest);
    }
    //Takes a POST request over at address $System_IP/prescription(ie http://localhost:8080/prescription if run on local system) with a JSON prescription request in the body
    //Method takes in a Prescription Request, gives information to PrescriptionManager and then returns the prescription Response it receives from the manager.
    @RequestMapping(value = "/prescription", method = RequestMethod.POST)
    @CrossOrigin(origins = crossOrigin)
    public PrescriptionResponse getPrescription(@RequestBody PrescriptionRequest prescriptionRequest) throws SQLException {
        logger.info("HTTP client received Prescription Request" + prescriptionRequest.getPatient_id());
        return prescriptionManager.getPrescription(prescriptionRequest);
    }
    //Takes a POST request over at address $System_IP/emergecyId(ie http://localhost:8080/emergencyId if run on local system) with a JSON emergencyId request in the body
    //Method takes in a EmergencyId Request, gives information to EmergencyIdManager and then returns the emergencyId Response it receives from the manager.
    @RequestMapping(value = "/emergencyId", method = RequestMethod.POST)
    @CrossOrigin(origins = crossOrigin)
    public EmergencyIdResponse getEmergencyId(@RequestBody EmergencyIdRequest emergencyIdRequest) throws SQLException {
        logger.info("HTTP client received EmergencyId Request" + emergencyIdRequest.getPatient_id());
        return EmergencyIdManager.getEmergencyId(emergencyIdRequest);
    }

}

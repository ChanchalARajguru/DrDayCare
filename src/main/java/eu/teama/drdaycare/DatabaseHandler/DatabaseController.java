package eu.teama.drdaycare.DatabaseHandler;
import eu.teama.drdaycare.UserTypes.Prescription;
import eu.teama.drdaycare.UserTypes.User;
import eu.teama.drdaycare.additionalDetails.AdditionalDetails;
import eu.teama.drdaycare.comment.Comment;
import eu.teama.drdaycare.comment.CommentRequest;
import eu.teama.drdaycare.UserTypes.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class DatabaseController {

    Logger logger = LoggerFactory.getLogger(DatabaseController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired PrescriptionRepository prescriptionRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AdditionalDetailsRepository additionalDetailsRepository;

    //Inserting user into repository, making use of the the repository interfaces built in save functionality.
    public void insertUser(User user){
        logger.info("Attempting to add user " + user.getName() + " to database");
        userRepository.save(user);
        logger.info("" + user.getName() + " added to database");
    }

    //Returning single user from repository, found by users id and making use of the the repository interfaces built in save functionality.
    //Note Optional Type is used in the case that no user is found and thus none can be returned.
    public Optional<User> getSingleUser(int id){
        logger.info("Searching for user with id: " + id);
        Optional <User> user =  userRepository.findById(id);
        if (user.isPresent())   logger.info("" + user.get().getName() + "was found");
        else logger.error("Unable to find " + user.get().getName());
        return user;
    }

    //Returning an iterable of all available users, making use of build in repository methods and logging the size of object returned.
    public Iterable<User> getAllUsers(){
        logger.info("Attempting to return all users");
        Iterable<User> users = userRepository.findAll();
        logger.info("Returning " + getIterableSize(users) + " users from repository");
        return users;
    }

    public boolean updateUserStatus(int id, boolean status){
        userRepository.updateUserStatus(id, status);
        return true;
    }

    //Returning an iterable of users based on userRole given. Makes use of custom made query method in userRepository.
    public Iterable<User> getUsersByRole(int userRole) {
        logger.info("Attempting to return all users with user role:" + userRole);
        Iterable<User> users = userRepository.userByRole(userRole);
        logger.info("Returning " + getIterableSize(users) + " many users");
        return users;
    }

    //Delete user from database, which relates to given ID. Makes use of built in repository
    public void deleteUser(int id){
        logger.info("Deleting user with id: " + id);
        userRepository.deleteById(id);
        logger.info("User with id " + id + " was deleted");
    }

    //Updating a single user object in database, making use of built method of repository
    public void updateUser(User user){
        logger.info("User with id " + user.getId() + " added to database");
        userRepository.save(user);
        logger.info("User with id " + user.getId() + " added to database");
    }

    //Method is used internally to get count of iterables for the purpose of logging.
    //If a similar method is required, feel free to overload
    private int getIterableSize(Iterable<User> iterable){
        int count = 0;
        for (Object object: iterable) {
            count++;
        }
        return count;
    }


    public Iterable<Patient> getAllPatients() {
        logger.info("Attempting to return all patients");
        Iterable<Patient> patients = patientRepository.findAll();
        return patients;
    }

    //Returning an iterable of prescriptions based on patient_id given. Makes use of custom made query method in userRepository.
    public Iterable<Prescription> getAllPrescriptions(){
        logger.info("Attempting to return all Prescription");
        Iterable<Prescription> prescriptions = prescriptionRepository.findAll();
        logger.info("Returning  prescriptions from repository");
        return prescriptions;
    }

    public void addComment(Comment comment){
        logger.info("Attempting to add comment to database");
        commentRepository.save(comment);
        logger.info("Comment added to database");
    }

    public Iterable <AdditionalDetails> getAdditionalDetailsForPatient(int patientId) {
        logger.info("Attempting to return additional detials to patient with PatientID:" + patientId);
        Iterable<AdditionalDetails> additionalDetails = additionalDetailsRepository.additionalDetailsForPatient(patientId);
        logger.info("Returning additional details");
        return additionalDetails;
    }
    public void insertPatient(Patient patient){
        logger.info("Attempting to add patient " + patient.getName() + " to database");
        patientRepository.save(patient);
        logger.info("" + patient.getName() + " added to database");
    }
    public void editpatient(@RequestBody Patient patient, int userid){
        Optional <Patient> epatient =  patientRepository.findById(userid);
        System.out.println("epatient"+epatient.get());
        System.out.println("in database : "+epatient.get());
        int id = epatient.get().getUserId();
        int eid = epatient.get().getEmergencyId();
        patient.setEmergencyId(eid);
        patient.setUserId(userid);
        System.out.println("the id: " +userid);
        System.out.println("new entry : "+ patient);
        patientRepository.save(patient);
    }
    public void deletepatient(int userid){
        System.out.println("deleteById "+ userid);
        patientRepository.deleteById(userid);
    }

    public Optional<AdditionalDetails> getDetail(int detailId) {
        logger.info("Getting detail with Id: " + detailId);
        Optional<AdditionalDetails> details = additionalDetailsRepository.findById(detailId);
        return details;
    }

    public void editDetail(AdditionalDetails detail) {
        logger.info("Editing detail with Id: " + detail.getId());
        additionalDetailsRepository.save(detail);
    }

    public void deleteDetail(AdditionalDetails detail) {
        logger.info("Deleting detail with Id: " + detail.getId());
        additionalDetailsRepository.deleteById(detail.getId());
    }

    public void addDetail(AdditionalDetails detail) {
        logger.info("Adding detail with Id: " + detail.getId());
        additionalDetailsRepository.save(detail);
    }

    public Iterable<Comment> getCommentsForPatient(int patientId) {
        logger.info("Attempting to return comments to patient with PatientID:" + patientId);
        Iterable<Comment> comments= commentRepository.commentsForPatient(patientId);
        logger.info("Returning comments");
        return comments;
    }

    public void editComment(Comment comment) {
        logger.info("Editing comment with Id: " + comment.getId());
        commentRepository.save(comment);
    }

    public Optional<Comment> getComment(int commentId) {
        logger.info("Getting detail with Id: " + commentId);
        Optional<Comment> comment = commentRepository.findById(commentId);
        return comment;
    }

    public void deleteComment(Comment comment) {
        logger.info("Deleting comment with Id: " + comment.getId());
        commentRepository.deleteById(comment.getId());
    }

    public Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}




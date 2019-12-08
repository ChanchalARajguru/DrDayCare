package eu.teama.drdaycare.additionalDetails;

import eu.teama.drdaycare.DatabaseHandler.DatabaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdditionalDetailsManager {

    Logger logger = LoggerFactory.getLogger(AdditionalDetailsManager.class);

    @Autowired
    private final DatabaseController databaseController;

    public AdditionalDetailsManager(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    public List<AdditionalDetails> getAdditionalDetailsForPatient(String patientId){
        logger.info("Attempting to get additional details for " + patientId);

        Iterable <AdditionalDetails> detailOfPatient = databaseController.getAdditionalDetailsForPatient(Integer.parseInt(patientId));
        List<AdditionalDetails> additionalDetailsList = turnToList(detailOfPatient);

        if (additionalDetailsList.size() > 0) {
            logger.info("Returning Additional details for " + patientId);
            logger.info("" + additionalDetailsList.size());
            logger.info(additionalDetailsList.get(0).getDetailsText());
        }
        return additionalDetailsList;
    }

    public void deleteDetail(int detailId) {
        logger.info("Attempting to delete detail with id: " + detailId);
        Optional<AdditionalDetails> optionalDetail = databaseController.getDetail(detailId);
        if (optionalDetail.isPresent()) {
            AdditionalDetails detail = optionalDetail.get();
            logger.info("Detail is present");
            databaseController.deleteDetail(detail);
        }
        logger.info("Detail is not present");
    }

    public void editDetail(int detailId, String commentText) {
        logger.info("Attempting to edit detail with id: " + detailId);
        Optional<AdditionalDetails> optionalDetail = databaseController.getDetail(detailId);

        if (optionalDetail.isPresent()) {
            AdditionalDetails detail = optionalDetail.get();
            logger.info("Detail is present");
            detail.setCommentText(commentText);
            databaseController.editDetail(detail);
        }
        logger.info("Detail is not present");
    }

    public void createAdditionalDetails(AdditionalDetailsRequest additionalDetailsRequest) {
        logger.info("Attempting to add new detail");

        AdditionalDetails details = new AdditionalDetails();
        details.setCreatorId(additionalDetailsRequest.getCreatorId());
        details.setPatientId(additionalDetailsRequest.getPatientId());
        details.setCommentText(additionalDetailsRequest.getDetails());

        databaseController.addDetail(details);
    }

    private List<AdditionalDetails> turnToList(Iterable <AdditionalDetails> iterable){
        List list = new ArrayList();

        for (AdditionalDetails object: iterable)
            list.add(object);

        return list;
    }
}

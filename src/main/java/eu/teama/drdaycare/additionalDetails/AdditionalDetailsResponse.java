package eu.teama.drdaycare.additionalDetails;

public class AdditionalDetailsResponse {

    private Iterable<AdditionalDetails> additionalDetailsList;

    public AdditionalDetailsResponse(Iterable <AdditionalDetails> additionalDetails){
        additionalDetailsList = additionalDetails;
    }
}

package eu.teama.drdaycare.comment;

public class CommentRequest {
    private final int patientId;
    private final int creatorId;
    private final String commentText;
    private final boolean visibleToPatient;

    public CommentRequest(int patientId, int creatorId, String commentText, boolean visibleToPatient){
        this.patientId = patientId;
        this.creatorId = creatorId;
        this.commentText = commentText;
        this.visibleToPatient = visibleToPatient;
    }

    public int getPatientId(){
        return patientId;
    }

    public int getCreatorId(){
        return creatorId;
    }

    public String getCommentText(){
        return commentText;
    }

    public boolean isVisibleToPatient(){
        return visibleToPatient;
    }
}

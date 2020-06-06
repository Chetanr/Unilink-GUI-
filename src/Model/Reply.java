package Model;

public class Reply {

    private String postId;
    private String attendeeId;
    private int attendeeCount;
    private String creatorId;
    private String responderId;
    private Double jobOffer;
    private Double saleOffer;

    public Reply(String creatorId, String postId, double jobOffer, double saleOffer, String attendeeId, int attendeeCount) {
        this.creatorId = creatorId;
        this.postId = postId;
        this.jobOffer = jobOffer;
        this.saleOffer = saleOffer;
        this.attendeeCount = attendeeCount;
        this.attendeeId = attendeeId;
    }


    public String getPostId() {
        return postId;
    }

    public String getAttendeeId() {
        return attendeeId;
    }

    public int getAttendeeCount() {
        return attendeeCount;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getResponderId() {
        return responderId;
    }

    public Double getJobOffer() {
        return jobOffer;
    }

    public Double getSaleOffer() {
        return saleOffer;
    }


}

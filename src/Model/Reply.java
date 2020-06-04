package Model;

public class Reply {

    private String postId;
    private int attendeeId;
    private int attendeeCount;
    private String creatorId;
    private String responderId;
    private Double jobOffer;
    private Double saleOffer;

    public Reply(String creatorId, String postId, double jobOffer, double saleOffer, int attendeeId, int attendeeCount) {
        this.creatorId = creatorId;
        this.postId = postId;
        this.jobOffer = jobOffer;
        this.saleOffer = saleOffer;
        this.attendeeCount = attendeeCount;
        this.attendeeId = attendeeId;
    }


}

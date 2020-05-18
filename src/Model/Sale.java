package Model;

public class Sale extends Post {

    private double askingPrice;
    private double highestOffer;
    private double minimumRaise;


    public Sale(String id, String title, String description, double askingPrice, double minimumRaise, String status, String creatorId) {
        super(id, title, description, status, creatorId);
        this.askingPrice = askingPrice;
        this.minimumRaise = minimumRaise;
    }

    @Override
    public boolean handleReply(Reply reply) {
        return false;
    }

    @Override
    public String getReplyDetails() {
        return null;
    }

    @Override
    public void insertDB() {

    }
}

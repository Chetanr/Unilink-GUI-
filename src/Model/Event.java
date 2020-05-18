package Model;

public class Event extends Post{
    private String venue;
    private String date;
    private int capacity;
    private int attendee_count;


    public Event(String id, String title, String description, String venue, String date, int capacity, String status, String creator_id) {
        super(id, title, description, status, creator_id);
        this.venue = venue;
        this.date = date;
        this.capacity = capacity;
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

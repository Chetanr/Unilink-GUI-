package Model;

import hsql_db.ConnectionTest;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;

public class Event extends Post{

    private String venue;
    private String date;
    private int capacity;
    private int attendee_count;
    final String DB_NAME = "Unilink";
    final String TABLE_NAME = "EVENTPOST";


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
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "INSERT INTO " + TABLE_NAME +
                    " VALUES (" + "'"+getCreatorId()+"'" + " ," + "'"+ getPostId()+"'" + " ," + "'"+getTitle()+"'" + " ," + "'"+getDescription()+"'" +
                    " ," +  "'"+getStatus()+"'" + " ," +  "'"+getVenue()+"'" + " ," + "'"+getDate()+"'"  + " ," + "'"+getCapacity()+"'" + " )";

            int result = stmt.executeUpdate(query);

            con.commit();

            System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAttendee_count() {
        return attendee_count;
    }

    public void setAttendee_count(int attendee_count) {
        this.attendee_count = attendee_count;
    }
}

package Model;

import hsql_db.ConnectionTest;

import java.sql.Connection;
import java.sql.Statement;

public class Event extends Post{

    private String venue;
    private String date;
    private int capacity;
    private int attendee_count;
    private final String DB_NAME = "Unilink";
    private final String TABLE_NAME = "EVENTPOST";



    public Event(String id, String title, String description, String venue, String date, int capacity, String status, String creator_id, String fileName) {
        super(id, title, description, status, creator_id, fileName);
        this.venue = venue;
        this.date = date;
        this.capacity = capacity;
    }

    public Event(String post_id, String title, String description, String status, String venue, String date, String image_name) {
        super(post_id, title, description, status, image_name);
        this.venue = venue;
        this.date = date;
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
            generateId();
            String query = "INSERT INTO " + TABLE_NAME +
                    " VALUES ( " + "'" + getCreatorId() + "'" + " ," + "'" + getPostId() + "'" + " ," + "'" + getTitle() + "'" + " ," + "'" + getDescription() + "'" +
                    " ," +  "'" + getStatus() + "'" + " ," +  "'" + getVenue() + "'" + " ," + "'" + getDate() + "'"  + " ," + "'" + getCapacity() + "'" + " ,"
                    +  "'" + getFileName() + "'" + " )";

            int result = stmt.executeUpdate(query);

            con.commit();

            System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }





    @Override
    public void generateId()
    {
        String temp = getPostId();
        temp = "EVE" + (Integer.parseInt(temp.substring(3)) + 1);
        setPostId(temp);
    }


    public String getVenue() {
        return venue;
    }

    public String getDate() {
        return date;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAttendee_count() {
        return attendee_count;
    }

}

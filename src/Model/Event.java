package Model;

import Database.ConnectionTest;

import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class Event extends Post{

    private String venue;
    private String date;
    private int capacity;
    private int attendeeCount;
    private final String DB_NAME = "Unilink";
    private final String TABLE_NAME = "EVENTPOST";
    private final String REPLY_TABLE = "REPLY";



    public Event(String id, String title, String description, String venue, String date, int capacity, String status, String creator_id, String fileName) {
        super(id, title, description, status, creator_id, fileName);
        this.venue = venue;
        this.date = date;
        this.capacity = capacity;
        this.attendeeCount = 0;
    }

//    public Event(String creatorId, String postId, String title, String description, String status, String venue, String date, String imageName) {
//        super(postId, title, description, status,creatorId, imageName);
//        this.venue = venue;
//        this.date = date;
//    }

    public Event(String creatorId, String postId, String title, String description, String status, String venue, String date, int attendeeCount, String imageName) {
        super(postId, title, description, status, creatorId, imageName);
        this.venue = venue;
        this.attendeeCount = attendeeCount;
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
                    +  "'" + getAttendeeCount() + "'" + "," +"'" + getFileName() + "'" + " )";

            int result = stmt.executeUpdate(query);

            con.commit();

            System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void insertReplies(String user) throws duplicateReplyException {
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "INSERT INTO " + REPLY_TABLE +
                    " (creator_id, post_id, attendee_id, attendee_count) VALUES ( " + "'" + getCreatorId() + "'" + " ," + "'" + getPostId() + "'" + " ," +"'"
                    + user + "'" + " ," +  "'" + getAttendeeCount() + "'" + " )";

            int result = stmt.executeUpdate(query);

            con.commit();

            System.out.println("Insert into table " + REPLY_TABLE + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            if(e instanceof SQLIntegrityConstraintViolationException)
            {
                throw new duplicateReplyException();
            }
        }
    }

    public void updatePost()
    {
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "UPDATE " + TABLE_NAME +
                    " SET attendee_count = " + "'" + getAttendeeCount() + "'"
                    + " WHERE post_id = " + "'" +getPostId() + "'";

            int result = stmt.executeUpdate(query);

            System.out.println("Update table " + TABLE_NAME + " executed successfully");
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


    public int getAttendeeCount() {
        return this.attendeeCount;
    }


    public void setAttendeeCount(int attendee_count) {
        this.attendeeCount = this.attendeeCount + attendee_count;
    }

}

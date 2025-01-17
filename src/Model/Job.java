package Model;

import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class Job extends Post{



    private double proposed_price;
    private double lowest_offer;

    private final String DB_NAME = "Unilink";
    private final String TABLE_NAME = "JOBPOST";
    private final String REPLY_TABLE = "REPLY";

    public Job(String id, String title, String description, double proposedPrice, String status, String creator_id, String fileName) {
        super(id, title, description, status, creator_id, fileName);
        this.proposed_price = proposedPrice;
        this.lowest_offer = proposedPrice;
    }

    public Job(String creatorId, String post_id, String title, String description, String status, double proposed_offer, String image_name) {
        super(post_id, title, description, status, creatorId, image_name);
        this.proposed_price = proposed_offer;
    }


    @Override
    public boolean handleReply(Reply reply) {
        return false;
    }


    @Override
    public String getReplyDetails() {
        return null;
    }


    //insert new post details into database
    @Override
    public void insertDB() {
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            generateId();
            String query = "INSERT INTO " + TABLE_NAME +
                    " VALUES ( " + "'" + getCreatorId() + "'" + " ," + "'" + getPostId() + "'" + " ," + "'" + getTitle() + "'" + " ," + "'" + getDescription() + "'" +
                    " ," +  "'" + getStatus() + "'" + " ," +  "'" + getProposedPrice() + "'"  + " ," + "'" + getFileName() + "'" + " )";
            int result = stmt.executeUpdate(query);

            con.commit();

            System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
        }

    }

    @Override
    public void insertReplies(String user) throws duplicateReplyException {
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "INSERT INTO " + REPLY_TABLE +
                    " (creator_id, post_id, job_offer, attendee_id) VALUES ( " + "'" + getCreatorId() + "'" + " ," + "'" + getPostId() + "'" + " ," + "'" + getLowestOffer() + "'" + " ," +
                    "'" + user + "'" + " )";

            int result = stmt.executeUpdate(query);

            con.commit();

            System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            if(e instanceof SQLIntegrityConstraintViolationException)
            {
                throw new duplicateReplyException();
            }
        }
    }

    @Override
    public void generateId()
    {
        String temp = getPostId();
        temp = "JOB" + (Integer.parseInt(temp.substring(3)) + 1);
        setPostId(temp);
    }


    public double getProposedPrice() {
        return proposed_price;
    }


    public double getLowestOffer() {
        return lowest_offer;
    }

    public void setLowestOffer(double lowest_offer) {
        this.lowest_offer = lowest_offer;
    }


}

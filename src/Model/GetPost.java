/*
This class is used to get the post details
stored in EventPost, SalePost, JobPost and
ReplyPost Table.
 */
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GetPost {
    private final String DB_NAME = "Unilink";
    private final String TABLE_NAME = "EVENTPOST";
    private final String TABLE_NAME2 = "SALEPOST";
    private final String TABLE_NAME3 = "JOBPOST";
    private final String TABLE_NAME4 = "REPLY";


    private ArrayList<Event> eventPost = new ArrayList<Event>();
    private ArrayList<Sale> salePost = new ArrayList<Sale>();
    private ArrayList<Job> jobPost = new ArrayList<Job>();
    private ArrayList<Reply> reply = new ArrayList<Reply>();


    //get the post details from the database
    public void selectDB() {
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = null;

            query = "SELECT creator_id, post_id, title, description, status, venue, date, attendee_count, image_name FROM " + TABLE_NAME;

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    Event event =  new Event(resultSet.getString("creator_id"), resultSet.getString("post_id"), resultSet.getString("title"), resultSet.getString("description"), resultSet.getString("status"),
                            resultSet.getString("venue"), resultSet.getString("date"),resultSet.getInt("attendee_count"), resultSet.getString("image_name"));
                    eventPost.add(event);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            query = "SELECT creator_id, post_id, title, description, status, proposed_offer, image_name FROM " + TABLE_NAME3;
            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    Job job = new Job(resultSet.getString("creator_id"), resultSet.getString("post_id"), resultSet.getString("title"), resultSet.getString("description"), resultSet.getString("status"),
                            resultSet.getDouble("proposed_offer"), resultSet.getString("image_name"));
                    jobPost.add(job);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            query = "SELECT creator_id, post_id, title, description, status, asking_price, image_name FROM " + TABLE_NAME2;
            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    Sale sale = new Sale(resultSet.getString("creator_id"), resultSet.getString("post_id"), resultSet.getString("title"), resultSet.getString("description"), resultSet.getString("status"),
                            resultSet.getDouble("asking_price"), resultSet.getString("image_name"));
                    salePost.add(sale);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            query = "SELECT creator_id, post_id, job_offer, sale_offer, attendee_id, attendee_count FROM " + TABLE_NAME4;
            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    Reply reply1= new Reply(resultSet.getString("creator_id"), resultSet.getString("post_id"), resultSet.getDouble("job_offer"), resultSet.getDouble("sale_offer"),
                            resultSet.getString("attendee_id"), resultSet.getInt("attendee_count"));
                    reply.add(reply1);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public ArrayList<Event> getEventPost() {
        return eventPost;
    }


    public ArrayList<Sale> getSalePost() {
        return salePost;
    }


    public ArrayList<Job> getJobPost() {
        return jobPost;
    }


    public ArrayList<Reply> getReply() {
        return reply;
    }
}

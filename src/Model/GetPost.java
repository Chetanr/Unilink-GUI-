package Model;

import hsql_db.ConnectionTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GetPost {
    private StringBuilder eventPosts = new StringBuilder();
    private StringBuilder salePosts = new StringBuilder();
    private StringBuilder jobPosts = new StringBuilder();
    private final String DB_NAME = "Unilink";
    private final String TABLE_NAME = "EVENTPOST";
    private final String TABLE_NAME2 = "SALEPOST";
    private final String TABLE_NAME3 = "JOBPOST";
    private ArrayList<Event> eventPost = new ArrayList<Event>();
    private ArrayList<Sale> salePost = new ArrayList<Sale>();
    private ArrayList<Job> jobPost = new ArrayList<Job>();

    public void selectDB() {
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = null;

            query = "SELECT post_id, title, description, status, venue, date, image_name FROM " + TABLE_NAME;

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    eventPosts.append(resultSet.getString("post_id") + " " + resultSet.getString("title") + " " + resultSet.getString("description") +
                            " " + resultSet.getString("status") + " " + resultSet.getString("venue") + " " + resultSet.getString("date") + " " + resultSet.getString("image_name") + " | ");
                    Event event =  new Event(resultSet.getString("post_id"), resultSet.getString("title"), resultSet.getString("description"), resultSet.getString("status"),
                            resultSet.getString("venue"), resultSet.getString("date"),resultSet.getString("image_name"));
                    eventPost.add(event);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            query = "SELECT post_id, title, description, status, proposed_offer, image_name FROM " + TABLE_NAME3;
            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    jobPosts.append(resultSet.getString("post_id") + " " + resultSet.getString("title") + " " + resultSet.getString("description") +
                            " " + resultSet.getString("status") + " " + resultSet.getDouble("proposed_offer") + " " + resultSet.getString("image_name") + " | ");
                    Job job = new Job(resultSet.getString("post_id"), resultSet.getString("title"), resultSet.getString("description"), resultSet.getString("status"),
                            resultSet.getDouble("proposed_offer"), resultSet.getString("image_name"));
                    jobPost.add(job);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            query = "SELECT post_id, title, description, status, asking_price, image_name FROM " + TABLE_NAME2;
            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
                    salePosts.append(resultSet.getString("post_id") + " " + resultSet.getString("title") + " " + resultSet.getString("description") +
                            " " + resultSet.getString("status") + " " + resultSet.getDouble("asking_price") + " " + resultSet.getString("image_name") + " | ");
                    Sale sale = new Sale(resultSet.getString("post_id"), resultSet.getString("title"), resultSet.getString("description"), resultSet.getString("status"),
                            resultSet.getDouble("asking_price"), resultSet.getString("image_name"));
                    salePost.add(sale);
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public StringBuilder getEventPosts() {
        return eventPosts;
    }


    public StringBuilder getSalePosts() {
        return salePosts;
    }

    public StringBuilder getJobPosts() {
        return jobPosts;
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



}

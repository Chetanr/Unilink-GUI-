package Model;

import hsql_db.ConnectionTest;

import java.sql.Connection;
import java.sql.Statement;

public class Job extends Post{



    private double proposed_price;
    private double lowest_offer;

    private final String DB_NAME = "Unilink";
    private final String TABLE_NAME = "SALEPOST";

    public Job(String id, String title, String description, double proposedPrice, String status, String creator_id, String fileName) {
        super(id, title, description, status, creator_id, fileName);
        this.proposed_price = proposedPrice;
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
                    " VALUES (" + "'" + getCreatorId() + "'" + " ," + "'" + getPostId() + "'" + " ," + "'" + getTitle() + "'" + " ," + "'" + getDescription() + "'" +
                    " ," +  "'" + getStatus() + "'" + " ," +  "'" + getProposedPrice() + "'"  + " ," + "'" + getFileName() + "'" + " )";
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
        temp = "JOB" + (Integer.parseInt(temp.substring(3)) + 1);
        setPostId(temp);
    }

    public double getProposedPrice() {
        return proposed_price;
    }


}

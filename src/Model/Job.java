package Model;

import hsql_db.ConnectionTest;

import java.sql.Connection;
import java.sql.Statement;

public class Job extends Post{

    private int proposed_price;
    private int lowest_offer;


    public Job(String id, String title, String description, int proposedPrice, String status, String creator_id) {
        super(id, title, description, status, creator_id);
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
        final String DB_NAME = "Unilink";
        final String TABLE = "Post";

        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "INSERT INTO " + TABLE +
                    " VALUES (1, 's3388490', 'Peter', 'Tilmanis')";
            //" VALUES (2, 's3089940', 'Tom', 'Bruster')";

            int result = stmt.executeUpdate(query);

            con.commit();

            System.out.println("Insert into table " + TABLE + " executed successfully");
            System.out.println(result + " row(s) affected");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

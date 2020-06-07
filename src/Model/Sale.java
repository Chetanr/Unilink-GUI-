package Model;

import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

public class Sale extends Post {

    private double askingPrice;
    private double highestOffer;
    private double minimumRaise;
    private final String DB_NAME = "Unilink";
    private final String TABLE_NAME = "SALEPOST";
    private final String REPLY_TABLE = "REPLY";


    public Sale(String id, String title, String description, double askingPrice, double minimumRaise, String status, String creatorId, String fileName) {
        super(id, title, description, status, creatorId, fileName);
        this.askingPrice = askingPrice;
        this.minimumRaise = minimumRaise;
    }

    public Sale(String creator_id, String post_id, String title, String description, String status, double asking_price, String image_name) {
        super(post_id, title, description, status, creator_id, image_name);
        this.askingPrice = asking_price;
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
                    " VALUES (" + "'" + getCreatorId() + "'" + " ," + "'" + getPostId() + "'" + " ," + "'" + getTitle() + "'" + " ," + "'" + getDescription() + "'" +
                    " ," +  "'" + getStatus() + "'" + " ," +  "'" + getAskingPrice() + "'" + " ," + "'" + getMinimumRaise() + "'"  + " ," + "'" + getFileName() + "'"
                    + " )";

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
//            generateId();
            String query = "INSERT INTO " + REPLY_TABLE +
                    " (creator_id, post_id, sale_offer, attendee_id) VALUES ( " + "'" + getCreatorId() + "'" + " ," + "'" + getPostId() + "'" + " ," + "'" + getHighestOffer() + "'" +
                    " ," + "'" + user + "'" + " )";

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



    @Override
    public void generateId()
    {
        String temp = getPostId();
        temp = "SAL" + (Integer.parseInt(temp.substring(3)) + 1);
        setPostId(temp);
    }





    public double getAskingPrice() {
        return askingPrice;
    }


    public double getMinimumRaise() {
        return minimumRaise;
    }


    public double getHighestOffer() {
        return highestOffer;
    }


    public void setHighestOffer(double highestOffer) {
        this.highestOffer = highestOffer;
    }
}

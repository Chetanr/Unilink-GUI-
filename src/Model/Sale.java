package Model;

import hsql_db.ConnectionTest;

import java.sql.Connection;
import java.sql.Statement;

public class Sale extends Post {

    private double askingPrice;
    private double highestOffer;
    private double minimumRaise;
    private final String DB_NAME = "Unilink";
    private final String TABLE_NAME = "SALEPOST";


    public Sale(String id, String title, String description, double askingPrice, double minimumRaise, String status, String creatorId, String fileName) {
        super(id, title, description, status, creatorId, fileName);
        this.askingPrice = askingPrice;
        this.minimumRaise = minimumRaise;
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
            System.out.println(e.getMessage());
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
}

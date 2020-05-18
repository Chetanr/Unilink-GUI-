package hsql_db;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	public static void main(String[] args) throws SQLException {

		final String DB_NAME = "Unilink";
		final String TABLE1 = "Post";
		final String TABLE2 = "Reply";

		createTable(TABLE1, DB_NAME);
		createReplyTable(TABLE2, DB_NAME);
	}


	//create post table to store all the posts
	private static void createTable(String table1, String DB_NAME) {
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
			 Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE post ("
					+ "creator_id VARCHAR(10) NOT NULL,"
					+ "post_id VARCHAR(8) NOT NULL,"
					+ "title VARCHAR(20) ,"
					+ "description VARCHAR(200) ,"
					+ "asking_price int,"
					+ "status VARCHAR(6),"
					+ "venue VARCHAR(10),"
					+ "date DATE,"
					+ "capacity int,"
					+ "PRIMARY KEY (post_id))");
			if(result == 0) {
				System.out.println("Table " + table1 + " has been created successfully");
			} else {
				System.out.println("Table " + table1 + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//create reply table
	private static void createReplyTable(String table2, String db_name) {
		try (Connection con = ConnectionTest.getConnection(db_name);
			 Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE Reply ("
					+ "creator_id VARCHAR(10) NOT NULL,"
					+ "post_id VARCHAR(8) NOT NULL,"
					+ "job_offer int,"
					+ "sale_offer int,"
					+ "PRIMARY KEY (post_id))");
			if(result == 0) {
				System.out.println("Table " + table2 + " has been created successfully");
			} else {
				System.out.println("Table " + table2 + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

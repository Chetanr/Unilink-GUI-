package Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	public static void main(String[] args) throws SQLException {

		final String DB_NAME = "Unilink";
		final String EVENT_TABLE = "EVENTPOST";
		final String SALE_TABLE = "SALEPOST";
		final String JOB_TABLE = "JOBPOST";
		final String REPLY_TABLE = "REPLY";

		createEventTable(EVENT_TABLE, DB_NAME);
		createSaleTable(SALE_TABLE, DB_NAME);
		createJobTable(JOB_TABLE, DB_NAME);
		createReplyTable(REPLY_TABLE, DB_NAME);
	}

	private static void createJobTable(String JOB_TABLE, String DB_NAME) {
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
			 Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE " + JOB_TABLE + "("
					+ "creator_id VARCHAR(10) NOT NULL,"
					+ "post_id VARCHAR(8) NOT NULL,"
					+ "title VARCHAR(20) NOT NULL,"
					+ "description VARCHAR(200) NOT NULL,"
					+ "status VARCHAR(6) NOT NULL,"
					+ "proposed_offer DECIMAL(5,2) NOT NULL,"
					+ "image_name VARCHAR(100),"
					+ "PRIMARY KEY (post_id))");
			if(result == 0) {
				System.out.println("Table " + JOB_TABLE + " has been created successfully");
			} else {
				System.out.println("Table " + JOB_TABLE + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void createSaleTable(String SALE_TABLE, String DB_NAME) {
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
			 Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE " + SALE_TABLE + "("
					+ "creator_id VARCHAR(10) NOT NULL,"
					+ "post_id VARCHAR(8) NOT NULL,"
					+ "title VARCHAR(20) NOT NULL,"
					+ "description VARCHAR(200) NOT NULL,"
					+ "status VARCHAR(6) NOT NULL,"
					+ "asking_price DECIMAL(6,2) NOT NULL,"
					+ "minimum_raise DECIMAL(6,2) NOT NULL,"
					+ "image_name VARCHAR(100),"
					+ "PRIMARY KEY (post_id))");
			if(result == 0) {
				System.out.println("Table " + SALE_TABLE + " has been created successfully");
			} else {
				System.out.println("Table " + SALE_TABLE + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	//create post table to store all the posts
	private static void createEventTable(String EVENT_TABLE, String DB_NAME) {
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
			 Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE " + EVENT_TABLE + "("
					+ "creator_id VARCHAR(10) NOT NULL,"
					+ "post_id VARCHAR(8) NOT NULL,"
					+ "title VARCHAR(20) NOT NULL,"
					+ "description VARCHAR(200) NOT NULL,"
					+ "status VARCHAR(6) NOT NULL,"
					+ "venue VARCHAR(10) NOT NULL,"
					+ "date DATE NOT NULL,"
					+ "capacity int NOT NULL,"
					+ "image_name VARCHAR(100),"
					+ "PRIMARY KEY (post_id))");
			if(result == 0) {
				System.out.println("Table " + EVENT_TABLE + " has been created successfully");
			} else {
				System.out.println("Table " + EVENT_TABLE + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//create reply table
	private static void createReplyTable(String REPLY_TABLE, String db_name) {
		try (Connection con = ConnectionTest.getConnection(db_name);
			 Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE " + REPLY_TABLE + "("
					+ "creator_id VARCHAR(10) NOT NULL,"
					+ "post_id VARCHAR(8) NOT NULL,"
					+ "job_offer DECIMAL(6,2),"
					+ "sale_offer DECIMAL(6,2),"
					+ "attendee_id VARCHAR(10),"
					+ "attendee_count int,"
					+ "PRIMARY KEY (post_id))");
			if(result == 0) {
				System.out.println("Table " + REPLY_TABLE + " has been created successfully");
			} else {
				System.out.println("Table " + REPLY_TABLE + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

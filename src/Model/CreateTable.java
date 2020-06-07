package Model;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTable {


	static final String DB_NAME = "Unilink";
	static final String EVENT_TABLE = "EVENTPOST";
	static final String SALE_TABLE = "SALEPOST";
	static final String JOB_TABLE = "JOBPOST";
	static final String REPLY_TABLE = "REPLY";

	public static void createJobTable() {
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

	public static void createSaleTable() {
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
	public static void createEventTable() {
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
					+ "attendee_count int NOT NULL,"
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
	public static void createReplyTable() {
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
			 Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE " + REPLY_TABLE + "("
					+ "creator_id VARCHAR(10) NOT NULL,"
					+ "post_id VARCHAR(8) NOT NULL,"
					+ "job_offer DECIMAL(6,2),"
					+ "sale_offer DECIMAL(6,2),"
					+ "attendee_id VARCHAR(10),"
					+ "attendee_count int)");
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

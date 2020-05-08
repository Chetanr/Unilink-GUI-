package hsql_db;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	public static void main(String[] args) throws SQLException {

		final String DB_NAME = "Unilink";
		final String TABLE1 = "SALE";
		final String TABLE2 = "EVENT";
		final String TABLE3 = "JOB";
		final String TABLE4 = "REPLY";

		createSaleTable(TABLE1, DB_NAME);
		createEventTable(TABLE2, DB_NAME);
		createJobTable(TABLE3, DB_NAME);
		createReplyTable(TABLE4, DB_NAME);
	}


	//create sale table
	private static void createSaleTable(String table1, String DB_NAME) {
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
			 Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE sale ("
					+ "creator_id VARCHAR(10) NOT NULL,"
					+ "post_id VARCHAR(8) NOT NULL,"
					+ "title VARCHAR(20) NOT NULL,"
					+ "description VARCHAR(200) NOT NULL,"
					+ "asking_price int,"
					+ "status VARCHAR(6),"
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


	//create event table
	private static void createEventTable(String table2, String DB_NAME) {

		try (Connection con = ConnectionTest.getConnection(DB_NAME);
			 Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE event ("
					+ "creator_id VARCHAR(10) NOT NULL,"
					+ "post_id VARCHAR(8) NOT NULL,"
					+ "title VARCHAR(20) NOT NULL,"
					+ "description VARCHAR(200) NOT NULL,"
					+ "venue VARCHAR(10) NOT NULL,"
					+ "date DATE NOT NULL,"
					+ "capacity int NOT NULL,"
					+ "status VARCHAR(6),"
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


	//create job table
	private static void createJobTable(String table3, String DB_NAME) {

		try (Connection con = ConnectionTest.getConnection(DB_NAME);
			 Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE job ("
					+ "creator_id VARCHAR(10) NOT NULL,"
					+ "post_id VARCHAR(8) NOT NULL,"
					+ "title VARCHAR(20) NOT NULL,"
					+ "description VARCHAR(200) NOT NULL,"
					+ "price int,"
					+ "status VARCHAR(6),"
					+ "PRIMARY KEY (post_id))");
			if(result == 0) {
				System.out.println("Table " + table3 + " has been created successfully");
			} else {
				System.out.println("Table " + table3 + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	//create reply table
	private static void createReplyTable(String table4, String db_name) {
	}
}

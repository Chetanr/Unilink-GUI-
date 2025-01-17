package Database;
import Model.ConnectionTest;

import java.sql.Connection;
import java.sql.Statement;

public class UpdateQuery {
	public static void main(String[] args) {
		final String DB_NAME = "Unilink";
		final String TABLE_NAME = "STUDENT";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
		) {
			String query = "UPDATE " + TABLE_NAME +
					" SET last_name = 'Singleton'" +
					" WHERE student_number LIKE 's3388490'";
			
			int result = stmt.executeUpdate(query);
			
			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

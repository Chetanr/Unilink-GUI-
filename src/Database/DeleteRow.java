package Database;
import Model.ConnectionTest;

import java.sql.Connection;
import java.sql.Statement;

public class DeleteRow {
	public static void main(String[] args) {
		final String DB_NAME = "Unilink";
		final String TABLE_NAME = "STUDENT";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
		) {
			String query = "DELETE FROM " + TABLE_NAME + 
					" WHERE first_name LIKE 'Tom'";
			
			int result = stmt.executeUpdate(query);
			
			System.out.println("Delete from table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

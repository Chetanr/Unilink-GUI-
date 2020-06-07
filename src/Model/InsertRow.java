package Model;

import java.sql.Connection;
import java.sql.Statement;

public class InsertRow {
	public static void main(String[] args) {
		final String DB_NAME = "Unilink";
		final String TABLE_NAME = "POST";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
		) {
			String query = "INSERT INTO " + TABLE_NAME +
					" VALUES ('EVE01', 'asdasd', 'asdasdasd', 'asdasdas', '')";
					//" VALUES (2, 's3089940', 'Tom', 'Bruster')";
			
			int result = stmt.executeUpdate(query);
			
			con.commit();
			
			System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

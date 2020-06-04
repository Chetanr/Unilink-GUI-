package Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteTable {
	public static void main(String[] args) throws SQLException {
		
		final String DB_NAME = "Unilink";
		final String TABLE_NAME = "REPLY";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("DROP TABLE reply");
			
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME + " has been deleted successfully");
			} else {
				System.out.println("Table " + TABLE_NAME + " was not deleted");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

package Database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectQuery {
	public static void main(String[] args) {
		final String DB_NAME = "Unilink";
		final String TABLE_NAME = "EVENTPOST";
		
		//use try-with-resources Statement
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "SELECT post_id, title, description, status, venue, date, creator_id, image_name FROM " + TABLE_NAME;


			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
//					System.out.printf("Id: %d | Student Number: %s | First Name: %s | Last Name: %s\n",
//							resultSet.getInt("id"), resultSet.getString("student_number"),
//							resultSet.getString("first_name"), resultSet.getString("last_name"));
					System.out.println(resultSet.getString("post_id") + " " + resultSet.getString("title") + " " + resultSet.getString("description") +
							" " + resultSet.getString("status") + " " + resultSet.getString("venue") + " " + resultSet.getString("date") + " " + resultSet.getString("image_name")
					+  " " + resultSet.getString("creator_id"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

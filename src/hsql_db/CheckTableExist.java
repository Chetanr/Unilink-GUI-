package hsql_db;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckTableExist {
	public static void main(String[] args) throws SQLException {

		final String DB_NAME = "Unilink";

		final String TABLE1 = "SALE";
		final String TABLE2 = "EVENT";
		final String TABLE3 = "JOB";

		checkSale(TABLE1, DB_NAME);
		checkEvent(TABLE2, DB_NAME);
		checkJob(TABLE3, DB_NAME);


	}

	//check if sale table exists
	private static void checkSale(String table1, String db_name) {
		try (Connection con = ConnectionTest.getConnection(db_name)) {

			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, table1.toUpperCase(), null);

			if(tables != null) {
				if (tables.next()) {
					System.out.println("Table " + table1 + " exists.");
				}
				else {
					System.out.println("Table " + table1 + " does not exist.");
				}
				tables.close();
			} else {
				System.out.println(("Problem with retrieving database metadata"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	//check if event table exists or not
	private static void checkEvent(String table2, String db_name) {
		try (Connection con = ConnectionTest.getConnection(db_name)) {

			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, table2.toUpperCase(), null);

			if(tables != null) {
				if (tables.next()) {
					System.out.println("Table " + table2 + " exists.");
				}
				else {
					System.out.println("Table " + table2 + " does not exist.");
				}
				tables.close();
			} else {
				System.out.println(("Problem with retrieving database metadata"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	//check if job table exists or not
	private static void checkJob(String table3, String db_name) {
		try (Connection con = ConnectionTest.getConnection(db_name)) {

			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, table3.toUpperCase(), null);

			if(tables != null) {
				if (tables.next()) {
					System.out.println("Table " + table3 + " exists.");
				}
				else {
					System.out.println("Table " + table3 + " does not exist.");
				}
				tables.close();
			} else {
				System.out.println(("Problem with retrieving database metadata"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}



}

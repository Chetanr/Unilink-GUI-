package Model;

import hsql_db.ConnectionTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenerateId {
    private String pid;
    private final String DB_NAME = "Unilink";
    private final String TABLE_NAME = "EVENTPOST";
    private final String TABLE_NAME2 = "SALEPOST";
    private final String TABLE_NAME3 = "JOBPOST";

    public String getId()
    {
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "SELECT post_id FROM " + TABLE_NAME + " order by post_id DESC LIMIT 1";

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
//					System.out.printf("Id: %d | Student Number: %s | First Name: %s | Last Name: %s\n",
//							resultSet.getInt("id"), resultSet.getString("student_number"),
//							resultSet.getString("first_name"), resultSet.getString("last_name"));
                    pid = resultSet.getString("post_id");
                }
                System.out.println(pid);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            con.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (pid == null)
        {
            return "EVE0";
        }
        return pid;
    }

    public String getSaleId()
    {
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "SELECT post_id FROM " + TABLE_NAME2 + " order by post_id DESC LIMIT 1";

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
//					System.out.printf("Id: %d | Student Number: %s | First Name: %s | Last Name: %s\n",
//							resultSet.getInt("id"), resultSet.getString("student_number"),
//							resultSet.getString("first_name"), resultSet.getString("last_name"));
                    pid = resultSet.getString("post_id");
                }
                System.out.println(pid);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            con.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (pid == null)
        {
            return "SAL0";
        }
        return pid;
    }

    public String getJobId()
    {
        try (Connection con = ConnectionTest.getConnection(DB_NAME);
             Statement stmt = con.createStatement();
        ) {
            String query = "SELECT post_id FROM " + TABLE_NAME3 + " order by post_id DESC LIMIT 1";

            try (ResultSet resultSet = stmt.executeQuery(query)) {
                while(resultSet.next()) {
//					System.out.printf("Id: %d | Student Number: %s | First Name: %s | Last Name: %s\n",
//							resultSet.getInt("id"), resultSet.getString("student_number"),
//							resultSet.getString("first_name"), resultSet.getString("last_name"));
                    pid = resultSet.getString("post_id");
                }
                System.out.println(pid);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            con.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (pid == null)
        {
            return "JOB0";
        }
        return pid;
    }
}

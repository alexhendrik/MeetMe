import java.sql.*;
public class SQL {


        public static void main (String[] args){

            int number = 1;


            String connectionURL = "jdbc:sqlserver://<server>:<port>;databaseName=meetme;user=meetme;password=Re2x?S-Omepy";
            try {
                Connection con = DriverManager.getConnection("jdbc:sqlserver://den1.mssql8.gear.host", "meetme", "Re2x?S-Omepy");

                Statement stmt = con.createStatement();

                String SQL = "SELECT TOP " + number + " * FROM dbo.nameTable";
                ResultSet rs = stmt.executeQuery(SQL);

                // Iterate through the data in the result set and display it.
                while (rs.next()) {
                    System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName") + " " + rs.getInt("StudentID"));
                }
            }catch (SQLException e) {
                System.out.println("Something went wrong.");
            }
        }

    }



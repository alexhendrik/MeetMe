import java.sql.*;
import java.sql.DriverManager;
public class SQL {


        public static void main (String[] args){

            int number = 1;


            try {
                Connection con = DriverManager.getConnection("jdbc:sqlserver://den1.mssql8.gear.host", "meetme", "Re2x?S-Omepy");

                Statement stmt = con.createStatement();

                String SQL = "SELECT TOP " + number + " * FROM dbo.nameTable";
                ResultSet rs = stmt.executeQuery(SQL);

                while (rs.next()) {
                    System.out.println(rs.getString("FirstName") + " " + rs.getString("LastName") + " " + rs.getInt("StudentID"));
                }
            }catch (SQLException e) {
                System.out.println("Something went wrong.");
            }
        }

    }



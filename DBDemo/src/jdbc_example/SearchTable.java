package jdbc_example;

import java.sql.*;

public class SearchTable {
   public static void main(String[] args) {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
      } catch(ClassNotFoundException e) {
         System.out.println("Class not found "+ e);
      }
      try {
         Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/testDb","root", "");
         
         Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
         System.out.println("id  name    job");
         
         while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String job = rs.getString("job");
            System.out.println(id+"   "+name+"    "+job);
         }
      } catch(SQLException e) {
         System.out.println("SQL exception occured" + e);
      }
   }
}
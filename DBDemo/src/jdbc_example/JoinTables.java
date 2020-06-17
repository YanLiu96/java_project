package jdbc_example;

import java.sql.*;

public class JoinTables {
   public static void main(String[] args) throws Exception {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection (
         "jdbc:mysql://localhost:3306/testDb","root", "");
      
      Statement stmt = con.createStatement();
      String query ="SELECT  employees.id, employees.first_name,employees.address from emp inner join employees on emp.id = employees.id";
      ResultSet rs = stmt.executeQuery(query);
      System.out.println("id  name   job");
      
      while (rs.next()) {
         String id = rs.getString("id");
         String name = rs.getString("first_name");
         String job = rs.getString("address");
         System.out.println(id + "  " + name+"   "+job);
      }
      System.out.println();
      System.out.println();
   }
}
package jdbc_example;

import java.sql.*;

public class SortTable {
   public static void main(String[] args) throws Exception {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection (
         "jdbc:mysql://localhost:3306/testDb","root","");
      
      Statement stmt = con.createStatement();
      String query = "select * from emp order by name";
      String query1 = "select * from emp order by name, job";
      
      ResultSet rs = stmt.executeQuery(query);
      System.out.println("Table contents sorted by Name");
      System.out.println("Id Name Job");
      
      while (rs.next()) {
         int id = rs.getInt("id");
         String name = rs.getString("name");
         String job = rs.getString("job");
         System.out.println(id + "  " + name+"   "+job);
      }
      rs = stmt.executeQuery(query1);
      System.out.println("Table contents after sorted by Name & job");
      System.out.println("Id Name    Job");
      
      while (rs.next()) {
         int id = rs.getInt("id");
         String name = rs.getString("name");
         String job = rs.getString("job");
         System.out.println(id + "  " + name+"   "+job);
      }
   }
}
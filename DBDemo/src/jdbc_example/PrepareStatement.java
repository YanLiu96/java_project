package jdbc_example;

import java.sql.*;

public class PrepareStatement {
   public static void main(String[] args) throws Exception {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection ( 
         "jdbc:mysql://localhost:3306/testDb","root","");
      PreparedStatement updateemp = con.prepareStatement(
         "insert into emp values(?,?,?)");
      
      updateemp.setInt(1,23);
      updateemp.setString(2,"Roshan");
      updateemp.setString(3, "CEO");
      updateemp.executeUpdate();
      
      Statement stmt = con.createStatement();
      String query = "select * from emp";
      ResultSet rs =  stmt.executeQuery(query);
      System.out.println("Id Name    Job");
      
      while (rs.next()) {
         int id = rs.getInt("id");
         String name = rs.getString("name");
         String job = rs.getString("job");
         System.out.println(id + "  " + name+"   "+job);
      }      
   }
}

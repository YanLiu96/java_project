package jdbc_example;

import java.sql.*;

public class GetRowNo {
   public static void main(String[] args) throws Exception { 
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(
         "jdbc:mysql://localhost:3306/testDb","root","");
      
      Statement stmt = con.createStatement(
         ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      
      String query = "select * from emp";
      ResultSet rs = stmt.executeQuery(query);
      rs.last();
      System.out.println("No of rows in table = "+rs.getRow());
      
      rs.moveToInsertRow();
      rs.updateInt("id", 9);
      rs.updateString("name","sujay");
      rs.updateString("job", "trainee");
      rs.insertRow();
      
      System.out.println("Row added");
      rs.first();
      rs.deleteRow();
      System.out.println("first row deleted");
   }
}
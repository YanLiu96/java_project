package jdbc_example;

import java.sql.*;

public class Rollback {
   public static void main(String[] args) throws Exception {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(
         "jdbc:mysql://localhost:3306/testDb","root","");
      
      Statement stmt = con.createStatement();
      String query1 = "insert into emp values(5,'name','job')";
      String query2 = "select * from emp";
      
      con.setAutoCommit(false);
      Savepoint spt1 = con.setSavepoint("svpt1");
      stmt.execute(query1);
      ResultSet rs = stmt.executeQuery(query2);
      int no_of_rows = 0;
      
      while (rs.next()) {
         no_of_rows++;
      }
      System.out.println("rows before rollback statement = " + no_of_rows);
      con.rollback(spt1);
      con.commit();
      no_of_rows = 0;
      rs = stmt.executeQuery(query2);
      
      while (rs.next()) {
         no_of_rows++;
      }
      System.out.println("rows after rollback statement = " + no_of_rows);
   }
}

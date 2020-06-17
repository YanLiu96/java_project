package jdbc_example;

import java.sql.*;

public class CommitQuery {
   public static void main(String[] args) throws Exception {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection (
         "jdbc:mysql://localhost:3306/testDb","root","");
      
      Statement stmt = con.createStatement();
      String query = "insert into emp values(2,'name1','job')";
      String query1 ="insert into emp values(5,'name2','job')";
      String query2 = "select * from emp";
      
      ResultSet rs = stmt.executeQuery(query2);
      int no_of_rows = 0;
      
      while (rs.next()) {
         no_of_rows++;
      }
      System.out.println("No. of rows before commit statement = "+ no_of_rows);
      con.setAutoCommit(false);
      stmt.execute(query1);
      stmt.execute(query);
      con.commit();
      rs = stmt.executeQuery(query2);
      no_of_rows = 0;
      
      while (rs.next()) {
         no_of_rows++;
      }
      System.out.println("No. of rows after commit statement = "+ no_of_rows);
      con.close();
   }
}      
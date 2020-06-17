package jdbc_example;

import java.sql.*;

public class MultipleCommands {
   public static void main(String[] args) throws Exception {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(
         "jdbc:mysql://localhost:3306/testDb","root","");
      
      Statement stmt = con.createStatement(
         ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
      
      String insertEmp1 = "insert into emp values(10,'jay','trainee')";
      String insertEmp2 = "insert into emp values(11,'jayes','trainee')";
      String insertEmp3 = "insert into emp values(12,'shail','trainee')";
      con.setAutoCommit(false);
      
      stmt.addBatch(insertEmp1);
      stmt.addBatch(insertEmp2);
      stmt.addBatch(insertEmp3);
      
      ResultSet rs = stmt.executeQuery("select * from emp");
      rs.last();
      System.out.println("rows before batch execution= "+ rs.getRow());
      
      stmt.executeBatch();
      con.commit();
      
      System.out.println("Batch executed");
      rs = stmt.executeQuery("select * from emp");
      rs.last();
      System.out.println("rows after batch execution = "+ rs.getRow());
   }
} 
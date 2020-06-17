package jdbc_example;
import java.sql.*;

public class CreateTable {
   public static void main(String[] args) throws Exception { 
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(
         "jdbc:mysql://localhost:3306/testDb","root", "");
      
      Statement stmt = con.createStatement();
      String query ="CREATE TABLE employees(id INTEGER PRIMARY KEY, first_name CHAR(50), last_name CHAR(75))";
      
      stmt.execute(query);
      System.out.println("Employee table created");
      String query1 = "aLTER TABLE employees ADD address CHAR(100) ";
      String query2 = "ALTER TABLE employees DROP COLUMN last_name";
      
      stmt.execute(query1);
      stmt.execute(query2);
      System.out.println("Address column added to the table & last_name column removed from the table");
      /*
      String query3 = "drop table employees";
      stmt.execute(query3);
      System.out.println("Employees table removed");
      */
   }	
}
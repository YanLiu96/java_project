package jdbc_example;

import java.sql.*;

public class SearchContents {
   public static void main(String[] args) throws Exception {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(
         "jdbc:mysql://localhost:3306/testDb","root", "");
      
      Statement stmt = con.createStatement();
      String query[] = {
         "SELECT * FROM employees where id = 1", 
         "select first_name from employees where first_name like 'ravi_'", 
         "select first_name from employees where first_name like 'ravi%'" 
      };
      
      for(String q : query) {
         ResultSet rs = stmt.executeQuery(q);
         System.out.println("Names for query "+q+" are");
         
         while (rs.next()) {
            String name = rs.getString("first_name");
            System.out.print(name+"  ");
         }
         System.out.println();
      }
   }
}
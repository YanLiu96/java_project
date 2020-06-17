package jdbc_example;

import java.sql.*;

public class UpdateContents {
   public static void main(String[] args) {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
      } catch(ClassNotFoundException e) {
         System.out.println("Class not found "+ e);
      }
      try {
         Connection con = DriverManager.getConnection (
            "jdbc:mysql://localhost:3306/testDb","root", "");
         
         Statement stmt = con.createStatement();
         String query1 = "update employees set first_name = 'ravi' where id = 2";
         String query2 = "delete from employees where id = 1";
         String query3 = "insert into employees values (1,'ronak','dsafsf')";
         
         stmt.execute(query1);
         stmt.execute(query2);
         stmt.execute(query3);
         
         ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
         System.out.println("id   first_name    address");
         
         while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("first_name");
            String job = rs.getString("address");
            System.out.println(id+"   "+name+"    "+job);
         }
      } catch(SQLException e) {
         System.out.println("SQL exception occured" + e);
      }
   }
}
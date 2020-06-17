import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
public class CustomersGui extends JFrame implements ActionListener
{
Statement stmt;
ResultSet rs;
String sql;
Connection con;
 int i=0;
 
 JButton jButton_add;
 JButton jButton_clear;
 JButton jButton_delete;
 JButton jButton_next;
 JButton jButton_previous;
 JButton jButton_update;
 
 JLabel jLabel_address;
 JLabel jLabel_city;
 JLabel jLabel_firstName;
 JLabel jLabel_id;
 JLabel jLabel_lastname;
 JLabel jLabel_phone;
 JLabel jLabel_postalCode;
 JLabel jLabel_state;
 
 JScrollPane jScrollPane2;
 
 JTable jTable_details;
 
 JTextField jTextField_address;
 JTextField jTextField_city;
 JTextField jTextField_firstName;
 JTextField jTextField_id;
 JTextField jTextField_lastName;
 JTextField jTextField_phone;
 JTextField jTextField_postalCode;
 JTextField jTextField_state;

 public CustomersGui()
 {
 jLabel_id = new JLabel("ID");
 jLabel_lastname = new JLabel("Last  Name");
 jLabel_firstName = new JLabel("First Name");
 jLabel_phone = new JLabel("Phone");
 jLabel_address = new JLabel("Address");
 jLabel_city = new JLabel("City");
 jLabel_state = new JLabel("State");
 jLabel_postalCode = new JLabel("Postal Code");
 
 jTextField_id = new JTextField();
 jTextField_firstName = new JTextField();
 jTextField_lastName = new JTextField();
 jTextField_phone = new JTextField();
 jTextField_address = new JTextField();
 jTextField_city = new JTextField();
 jTextField_state = new JTextField();
 jTextField_postalCode = new JTextField();
 
 jScrollPane2 = new JScrollPane();
 jTable_details = new JTable();
 
 jButton_previous = new JButton("Previous");
 jButton_next = new JButton("Next");
 jButton_clear = new JButton("Clear");
 jButton_add = new JButton("Add");
 jButton_delete = new JButton("Delete");
 jButton_update = new JButton("Update");
	 

 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
 jTable_details.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][] {
             {null, null, null, null, null, null, null, null},
             {null, null, null, null, null, null, null, null},
             {null, null, null, null, null, null, null, null},
             {null, null, null, null, null, null, null, null},
             {null, null, null, null, null, null, null, null},
             {null, null, null, null, null, null, null, null},
             {null, null, null, null, null, null, null, null},
             {null, null, null, null, null, null, null, null},
             {null, null, null, null, null, null, null, null}
         },
         new String [] {
             "ID", "First_name", "Last_name", "Phone", "Address", "City", "State", "Postal Code"
         }
     ));
     jScrollPane2.setViewportView(jTable_details);
     if (jTable_details.getColumnModel().getColumnCount() > 0) {
         jTable_details.getColumnModel().getColumn(0).setPreferredWidth(12);
         jTable_details.getColumnModel().getColumn(1).setPreferredWidth(14);
         jTable_details.getColumnModel().getColumn(2).setPreferredWidth(14);
         jTable_details.getColumnModel().getColumn(3).setPreferredWidth(15);
         jTable_details.getColumnModel().getColumn(4).setPreferredWidth(14);
         jTable_details.getColumnModel().getColumn(5).setPreferredWidth(12);
         jTable_details.getColumnModel().getColumn(6).setPreferredWidth(12);
         jTable_details.getColumnModel().getColumn(7).setPreferredWidth(12);
     }
     
 javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
 getContentPane().setLayout(layout);
 layout.setHorizontalGroup(
     layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addGroup(layout.createSequentialGroup()
         .addGap(53, 53, 53)
         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
             .addGroup(layout.createSequentialGroup()
                 .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                 .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                 .addComponent(jButton_update, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
             .addGroup(layout.createSequentialGroup()
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(jLabel_id, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jLabel_firstName)
                     .addComponent(jLabel_lastname)
                     .addComponent(jLabel_phone)
                     .addComponent(jLabel_address)
                     .addComponent(jLabel_city)
                     .addComponent(jLabel_state)
                     .addComponent(jLabel_postalCode))
                 .addGap(44, 44, 44)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                     .addComponent(jTextField_id)
                     .addComponent(jTextField_firstName)
                     .addComponent(jTextField_lastName)
                     .addComponent(jTextField_phone)
                     .addComponent(jTextField_address)
                     .addComponent(jTextField_city)
                     .addComponent(jTextField_state)
                     .addComponent(jTextField_postalCode, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))))
         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGap(18, 18, 18)
                 .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addContainerGap(36, Short.MAX_VALUE))
             .addGroup(layout.createSequentialGroup()
                 .addGap(61, 61, 61)
                 .addComponent(jButton_previous)
                 .addGap(133, 133, 133)
                 .addComponent(jButton_clear)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                 .addComponent(jButton_next, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(138, 138, 138))))
 );
 layout.setVerticalGroup(
     layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
     .addGroup(layout.createSequentialGroup()
         .addGap(45, 45, 45)
         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
             .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
             .addGroup(layout.createSequentialGroup()
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(jLabel_id, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jTextField_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel_lastname, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jTextField_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addGap(12, 12, 12)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jTextField_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel_address, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jTextField_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel_city, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jTextField_city, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel_state, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jTextField_state, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel_postalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jTextField_postalCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
             .addComponent(jButton_add)
             .addComponent(jButton_delete)
             .addComponent(jButton_update)
             .addComponent(jButton_previous)
             .addComponent(jButton_next)
             .addComponent(jButton_clear))
         .addContainerGap(83, Short.MAX_VALUE))
 );

 pack();
 jButton_add.addActionListener(this);
 jButton_delete.addActionListener(this);
 jButton_previous.addActionListener(this);
 jButton_update.addActionListener(this);
 jButton_next.addActionListener(this);
 jButton_clear.addActionListener(this);
 
this.setVisible(true);
 }
 {
 try
 {
 String url ="jdbc:mysql://localhost:3306/test";
 Class.forName("com.mysql.cj.jdbc.Driver");
 con =
 DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root", "");
 }
 catch(Exception e)
 {
 JOptionPane.showMessageDialog(this, "error in connection");
 }
 }
 public void actionPerformed(ActionEvent e)
 {
 if(e.getSource()==jButton_next)
 {
 i++;
 System.out.println(i);
 try
 {
 stmt=con.createStatement();
 sql="select * from customers where id="+i;
 rs=stmt.executeQuery(sql);
 if(rs.next())
 {
 jTextField_id.setText(rs.getString(1));
 jTextField_firstName.setText(rs.getString(2));
 jTextField_lastName.setText(rs.getString(3));
 jTextField_phone.setText(rs.getString(4));
 jTextField_address.setText(rs.getString(5));
 jTextField_city.setText(rs.getString(6));
 jTextField_state.setText(rs.getString(7));
 jTextField_postalCode.setText(rs.getString(8));
 }
 }
 catch(Exception e4){System.out.println(e4);}
 }
 else if(e.getSource()==jButton_previous)
 {
 if (i<=0){
	 i=0;
}
 else{
	 i--;
	 System.out.println(i);
}
 try
 {
 sql="select * from customers where id="+i;
 rs=stmt.executeQuery(sql);
 if(rs.next())
 { 

 jTextField_id.setText(rs.getString(1));
 jTextField_firstName.setText(rs.getString(2));
 jTextField_lastName.setText(rs.getString(3));
 jTextField_phone.setText(rs.getString(4));
 jTextField_address.setText(rs.getString(5));
 jTextField_city.setText(rs.getString(6));
 jTextField_state.setText(rs.getString(7));
 jTextField_postalCode.setText(rs.getString(8));
 }
 }
 catch(Exception e5)
 {System.out.println(e5);}
 }
 else if(e.getSource()==jButton_add)
 {
 int id = Integer.parseInt(jTextField_id.getText());
 String firstName = jTextField_firstName.getText();
 String lastName = jTextField_lastName.getText();
 String phone = jTextField_phone.getText();
 String address = jTextField_address.getText();
 String city = jTextField_city.getText();
 String state = jTextField_state.getText();
 String postalCode = jTextField_postalCode.getText();

 try
 { 
 String query ="insert into customers values('"+id+"','"+firstName+"','"+lastName+"','"+phone+"','"+address+"','"+city+"','"+state+"','"+postalCode+"')";
 stmt.execute(query);
 }
 catch(Exception e6)
 {System.out.println(e6);}
 }
 else if(e.getSource()==jButton_delete)
 {
 int id = Integer.parseInt(jTextField_id.getText());
 try
 { 
 String query = "delete from customers where id ="+id;
 stmt.execute(query);
 jTextField_id.setText(null);
 jTextField_firstName.setText(null);
 jTextField_lastName.setText(null);
 jTextField_phone.setText(null);
 jTextField_address.setText(null);
 jTextField_city.setText(null);
 jTextField_state.setText(null);
 jTextField_postalCode.setText(null);
 }
 catch(Exception e7)
 {System.out.println(e7);}
 }
 else if(e.getSource()==jButton_update)
 {
 int id = Integer.parseInt(jTextField_id.getText());
 String firstName = jTextField_firstName.getText();
 String lastName = jTextField_lastName.getText();
 String phone = jTextField_phone.getText();
 String address = jTextField_address.getText();
 String city = jTextField_city.getText();
 String state = jTextField_state.getText();
 String postalCode = jTextField_postalCode.getText();
 try
 { 
// String query = "update customers set id ='"+id+"',first_name = '"+firstName+"',last_name= '"+lastName+"',phone='"+phone+"',address='"+address+",city='"+city+"',state='"+state+"',postalCode='"+postalCode+"' where id ="+i;
 String query = "update customers set id ='"+id+"',first_name = '"+firstName+"',last_name ='"+lastName+"',phone ='"+phone+"',address= '"+address+"',city="+city+"',state="+state+"',postalCode="+postalCode+"'where id ="+i;
 stmt.execute(query);
 }
 catch(Exception e8)
 {System.out.println(e8);}
 }
 else if(e.getSource()==jButton_clear)
 {
 try
 { 
 String query = "delete from customers";
 stmt.execute(query);
 jTextField_id.setText(null);
 jTextField_firstName.setText(null);
 jTextField_lastName.setText(null);
 jTextField_phone.setText(null);
 jTextField_address.setText(null);
 jTextField_city.setText(null);
 jTextField_state.setText(null);
 jTextField_postalCode.setText(null);
 }
 catch(Exception e9)
 {System.out.println(e9);}
 }
 }
 public static void main(String []args)
 {
	 CustomersGui demo = new CustomersGui();
 //.setBackground(Color.black);
     demo.getContentPane().setBackground(Color.PINK);
 }
 }
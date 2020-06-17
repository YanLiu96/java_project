package comunicationGUI;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Server extends JFrame {
  // Text area for displaying contents
  private JTextArea jta = new JTextArea();
  private JTextField jtf = new JTextField();
  private JButton jbt_exit = new JButton("Exit");
  private JButton jbt_enter = new JButton("Enter");
  private DataOutputStream outputToClient;
  private DataInputStream inputFromClient;

  public static void main(String[] args) {
    new Server();
  }

  public Server() {
    // Place text area on the frame
	  JPanel p = new JPanel();
	    p.setLayout(new BorderLayout());
	    p.add(new JLabel("Enter message"), BorderLayout.WEST);
	    p.add(jtf, BorderLayout.CENTER);
	    p.add(jbt_enter,BorderLayout.EAST);
	    jbt_enter.addActionListener(new Listener());
	    jtf.setHorizontalAlignment(JTextField.RIGHT);
	    setLayout(new BorderLayout());
	    add(p, BorderLayout.SOUTH);
	    add(new JScrollPane(jta), BorderLayout.CENTER);
	    add(new JScrollPane(jbt_exit), BorderLayout.EAST);
    setTitle("Server");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!

    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(8000);
      jta.append("Server started at " + new Date() + '\n');

      //set exit action
      jbt_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
      // Listen for a connection request
      Socket socket = serverSocket.accept();

      // Create data input and output streams
      inputFromClient = new DataInputStream(
        socket.getInputStream());
      outputToClient = new DataOutputStream(
        socket.getOutputStream());

      while (true) {
        // Receive message from the client
      String messageclient = inputFromClient.readUTF();

        // Send area back to the client
        jta.append("Client send: " + messageclient + '\n');
      }
    }
    catch(IOException ex) {
      System.err.println(ex);
    }
  }
  private class Listener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	      try {
	          String server_message = jtf.getText();
	          outputToClient.writeUTF(server_message);
	          jta.append("Server send:" + server_message + "\n");
	      }
	      catch (IOException ex) {
	        System.err.println(ex);
	      }
	    }
	  }
}
package comunicationGUI;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame {
  // Text field to write message
  private JTextField jtf = new JTextField();
  
  // Text area to display contents
  private JTextArea jta = new JTextArea();
  private JButton jbt_exit = new JButton("Exit");
  private JButton jbt_enter = new JButton("Enter");
  // IO streams
  private DataOutputStream toServer;
  private DataInputStream fromServer;

  public static void main(String[] args) {
    new Client();
  }

  public Client() {
    // Panel p to hold the label and text field
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    p.add(new JLabel("Enter message"), BorderLayout.WEST);
    p.add(jtf, BorderLayout.CENTER);
    p.add(jbt_enter,BorderLayout.EAST);
    jtf.setHorizontalAlignment(JTextField.RIGHT);

    setLayout(new BorderLayout());
    add(p, BorderLayout.SOUTH);
    add(new JScrollPane(jta), BorderLayout.CENTER);
    add(new JScrollPane(jbt_exit), BorderLayout.EAST);
    jbt_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
    jbt_enter.addActionListener(new Listener()); // Register listener
    setTitle("Client");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!

    try {
      // Create a socket to connect to the server
      Socket socket = new Socket("localhost", 8000);
      

      // Create an input stream to receive message from the server
      fromServer = new DataInputStream(socket.getInputStream());

      // Create an output stream to send message to the server
      toServer = new DataOutputStream(socket.getOutputStream());
      
      // Get message from the server
      String server_message = fromServer.readUTF();
      jta.append("Server send "
              + server_message + '\n');
    }
    catch (IOException ex) {
      jta.append(ex.toString() + '\n');
    }
  }

  private class Listener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        String client_message = jtf.getText();
        // Send the message to the server
        toServer.writeUTF(client_message);
       // toServer.flush();

        // Display to the text area
        jta.append("Client send:" + client_message + "\n");
      }
      catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }
}
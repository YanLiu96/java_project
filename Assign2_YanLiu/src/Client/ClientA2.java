package Client;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * Author: 	Yan Liu
 * ID:      20082245
 * File:	Distributed System assignment 2 Client
 * Date:    2019-3-4
*/
public class ClientA2 extends JFrame implements ActionListener {
	// Text field to write message
	private JTextField jtf = new JTextField();

	// Text area to display contents
	private JTextArea jta = new JTextArea();

	// Button which exit project
	private JButton jbt_exit = new JButton("Exit");

	// Button to be clicked and send student id which inputed by user
	private JButton jbt_enter = new JButton("Enter");

	// IO streams
	private DataOutputStream toServer;
	private DataInputStream fromServer;
	private int address;

	public ClientA2() {
		setClientGUI();
		setActionListener();
		createSocketandStream();
	}

	private void createSocketandStream() {
		try {
			// Create a socket to connect to the server
			Socket socket = new Socket("localhost", 8000);
			// Create an input stream to receive message from the server
			fromServer = new DataInputStream(socket.getInputStream());
			// Create an output stream to send message to the server
			toServer = new DataOutputStream(socket.getOutputStream());
			address = socket.getLocalPort();
		} catch (IOException ex) {
			jta.append(ex.toString() + '\n');
		}
	}

	private void setClientGUI() {
		// Panel p to hold the label and text field
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		// ADD label which is "enter student id " and put it in the center
		p.add(new JLabel("Enter student ID"), BorderLayout.WEST);
		p.add(jtf, BorderLayout.CENTER);
		// put enter button in the east of borderLayout
		p.add(jbt_enter, BorderLayout.EAST);
		jtf.setHorizontalAlignment(JTextField.RIGHT);
		setLayout(new BorderLayout());
		add(p, BorderLayout.SOUTH);
		add(new JScrollPane(jta), BorderLayout.CENTER);
		add(new JScrollPane(jbt_exit), BorderLayout.EAST);
		setTitle("Client");
		setSize(550, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true); // It is necessary to show the frame here
	}

	private void setActionListener() {
		jbt_exit.addActionListener(this);
		jbt_enter.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbt_exit) {
			System.exit(0);
		} else if (e.getSource() == jbt_enter) {
				sendDataToServer();
				getFeebackFromServer();
		}
	}

	private void sendDataToServer() {
		try {
			// Get the student_id from the text field
			int student_id = Integer.parseInt(jtf.getText());
			jta.append("Client-"+address+ ":" + "  Enter Student ID:  " + student_id + '\n');
			// Send the student id to the server
			toServer.writeInt(student_id);
			// Get the port of the client
			String clientPort = Integer.toString(address);
			// Send the client port to the server
			toServer.writeUTF(clientPort);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	private void getFeebackFromServer() {
		try {
			// receive the feedback from server
			String feedback = fromServer.readUTF();
			jta.append("Server-: Processing........" + '\n');
			jta.append(feedback + '\n');
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
	
	public static void main(String[] args) {
		new ClientA2();
	}
}

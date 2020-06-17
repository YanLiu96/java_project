package Server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Date;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Client.ClientA2;

import java.sql.*;

/*
 * Author: 	Yan Liu
 * ID:      20082245
 * File:	Distributed System assignment 2 Server
 * Date:    2019-2-28
*/
public class MultiThreadedServerA2 extends JFrame implements ActionListener {
	// Text area for displaying contents
	private JTextArea jta = new JTextArea();
	private JButton start_client = new JButton("Start a Client");
	// Variables that will be used connect and query database
	Statement stmt;
	ResultSet rs;
	String sql;
	Connection con;

	public static void main(String[] args) {
		new MultiThreadedServerA2();
	}

	public MultiThreadedServerA2() {
		// Set the user interface
		setServerGUI();
		// show the frame
		setVisible(true);
		{
			// connect to the database while the frame shows
			connectDB();
		}
		createServerSocket();
	}

	private void setServerGUI() {
		// Place text area on the center of the frame
		setLayout(new BorderLayout());
		add(new JScrollPane(jta), BorderLayout.CENTER);
		add(start_client, BorderLayout.SOUTH);
		start_client.addActionListener(this);
		// Set the title of the window
		setTitle("MultiThreaded Server");
		// Set the size of the window
		setSize(550, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void createServerSocket() {
		try {
			// Create a server socket at port 8000
			ServerSocket serverSocket = new ServerSocket(8000);

			// Show server start notification in the text area
			jta.append("Server started at " + new Date() + '\n');

			// Listen for a connection request and new thread
			setClientThread(serverSocket);
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

	private void setClientThread(ServerSocket serverSocket) throws IOException {
		while (true) {
			Socket socket = serverSocket.accept();
			// Connect to a client Thread
			ThreadClass thread = new ThreadClass(socket);
			// start the thread
			thread.start();
		}
	}

	private void connectDB() {
		// Set database driver and connection
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Assign2", "root", "");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "error in connection");
		}
	}

	private class ThreadClass extends Thread {
		private Socket socket;
		// The address of Server
		private InetAddress address;
		// Data Stream
		private DataInputStream inputFromClient;
		private DataOutputStream outputToClient;

		public ThreadClass(Socket socket) {
			this.socket = socket;
			address = socket.getInetAddress();
			// Create data input from client and output to client streams
			createStreams(socket);
		}

		public void run() {
			try {
				while (true) {
					// Receive student id from the client.
					int student_id = inputFromClient.readInt();
					String clientPort = inputFromClient.readUTF();
					// check whether the id is in the database, create feedback message.
					try {
						queryDatabase(student_id);
						studentValidate(student_id, clientPort);
					} catch (Exception e) {
						System.err.println(e + "on " + "Database");
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				System.err.println(e + "on " + socket);
				e.printStackTrace();
			}
		}

		private void createStreams(Socket socket) {
			try {
				inputFromClient = new DataInputStream(socket.getInputStream());
				outputToClient = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				System.err.println("Exception in class");
				e.printStackTrace();
			}
		}

		private void queryDatabase(int student_id) throws SQLException {
			stmt = con.createStatement();
			// Search student whose id is inputed whether in the database
			sql = "select * from myStudents where STUD_ID=" + student_id;
			// Get the search result
			rs = stmt.executeQuery(sql);
		}

		// Validate whether student id is right (in the database)
		private void studentValidate(int student_id, String clientPort) throws SQLException, IOException {
			/*
			 * If the result does not contain the student rs.last() is false if there are no
			 * rows in the result set which means student is not exist
			 */
			if (rs.last() == false) {
				validateFailure(student_id, clientPort);
			} else {
				validateSuccess(clientPort);
			}
		}

		private void validateFailure(int student_id, String clientPort) throws IOException {
			// When student is not exist, server sends wrong feedback to client
			String wrong_feedback = "Client-" + clientPort + ": Sorry " + student_id
					+ " You are not a register student! Bye!. \n";
			jta.append(wrong_feedback + '\n');
			outputToClient.writeUTF(wrong_feedback);
			// Wrong id and socket is closed
			socket.close();
		}

		private void validateSuccess(String clientPort) throws SQLException, IOException {
			// When student is exist, get the student first and last name
			String studen_name = rs.getString(3) + " " + rs.getString(4);

			// Send the Confirm the information to the Client
			String right_feedback = "Client-" + clientPort + ": Welcome " + studen_name
					+ "! You are now connected to the Server \n";
			jta.append(right_feedback + '\n');

			// Send the feedback to the client which send the student id
			outputToClient.writeUTF(right_feedback);
		}

	}

	// start a new client interface
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start_client) {
			new ClientA2();
		}
	}
}
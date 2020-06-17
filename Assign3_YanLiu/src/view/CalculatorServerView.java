package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*
 * Author:    YanLiu
 * ClassName: Calculator
 * FileName:  CalculatorServerView.java
 * This file is the server view
*/
public class CalculatorServerView extends JFrame implements ActionListener{
		// Text area for displaying contents
		public JTextArea jta = new JTextArea();
		
		// Start a new client
		private JButton start_calculator = new JButton("Start a new calculator");
		private JButton clear_console = new JButton("Clear the output"); 
		private int clientNameCount =0;
		public CalculatorServerView() {
			// Set the user interface
			setServerGUI();
			setVisible(true);
		}
		
		private void setServerGUI() {
			// Place text area on the center of the frame
			setLayout(new BorderLayout());
			add(new JScrollPane(jta), BorderLayout.CENTER);
			add(start_calculator, BorderLayout.SOUTH);
			add(clear_console,BorderLayout.NORTH);
			start_calculator.addActionListener(this);
			clear_console.addActionListener(this);
			// Set the title of the window
			setTitle("Calculator Server");
			// Set the size of the window
			setSize(400, 400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		// Open a new client and set the client name
	    public void actionPerformed(ActionEvent e) {
	    		String action = e.getActionCommand();
	    		if(action == "Start a new calculator") {
	    			clientNameCount++;
		            new CalculatorClientView(clientNameCount);
		            
		            //clear the output 
	    		}else if (action == "Clear the output") {
	    			jta.setText(null);
	    		}
	    		
	    }
}

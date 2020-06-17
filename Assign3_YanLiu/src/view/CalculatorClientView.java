package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.CalculatorClientController;;

/*
 * Author:    YanLiu
 * ClassName: Calculator
 * FileName:  CalculatorClientView.java
 * This file is the client view
 * The calculator support + - * /, Multiple numbers operations, decimal operations, negative operations
*/
public class CalculatorClientView extends JFrame implements ActionListener {

	// Text field for displaying formula and answer.
	private JTextField jtf = new JTextField();
	// Create text area at the bottom of the calculator which shows system messages
	private JTextArea jta = new JTextArea();

	private String textArea = "This calculator supports "
			+ "\n Multiple numbers operation."
			+ "\n For example :2+3-((-2+3)*4)/2 " + "\n Can calculate after getting the last result."
			+ "\n The output in server is step by step.";
	
	private JButton btn_backspace;
	// Store the operand
	private Stack<Double> operand = new Stack<>();
	// Store the operator
	private Stack<Character> operator = new Stack<>();
	private String clientName = "";
	// A object of client controller
	private CalculatorClientController calClientController;

	public CalculatorClientView(int clientNamecount) {
		// Set the user interface
		calClientController = new CalculatorClientController();
		// Set the client name
		setServerGUI(clientNamecount);
		// Pass client name to the server(connection information) when open a new client
		calClientController.connectionIfo(clientName);
	}

	private void setServerGUI(int clientCount) {

		setTitle("Calculator Client-" + clientCount);
		clientName = getTitle();

		jtf = new JTextField();
		jtf.setEditable(false);
		jtf.setHorizontalAlignment(JTextField.RIGHT);
		jtf.setBounds(60, 40, 280, 40);
		add(jtf);

		JButton btn_1 = new JButton("1");
		btn_1.setBounds(65, 250, 55, 50);
		btn_1.setForeground(Color.red);
		btn_1.setBackground(Color.lightGray);
		btn_1.addActionListener(this);
		add(btn_1);

		JButton btn_2 = new JButton("2");
		btn_2.setBounds(135, 250, 55, 50);
		btn_2.setForeground(Color.red);
		btn_2.setBackground(Color.lightGray);
		btn_2.addActionListener(this);
		add(btn_2);

		JButton btn_3 = new JButton("3");
		btn_3.setBounds(205, 250, 55, 50);
		btn_3.setForeground(Color.red);
		btn_3.setBackground(Color.lightGray);
		btn_3.addActionListener(this);
		add(btn_3);

		JButton btn_4 = new JButton("4");
		btn_4.setBounds(65, 185, 55, 50);
		btn_4.setForeground(Color.red);
		btn_4.setBackground(Color.lightGray);
		btn_4.addActionListener(this);
		add(btn_4);

		JButton btn_5 = new JButton("5");
		btn_5.setBounds(135, 185, 55, 50);
		btn_5.setForeground(Color.red);
		btn_5.setBackground(Color.lightGray);
		btn_5.addActionListener(this);
		add(btn_5);

		JButton btn_6 = new JButton("6");
		btn_6.setBounds(205, 185, 55, 50);
		btn_6.setForeground(Color.red);
		btn_6.setBackground(Color.lightGray);
		btn_6.addActionListener(this);
		add(btn_6);

		JButton btn_7 = new JButton("7");
		btn_7.setBounds(65, 120, 55, 50);
		btn_7.setForeground(Color.red);
		btn_7.setBackground(Color.lightGray);
		btn_7.addActionListener(this);
		add(btn_7);

		JButton btn_8 = new JButton("8");
		btn_8.setBounds(135, 120, 55, 50);
		btn_8.setForeground(Color.red);
		btn_8.setBackground(Color.lightGray);
		btn_8.addActionListener(this);
		add(btn_8);

		JButton btn_9 = new JButton("9");
		btn_9.setBounds(205, 120, 55, 50);
		btn_9.setForeground(Color.red);
		btn_9.setBackground(Color.lightGray);
		btn_9.addActionListener(this);
		add(btn_9);

		JButton btn_0 = new JButton("0");
		btn_0.setBounds(65, 315, 55, 50);
		btn_0.setForeground(Color.red);
		btn_0.setBackground(Color.lightGray);
		btn_0.addActionListener(this);
		add(btn_0);

		JButton btn_division = new JButton("/");
		btn_division.setBounds(275, 120, 55, 50);
		btn_division.addActionListener(this);
		btn_division.setForeground(Color.blue);
		btn_division.setBackground(Color.lightGray);
		add(btn_division);

		JButton btn_multiplication = new JButton("*");
		btn_multiplication.setBounds(275, 185, 55, 50);
		btn_multiplication.addActionListener(this);
		btn_multiplication.setBackground(Color.lightGray);
		btn_multiplication.setForeground(Color.blue);
		add(btn_multiplication);

		JButton btn_minus = new JButton("-");
		btn_minus.setBounds(275, 250, 55, 50);
		btn_minus.addActionListener(this);
		btn_minus.setBackground(Color.lightGray);
		btn_minus.setForeground(Color.blue);
		add(btn_minus);

		JButton btn_plus = new JButton("+");
		btn_plus.setBounds(275, 315, 55, 50);
		btn_plus.addActionListener(this);
		btn_plus.setBackground(Color.lightGray);
		btn_plus.setForeground(Color.blue);
		add(btn_plus);

		JButton btn_decimal = new JButton(".");
		btn_decimal.setBounds(135, 315, 55, 50);
		btn_decimal.setForeground(Color.red);
		btn_decimal.setBackground(Color.lightGray);
		btn_decimal.addActionListener(this);
		add(btn_decimal);

		JButton btn_clear = new JButton("AC");
		btn_clear.setBounds(135, 380, 55, 50);
		btn_clear.setForeground(Color.blue);
		btn_clear.setBackground(Color.lightGray);
		btn_clear.addActionListener(this);
		add(btn_clear);

		btn_backspace = new JButton("BS");
		btn_backspace.setBounds(65, 380, 55, 50);
		btn_backspace.addActionListener(this);
		btn_backspace.setBackground(Color.lightGray);
		btn_backspace.setForeground(Color.blue);
		add(btn_backspace);

		JButton btn_submit = new JButton("=");
		btn_submit.setBounds(205, 315, 55, 50);
		btn_submit.addActionListener(this);
		btn_submit.setBackground(Color.lightGray);
		btn_submit.setForeground(Color.blue);
		add(btn_submit);

		JButton btn_leftparenthesis = new JButton("(");
		btn_leftparenthesis.setBounds(205, 380, 55, 50);
		btn_leftparenthesis.setForeground(Color.blue);
		btn_leftparenthesis.setBackground(Color.lightGray);
		btn_leftparenthesis.addActionListener(this);
		add(btn_leftparenthesis);

		JButton btn_rightparenthesis = new JButton(")");
		btn_rightparenthesis.addActionListener(this);
		btn_rightparenthesis.setBackground(Color.lightGray);
		btn_rightparenthesis.setForeground(Color.blue);
		btn_rightparenthesis.setBounds(275, 380, 55, 50);
		add(btn_rightparenthesis);

		jta = new JTextArea(textArea);

		jta.setBounds(65, 445, 270, 90);
		jta.setEditable(false);
		add(jta);

		setLayout(null);
		setVisible(true);
		setSize(400, 580);
		getContentPane().setBackground(Color.gray);
		// when close a client, just close the current window
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
	}

	// The action event of buttons.
	public void actionPerformed(ActionEvent e) {
		// get the value of button when the button is clicked
		String buttonValue = e.getActionCommand();
		// use switch to determine which event to handle
		switch (buttonValue) {
		// clear the formula in the text field
		case ("AC"):
			jtf.setText(null);
			jta.setText(textArea);
			break;
		case ("BS"):// backspace when you find you enter wrong number of operator
			try {
				if (jtf.getText().length() > 0) {
					StringBuilder strBuilder = new StringBuilder(jtf.getText());
					strBuilder.deleteCharAt(jtf.getText().length() - 1);
					String Afterbackspace = strBuilder.toString();
					jtf.setText(Afterbackspace);
				}
			} catch (Exception e1) {
				System.out.print(e1);
			}
			break;
		case ("="):
			try {
				// check the format of formula whether valid and get result if valid
				if (jtf.getText().length() == 0) {// check whether formula is null
					JOptionPane.showMessageDialog(this, "No things to caculate,the formula is null");
					jta.setText(textArea);
					break;
				}
				// deal with the formula (user input)
				Double outputAnswer = handleFormula(jtf.getText());

				// check that it can not division by 0
				if (Double.toString(outputAnswer) == "Infinity" || Double.toString(outputAnswer) == "-Infinity") {
					JOptionPane.showMessageDialog(this, "Sorry, You can't division by 0");
					jtf.setText(null);
					jta.setText(textArea);
					break;
				}
				jta.setText("Data received from Server" + "\n" + outputAnswer);
				jtf.setText(" " + outputAnswer);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "Formula error, please check it:" + jtf.getText());
				jta.setText(textArea);
				jtf.setText(null);
				break;
			}
			break;
		// display result in JTextField in the client
		default:
			String displayResult = jtf.getText() + buttonValue;
			jtf.setText(displayResult);
		}
	}

	private Double handleFormula(String expression) {
		// check whether negative number in the formula
		expression = judgeNegative(expression);

		// add blank into the formula then it can be split into numeric and operator
		expression = insertBlank(expression);

		// judge whether push or pop the stacks and when should calculate
		logicalJudgment(expression);

		// get the answer of input formula
		return operand.pop();

	}

	private void logicalJudgment(String expression) {
		// split the formula
		String[] operandAndOperator = expression.split(" ");
		for (String element : operandAndOperator) {
			// if element is " " ,continue for
			if (element.length() == 0) {
				continue;
			}
			// if operator is "+" or "-" , it has lowest priorities, should be operated
			else if (element.charAt(0) == '+' || element.charAt(0) == '-') {
				// When the operator stack is not empty，And the top element in the stack is
				// any one of the add, subtract, multiply and divide operator
				while (!operator.isEmpty() && (operator.peek() == '-' || operator.peek() == '+'
						|| operator.peek() == '/' || operator.peek() == '*')) {
					calculate(operand, operator); // Start operation
				}

				// When operation finished, the current operator is pushed on the stack
				operator.push(element.charAt(0));

			}
			// When the current operator is multiplication or division,
			// because the priority is higher than addition and subtraction,
			// so it need to determine whether the top is multiplication or division,
			// if it is multiplication or division operation, otherwise directly push into
			// the stack
			else if (element.charAt(0) == '*' || element.charAt(0) == '/') {
				while (!operator.isEmpty() && (operator.peek() == '/' || operator.peek() == '*')) {
					calculate(operand, operator);
				}
				operator.push(element.charAt(0)); // the current operator is pushed on the stack
			}
			// If it's an left parenthesis,just push it.The trim() function can delete " ".
			else if (element.trim().charAt(0) == '(') {
				operator.push('(');
			}
			// If it's an right parenthesis,Clears the stack of operators up to the right
			// parenthesis
			else if (element.trim().charAt(0) == ')') {
				while (operator.peek() != '(') {
					calculate(operand, operator); // calculate
				}
				operator.pop(); // After calculation,clear right parenthesis
			}
			// if element is number, push it in the operand stack
			else {
				operand.push(Double.parseDouble(element));
			}
		}
		// when the stack is not empty,continues the operation until the stack is empty
		while (!operator.isEmpty()) {
			calculate(operand, operator);
		}
	}

	private String judgeNegative(String expression) {
		// When it contains negative in the beginning of formula for example: -4+8
		if (expression.charAt(0) == '-' || (expression.charAt(0) == ' ') && expression.charAt(1) == '-') {
			expression = 0 + expression;
		}
		// When it contains negative for example: (-3*6+2) change it to (0-3*6+2)
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '-' && expression.charAt(i - 1) == '(') {
				StringBuilder strb = new StringBuilder(expression);
				strb.replace(i, i + 1, "0-");
				expression = strb.toString();
				continue;
			}
		}
		return expression;
	}

	public String insertBlank(String formula) {
		String result = "";
		for (int i = 0; i < formula.length(); i++) {
			char element = formula.charAt(i);
			if (element == '(' || element == ')' || element == '+' || element == '-' || element == '*'
					|| element == '/')
				result += " " + element + " ";
			else
				result += element;
		}
		return result;
	}

	// Use ClientController to calculate
	public void calculate(Stack<Double> operandStack, Stack<Character> operatorStack) {
		char operator = operatorStack.pop(); // pop a operator in the operator stack
		// pop two operand at a time, and do calculate
		Double operand1 = operandStack.pop();
		Double operand2 = operandStack.pop();
		if (operator == '+') // do add
			operandStack.push(calClientController.calculate("add", operand2, operand1, clientName));
		else if (operator == '-') // do subtraction
			operandStack.push(calClientController.calculate("subtract", operand2, operand1, clientName));
		else if (operator == '*') // do multiplication
			operandStack.push(calClientController.calculate("multiply", operand2, operand1, clientName));
		else if (operator == '/') // do division
			operandStack.push(calClientController.calculate("divide", operand2, operand1, clientName));
	}
}

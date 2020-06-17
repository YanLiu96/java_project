package controller;

import model.Calculator;
import view.CalculatorServerView;

import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

/*
 * Author:    YanLiu
 * ClassName: CalculatorServerController
 * FileName:  CalculatorServerController.java
 * This file is controller of the Server.
 * It implements the methods in calculator interface
*/
public class CalculatorServerController extends UnicastRemoteObject implements Calculator {

	CalculatorServerView serverView = new CalculatorServerView();

	// Constructor
	private CalculatorServerController() throws RemoteException {
		super();
	}

	// implement add operation in the interface
	public double addNums(double num1, double num2, String clientName) throws RemoteException {
		double result = num1 + num2;
		displayResult(num1, num2, "+", result, clientName);
		return result;
	}

	// implement subtract operation in the interface
	public double subtractNums(double num1, double num2, String clientName) throws RemoteException {
		double result = num1 - num2;
		displayResult(num1, num2, "-", result, clientName);
		return result;
	}

	// implement multiply operation in the interface
	public double multiplyNums(double num1, double num2, String clientName) throws RemoteException {
		double result = num1 * num2;
		displayResult(num1, num2, "*", result, clientName);
		return result;
	}

	// implement divide operation in the interface
	public double divideNums(double num1, double num2, String clientName) throws RemoteException {
		double result = num1 / num2;
		displayResult(num1, num2, "/", result, clientName);
		return result;
	}

	// The the client is opened,run this function
	public void connection(String connectionInfo) throws RemoteException {
		// show the connection information(client name, host)
		displayConnection(connectionInfo);

	}

	// When the operation is finished, the result is displayed in the server's text
	// area.
	private void displayResult(double num1, double num2, String operator, double result, String clientName) {
		// check whether result is infinity or -infinity(divided by 0)
		if (Double.toString(result) == "Infinity" || Double.toString(result) == "-Infinity") {
			serverView.jta.append("\nSorry, You can't Division by 0" + "\n");
		} 
		else {
			// Append the result in server
			serverView.jta.append("\nRequest from " + clientName + 
								  ": \n" + "Operand 1: " + num1 + 
								  "\nOperand 2: " + num2 + "" +
								  "\nOperator: " + operator + 
								  "\nData to " + clientName + ": " + result + "\n");
		}
	}

	// show the connection information(client name, host)
	private void displayConnection(String connectionInfo) {
		serverView.jta.append("\n" + connectionInfo + " connected at IP: \n" + getClient());
	}

	// get the host of client
	public String getClient() {
		try {
			return getClientHost();
		} catch (ServerNotActiveException e) {
			System.out.println("\nError: " + e.getMessage());
			return "";
		}
	}

	public static void main(String[] args) {
		try {
			// Create an object
			CalculatorServerController csc = new CalculatorServerController();
			Registry registry = LocateRegistry.createRegistry(1099);

			// Bind this object instance to the name "Calculator".
			registry.rebind("Calculator", csc);
			System.out.println("Calculator server bound in registry");
		} catch (Exception e) {
			System.out.println("CalculatorServer error : " + e.getMessage());
			System.exit(0);
		}
	}

}

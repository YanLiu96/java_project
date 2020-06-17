package model;

import java.rmi.Remote; 
import java.rmi.RemoteException; 

/*
 * Author:    YanLiu
 * ClassName: Calculator
 * FileName:  Calculator.java
 * This file is the remote interface of this application  
*/
public interface Calculator extends Remote{
	double addNums(double num1,double num2,String clientName) throws RemoteException;
	double subtractNums(double num1,double num2,String clientName) throws RemoteException;
	double multiplyNums(double num1,double num2,String clientName) throws RemoteException;
	double divideNums(double num1,double num2,String clientName) throws RemoteException;
	void   connection(String connectionInfo)throws RemoteException;
}

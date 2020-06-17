package controller;

import model.Calculator;
import java.rmi.Naming;

/*
 * Author:    YanLiu
 * ClassName: CalculatorClientController
 * FileName:  CalculatorClientController.java
 * This file is controller of the client
*/

public class CalculatorClientController{
	
	// constructor
	public CalculatorClientController(){}
    private double answer = 0.0;

    /*
    Calculate method which is called from the Client view.
    */
    public double calculate(String operator, double num1, double num2,String clientName){
        try{
            Calculator cal = (Calculator) Naming.lookup("//localhost/Calculator");
            // Determines which method of the interface is called
            switch (operator){
                case ("add"):
                    answer = cal.addNums(num1, num2,clientName);
                    break;
                case("subtract"):
                    answer = cal.subtractNums(num1, num2,clientName);
                    break;
                case("multiply"):
                    answer = cal.multiplyNums(num1, num2,clientName);
                    break;
                case("divide"):
                    answer = cal.divideNums(num1, num2,clientName);
                    break;
            }
        } catch (Exception e) {
            System.out.println("CalculatorClient exception : " + e.getMessage());
        }
        return answer;
}
    // Initialize client connection information
    public void connectionIfo(String info) {
    	try{
            Calculator cal = (Calculator) Naming.lookup("//localhost/Calculator");
            cal.connection(info);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}

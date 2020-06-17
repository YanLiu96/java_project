package example;


import java.rmi.Naming;
import java.rmi.RemoteException;


public class HelloWorldClient implements HelloWorld{

static String message = "blank";

static HelloWorld obj = null;

public static void main(String args[])
{
	try {
		obj = (HelloWorld)Naming.lookup("//"
				+ "localhost"
				+ "/HelloWorld");
		message = obj.helloWorld();
		System.out.println("Message from the RMI-server was: \""
				+ message + "\"");
	}
	catch (Exception e) {
		System.out.println("HelloWorldClient exception: "
				+ e.getMessage());
		e.printStackTrace();
	}
}

@Override
public String helloWorld() throws RemoteException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int addNums(int num1, int num2) throws RemoteException {
	// TODO Auto-generated method stub
	return 0;
}
}
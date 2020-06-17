package example;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloWorld extends Remote {
	String helloWorld() throws RemoteException;
	int addNums(int num1,int num2) throws RemoteException;
}
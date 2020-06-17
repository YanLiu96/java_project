package example3;

//Create a thread to implement Runnable
public class DisplayMessage implements Runnable
{
private String message;
public DisplayMessage(String message)
{
this.message = message;
}
public void run()
{
try {
for(int i = 0; i < 10; i++) {
System.out.println(message);
Thread.sleep(300);
}
} catch (InterruptedException e) {
System.out.println("DisplayMessage thread interrupted.");
}
}
}

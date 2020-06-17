package example1;

import java.util.Random;

public class Tuna implements Runnable {
	String name;
	int time;
	Random r = new Random();
	
	public Tuna(String x) {
		name = x;
		time = r.nextInt(999);
	}
	//随机选取某个线程输出（线程同时运行）
	public void run() {
		try {
			System.out.printf("%s us sleeping for %d",name,time);
			System.out.println();
			Thread.sleep(time);
			System.out.printf("%s is done\n",name);
		}catch(Exception e) {}
	}
}

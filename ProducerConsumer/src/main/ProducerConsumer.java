package main;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JOptionPane;

import frame.MyFrame;

public class ProducerConsumer{
    
    /**
    * @Fields MAX : 缓冲区的数量上限
    */ 
    private int MAX = 1;
    
    /**
    * @Fields lock : Lock对象，为临界区加锁和释放
    */ 
    private final Lock lock = new ReentrantLock();
    
    /**
    * @Fields full : 缓冲池内满缓冲区,生产者进程
    */ 
    private final Condition full = lock.newCondition();
    
    /**
    * @Fields empty : 缓冲区内空缓冲区,消费者进程
    */ 
    private final Condition empty = lock.newCondition();
    
    /**
    * @Fields numbers : 生产者队列待生产的产品数
    */ 
    private int numbers=20;
    

    public ProducerConsumer() {//初始化待生产者列表
    	for(int i=numbers;i>0;i--) {
        	MyFrame.proList.add(new String(String.valueOf(i)));
        }
    }

    public void run() {
    	
    	Producer producer1 = new Producer();
    	producer1.setName("生产者1");
    	
    	
    	Producer producer2 = new Producer();
    	producer2.setName("生产者2");
    	
    	
    	Consumer consumer1 = new Consumer();
    	consumer1.setName("消费者1");
    	
    	
    	Consumer consumer2 = new Consumer();
    	consumer2.setName("消费者2");
    	
    	consumer1.start();
    	producer2.start();
    	
    	consumer2.start();
    	producer1.start();
    	
    	/*JOptionPane.showMessageDialog(null, "演示完成！", "系统提示",
				JOptionPane.INFORMATION_MESSAGE);*/
    }
   
    public int getNumbers() {
		return numbers;
	}

	
	public int getMAX() {
		return MAX;
	}

	public void setMAX(int mAX) {
		MAX = mAX;
	}

	
	public void lessNumber() {
		this.numbers=this.numbers-1;
	}

	/**
    * @ClassName: Producer
    * @Description: 生产者进程
    * @author kooking
    * @date 2018年1月14日 下午2:15:39
    */ 
    class Producer extends Thread {


		/* (non-Javadoc)
    	* Title: run 
    	* Description: 重写run方法
    	* @see java.lang.Thread#run()
    	*/ 
    	@Override
        public void run() {
            while (true) {
            	
            	//加锁
                lock.lock();
                if(getNumbers()==0) {lock.unlock();break;}
                try {
                	//线程停止1秒是为了让程序走的慢一些，不至于一眨眼就结束了
                	sleep(1000);
                   if (MyFrame.comList.size() == getMAX()) {
                	   //若公共缓冲池已满，将当前进程加入等待队列
                	   //直到被唤醒：full.signal
                        util.showInfo("警告: 公共缓冲池已满!"+Thread.currentThread().getName()+"等待\n");
                        MyFrame.setFull(true);
                        //刷新画面
                        MyFrame.centerPanel.repaint();//会调用paint()方法
                        //当前进程进入等待队列，并让出同步锁
                        full.await();
                        continue;
                    }
                   
                    String str = MyFrame.proList.removeLast();//移除最后一个
                    if (MyFrame.comList.add(str)) {
                    	//若object对象成功添加进队列comList
                    	//说明生产者进程成功生产一个产品并送入公共缓冲池
                    	util.showInfo(Thread.currentThread().getName()+" 生产了: "+str+"\n");
                    	MyFrame.proListadd.add(str);
                    	MyFrame.setEmpty(false);
                        MyFrame.centerPanel.repaint();
                        
                        //次数减一
                    	lessNumber();
                    	//唤醒等待的消费者进程
                        empty.signal();
                    }
                } catch (InterruptedException ie) {
                	//捕获到异常说明进程被异常中断
                	util.showInfo("生产中断!\n");
                } finally {
                	
                	//释放锁
                    lock.unlock();
                    try {
                    	//线程随机暂停几秒是为了让生产者和消费者之间的运行产生冲突
                    	//从而达到演示的目的
						sleep(new Random().nextInt(5000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
            }
            
            try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            util.showInfo(Thread.currentThread().getName()+" 结束!\n");
            
        }
    }

    /**
    * @ClassName: Consumer
    * @Description: 消费者进程
    * @author kooking
    * @date 2018年1月14日 下午2:35:56
    */ 
    class Consumer extends Thread {
    	
    	/* (non-Javadoc)
    	* Title: run
    	* Description: 重写run方法
    	* @see java.lang.Thread#run()
    	*/ 
    	@Override
        public void run() {
            while (true) {
            	
            	//加锁
                lock.lock();
                if(getNumbers()==0 && MyFrame.comList.size()==0) {lock.unlock();break;}
                try {
                	//线程停止2秒是为了让程序走的慢一些，不至于一眨眼就结束了
                	sleep(1000);
                    if (MyFrame.comList.size() == 0) {
                    	//若comList长度为0说明公共缓冲池里没有没有缓冲区可用
                    	//即缓冲区为空，没有产品可取
                    	util.showInfo("警告: 公共缓冲池已空!"+Thread.currentThread().getName()+"等待\n");
                    	MyFrame.setEmpty(true);
                    	//刷新画面
                    	MyFrame.centerPanel.repaint();
                    	
                        //将当前进程放入等待队列直到被唤醒：empty.signal()
                        empty.await();
                        continue;
                    }
                    
                	//消费者进程成功将产品从缓冲池取出
                    String str = MyFrame.comList.removeLast();
                    if(MyFrame.conList.add(str)) {
                    	util.showInfo(Thread.currentThread().getName()+" 消费了: " + str+"\n");
                    	MyFrame.setFull(false);
                    	 MyFrame.centerPanel.repaint();
                         //唤醒在等待的生产者进程
                         full.signal();
                    }
                } catch (InterruptedException ie) {
                	util.showInfo("消费被中断!\n");
                } finally {
                	//释放锁
                    lock.unlock();
                    try {
                    	//线程随机暂停几秒是为了让生产者和消费者之间的运行产生冲突
                    	//从而达到演示的目的
						sleep(new Random().nextInt(5000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
            }
            
            try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            util.showInfo(Thread.currentThread().getName()+" 结束!\n");
            
        }
    }
    
}


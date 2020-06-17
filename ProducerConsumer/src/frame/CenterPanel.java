package frame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CenterPanel extends JPanel{
	
	/**
	* @Fields serialVersionUID : 
	*/ 
	private static final long serialVersionUID = 1L;//序列化版本号，与版本号控制有关

	public CenterPanel() {
		this.setBackground(Color.WHITE);
	}
	
	@Override	//在父类中的无参构造函数中已经调用了paint方法，所以所有的子类中只要覆盖了paint方法，就不用再调用paint方法了，可以直接绘制图形了。
	public void paint(Graphics g) {//该方法自动调用
		super.paint(g);
		g.setColor(Color.gray);
		//画出生产者队列中的所有产品（不是线程）
		for(int i=0;i<MyFrame.proListadd.size();i++) {
			g.fill3DRect(35, i*20+5, 200, 20, true);//draw3DRect(int x,int y,int width,int height, boolean raised)：画一个突出显示的矩形。
			//其中x和y指定矩形左上角的位置，参数width和height是矩形的宽和高，参数raised是突出与否。
			g.drawString(MyFrame.proListadd.get(i), 235, i*20+15);
		}
		
		if(MyFrame.isFull()) {
			//若公共缓冲池已满，将公共缓冲池里的缓冲区变为黄色
			g.setColor(Color.YELLOW);
		}
		//画出公共缓冲池中所有缓冲区（不是线程）
		for(int i=0;i<MyFrame.comList.size();i++) {
			g.fill3DRect(285, i*20+5, 200, 20, true);
			g.drawString(MyFrame.comList.get(i), 485, i*20+15);
		}
		

		//将画笔颜色调回灰色
		g.setColor(Color.gray);
		// 画出消费者队列中所有已取得的任务（不是线程）
		for (int i = 0; i < MyFrame.conList.size(); i++) {
			g.fill3DRect(535, i * 20 + 5, 200, 20, true);
			g.drawString(MyFrame.conList.get(i), 735, i*20+15);
		}
		
		if(MyFrame.isEmpty()) {
			//若公共缓冲池为空
			g.setColor(Color.red);
			g.fill3DRect(285, 5, 200, 200, true);
			g.setColor(Color.black);
			g.drawString("Empty", 385, 100);//Emplty居中，x坐标285~485，y坐标5~205
		}
		
	}
	
	
}

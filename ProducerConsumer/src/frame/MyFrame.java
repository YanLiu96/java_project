package frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import main.Main;
import main.MyHelp;
import main.ProducerConsumer;
import main.util;


/**
* @ClassName: MyFrame
* @Description: 设计交互界面
* @author kooking
* @date 2017年11月5日 下午9:54:42
*/ 
public class MyFrame extends JFrame{
	/**
	* @Fields serialVersionUID : 
	*/ 
	private static final long serialVersionUID = 1L;
	public static JPanel topPanel, lowPanel, lablePanel;
	public static JTextArea  textArea;
	public static JScrollPane textScroll;
	public static JButton startButton, helpButton;
	public static JComboBox<String> jcb;
	public static CenterPanel centerPanel;
	
	/**
	* @Fields proList : 生产者待生产产品链表
	*/ 
	/**
	* @Fields conList : 消费者已消费产品链表
	*/ 
	/**
	* @Fields comList : 公共缓冲池链表
	*/ 
	public  static  LinkedList<String> proList,conList,comList;
	public  static  LinkedList<String> proListadd;//已生产产品列表
	
	/**
	* @Fields full : 公共缓冲池是否已满
	*/ 
	public static  boolean full=false;
	
	/**
	* @Fields empty : 公共缓冲池是否为空
	*/ 
	public static boolean empty=false;
	
	//初始化链表
	static {
		proList=new LinkedList<>();
		conList=new LinkedList<>();
		comList=new LinkedList<>();
		proListadd=new LinkedList<>();
	}

//	 public Cipher cipher=new Cipher();
	public MyFrame() {
		//设置文本框字体
		Font textFont=new Font("宋体", Font.PLAIN, 20);
		
		//设置左上角的组件布局
		topPanel=setTopLeftPanel();
		
		// 定义右上角（即文字显示演示过程）组件部分
		textArea = new JTextArea(20, 10);//20行,10列
		textArea.setEditable(false);
		textScroll = new JScrollPane();
		textScroll.setViewportView(textArea);//创建后指出内部控件
		textScroll.setColumnHeaderView(new JLabel("文字显示"));
		textArea.setFont(textFont);
		
		
		//定义中间（即显示框）部分组件
		lablePanel=setLablePanel();
		centerPanel=new CenterPanel();
		//定义底部按钮组件
		lowPanel=new JPanel();
		lowPanel.setLayout(null);
		startButton=new JButton("开始");
		//添加监听
		MyActionListener myActionListener = new MyActionListener();
		startButton.addActionListener(myActionListener);
		helpButton=new JButton("帮助");
		helpButton.addActionListener(myActionListener);
		
		
		startButton.setBounds(210,10,70,35);
		helpButton.setBounds(450,10,70,35);
		
		lowPanel.add(startButton);
		lowPanel.add(helpButton);
				
		//将所有组件添加到JFrame里
		this.setLayout(null);
		topPanel.setBounds(20, 10, 350, 170);
		textScroll.setBounds(400, 10, 350, 170);
		lablePanel.setBounds(0,190,770,25);
		centerPanel.setBounds(0,220,770,410);
		lowPanel.setBounds(20,630,750,50);
		this.add(topPanel);//上左面板
		this.add(textScroll);
		this.add(lablePanel);//中间（即显示框）部分组件,标签面板
		this.add(centerPanel);
		this.add(lowPanel);//底部按钮面板
		this.setTitle("生产者-消费者 V1.115") ;
		setSize(770, 740);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//设置窗体居中
		setFrameCenter(this);
		
	}
	
	class MyActionListener implements ActionListener{

		/* (non-Javadoc)
		* Title: actionPerformed
		* Description: 监听事件，点击按钮时做出相应反应
		* @param e
		* @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		*/ 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//ProducerConsumer producerConsumer=new ProducerConsumer();
			if(e.getSource()==MyFrame.startButton) {
				
				//点击开始按钮
				//将显示框清空
				MyFrame.textArea.setText("");
				util.clear();
				//MyFrame.startButton.setText("暂停");
				String numbers=(String) MyFrame.jcb.getSelectedItem();
				ProducerConsumer producerConsumer=new ProducerConsumer();
				producerConsumer.setMAX(Integer.valueOf(numbers));
				producerConsumer.run();
				
			}
			
			if(e.getSource()==MyFrame.helpButton) {
				//点击按钮
				File file=new File("./source/help.text");
				try {
					new MyHelp().showHelpWin(file);
				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(MyFrame.this, "无法打开帮助文档", 
						"帮助", JOptionPane.PLAIN_MESSAGE);//plain 平的，朴实无华的
				}
				
			}
		}
	}
	
	private void setFrameCenter(JFrame jFrame) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		double screenWidth = d.getWidth();
		double screenHeight = d.getHeight();
		int frameWidth = jFrame.getWidth();
		int frameHeight = jFrame.getHeight();
		
		int width = (int)(screenWidth-frameWidth)/2;
		int height = (int)(screenHeight-frameHeight)/2;
		jFrame.setLocation(width,height);
	}



	/**
	* @Title: setTopLeftPanel
	* @Description:    // 定义左上角组件部分
	* @return void    返回类型
	* @throws
	*/ 
	private JPanel setTopLeftPanel() {
		JLabel numberLabel=new JLabel("公共缓冲池最大缓冲区数量");//lable标签
		JPanel panel = new JPanel();//面板
		panel.setBorder(BorderFactory.createTitledBorder(null, "选择", TitledBorder.CENTER, TitledBorder.TOP));
		// 重置topPanel1的布局
		panel.setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);

		//下拉框
		String []numberStr= {"1","2","5"};
		jcb=new JComboBox<String>(numberStr);
		jcb.setSelectedIndex(1);

		// 设置各组件在panel里的位置
		numberLabel.setBounds(70, 20, 200, 20);
		panel1.add(numberLabel);

		jcb.setBounds(70, 10, 150, 20);
		panel2.add(jcb);

		panel1.setBounds(30, 15, 300, 49);
		panel2.setBounds(30, 70, 300, 90);
		panel.add(panel1);
		panel.add(panel2);
		return panel;
	}
	
	
	/**
	* @Title: setLablePanel
	* @Description: 定义图形显示区的组件
	* @return   
	* @return JPanel    返回类型
	* @throws
	*/ 
	private JPanel setLablePanel() {//设置标签面板
		JPanel panel=new JPanel();
		panel.setLayout(null);
		JLabel proLabel,comLabel,conLabel;
		proLabel=new JLabel("已生产产品");
		comLabel=new JLabel("公共缓冲池");
		conLabel=new JLabel("已消费产品");
		
		//设置布局
		proLabel.setBounds(35,5,200,20);
		comLabel.setBounds(285,5,200,20);
		conLabel.setBounds(535,5,200,20);
		
		panel.add(proLabel);
		panel.add(comLabel);
		panel.add(conLabel);
		return panel;
		
	}



	public synchronized static boolean isFull() {
		return full;
	}



	public synchronized static void setFull(boolean f) {
		full = f;
	}



	public synchronized static boolean isEmpty() {
		return empty;
	}



	public synchronized static void setEmpty(boolean e) {
		empty = e;
	}
}



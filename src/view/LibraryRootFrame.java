package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Date.BarTest;
import Date.PieChart;
import Date.PieTest;
/**
 * 管理员登陆后的主界面
 * 
 * @author 何兴旺
 *
 */
public class LibraryRootFrame extends JFrame {
	public static String USER_NAME;

	public LibraryRootFrame() {
		this.setLayout(null);
		ImageIcon img = new ImageIcon("image/3.jpg");
		//要设置的背景图片
		JLabel imgLabel = new JLabel(img);
		//将背景图放在标签里。
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		//将背景标签添加到jfram的LayeredPane面板里。
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// 设置背景标签的位置
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false); 

		setSize(1000, 730);
		setTitle("图书馆管理系统——管理员");
		setResizable(false); // 不可改变窗口大小
		// 获取屏幕大小和当前frame的大小
		Dimension thisScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension thisFrame = this.getSize();
		// 使启动窗口位于屏幕的正中心
		setLocation((thisScreen.width - thisFrame.width) / 2,
				(thisScreen.height - thisFrame.height) / 2);
		// 设置单击窗口的【关闭】按钮时将发生相应的动作
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar(); // 创建菜单栏
		// 创建菜单
		JMenu j1 = new JMenu("书籍管理");
		JMenuItem J1_1 = new JMenuItem("添加书籍");
		J1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BookAddFrame().setVisible(true);
			}
		});

		JMenuItem J1_2 = new JMenuItem("更新和删除书籍");
		J1_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Book_Update_Delete().setVisible(true);
			}
		});
		
		JMenuItem J1_3 = new JMenuItem("查找书籍");
		J1_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BookSearch().setVisible(true);
			}
		});
		
		JMenuItem J1_4 = new JMenuItem("查看所有书籍");
		J1_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AllBook().setVisible(true);
			}
		});
		j1.add(J1_1);
		j1.add(J1_2);
		j1.add(J1_3);
		j1.add(J1_4);

		JMenu j2 = new JMenu("用户管理");
		JMenuItem J2_1 = new JMenuItem("添加用户");
		J2_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UserAddFrame().setVisible(true);
			}
		});

		JMenuItem J2_2 = new JMenuItem("更新和删除用户");
		J2_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new User_Update_Delete().setVisible(true);
			}
		});

		JMenuItem J2_3 = new JMenuItem("查询用户");
		J2_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new UserSearch().setVisible(true);
			}
		});
		j2.add(J2_1);
		j2.add(J2_2);
		j2.add(J2_3);

		JMenu j3 = new JMenu("借书记录");
		JMenu J3 = new JMenu("借书数据分析");
		JMenuItem J3_1 = new JMenuItem("饼形图查看");
		J3_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new PieTest().setVisible(true);
			}
		});
		JMenuItem J3_2 = new JMenuItem("条形图查看");
		J3_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new BarTest().setVisible(true);
			}
		});
		JMenuItem j3_1 = new JMenuItem("查看借书记录");
		j3_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AllBorrow().setVisible(true);
			}
		});
		J3.add(J3_1);
		J3.add(J3_2);
		j3.add(J3);
		j3.add(j3_1);
		
		JMenu j4 = new JMenu("系统设置");
		JMenuItem j4_1 = new JMenuItem("修改密码");
		j4_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new RootXiugaiMima().setVisible(true);
			}
		});

		JMenuItem j4_2 = new JMenuItem("注销登录");
		j4_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new LogIn().setVisible(true);
			}
		});
		j4.add(j4_1);
		j4.add(j4_2);

		menuBar.add(j1);
		menuBar.add(j2);
		menuBar.add(j3);
		menuBar.add(j4);

		this.setJMenuBar(menuBar);
	}
	public static void main(String[] args) {
		new LibraryRootFrame().setVisible(true);
	}
}

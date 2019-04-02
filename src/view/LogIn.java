package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import utils.DBUtil;

/**
 * 登录界面
 * 
 * @author 何兴旺
 *
 */
public class LogIn extends JFrame {
	private JLabel labTitle = new JLabel("图书信息管理系统登录");
	private Font font = new Font("隶书", Font.BOLD, 34);
	private JButton btnSure = new JButton("登录");
	private JButton btnCancel = new JButton("重置");
	private JButton btnZhuce = new JButton("注册");
	private JPanel panBtn = new JPanel();

	private JLabel labLoginName = new JLabel("用户名:");
	private JLabel labPWD = new JLabel("密    码:");
	private JTextField jtfLoginName;
	private JPasswordField jpfPWD;
	private JPanel panMain = new JPanel();

	public LogIn() {
		setTitle("登录界面");
		// setDefaultLookAndFeelDecorated(true);
		this.setSize(500, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init();
		add(labTitle, BorderLayout.NORTH);
		add(panBtn, BorderLayout.SOUTH);
		add(panMain, BorderLayout.CENTER);
	}

	private void init() {
		labTitle.setFont(font);
		labTitle.setHorizontalAlignment(JLabel.CENTER);
		labTitle.setForeground(Color.RED);
		// labTitle.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		labTitle.setBackground(Color.BLUE);
		// 单选框
		JRadioButton JB1 = new JRadioButton("管理员登录");
		JRadioButton JB2 = new JRadioButton("用户登录");
		// 加入组，避免出现可以两个都选择的情况
		ButtonGroup bg = new ButtonGroup();
		JB2.setSelected(true);// 默认选中'用户登录'

		jtfLoginName = new JTextField("", 10);
		jpfPWD = new JPasswordField(16);

		labLoginName.setSize(100, 40);
		labLoginName.setLocation(50, 50);
		labLoginName.setHorizontalAlignment(JLabel.RIGHT);
		labPWD.setSize(100, 40);
		labPWD.setLocation(50, 50 + 40 + 10);
		labPWD.setHorizontalAlignment(JLabel.RIGHT);

		jtfLoginName.setSize(200, 40);
		jtfLoginName.setLocation(50 + 100 + 20, 50);
		jpfPWD.setSize(200, 40);
		jpfPWD.setLocation(50 + 100 + 20, 50 + 40 + 10);
		jpfPWD.setEchoChar('*');

		JB1.setSize(90, 20);
		JB1.setLocation(125, 175);
		JB2.setSize(80, 20);
		JB2.setLocation(275, 175);

		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JB2.isSelected()) {
					Statement stmt = DBUtil.getStatement();
					String name = jtfLoginName.getText().trim();
					String pwd = new String(jpfPWD.getPassword()).trim();
					String sql = "select * from user where username='" + name
							+ "' AND userpsw='" + pwd + "';";
					try {
						ResultSet rs = stmt.executeQuery(sql);
						if (rs.next()) {
							JOptionPane.showConfirmDialog(LogIn.this,
									"登陆成功，欢迎进入!", "登陆成功",
									JOptionPane.CLOSED_OPTION);
							dispose();
							new LibraryUserFrame().setVisible(true);
							LibraryUserFrame.USER_NAME = name;
						} else {
							JOptionPane.showMessageDialog(null, "账号或密码错误！");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					/*
					 * UserDao UD = new UserDao(); UserBean uBean =
					 * UD.findUser(name, pwd); if (uBean != null) {
					 * JOptionPane.showConfirmDialog(LogIn.this, "登陆成功，欢迎进入!",
					 * "登陆成功", JOptionPane.CLOSED_OPTION); dispose();
					 * LibraryUserFrame LU = new LibraryUserFrame();
					 * LU.setVisible(true); LibraryUserFrame.USER_NAME = name;
					 * }else { JOptionPane.showMessageDialog(null, "账号或密码错误！");
					 * }
					 */

				} else {
					Statement stmt = DBUtil.getStatement();
					String name = jtfLoginName.getText().trim();
					String pwd = new String(jpfPWD.getPassword()).trim();
					String sql = "select * from root where rootName='" + name
							+ "' AND rootPWD='" + pwd + "';";
					try {
						ResultSet rs = stmt.executeQuery(sql);
						if (rs.next()) {
							JOptionPane.showConfirmDialog(LogIn.this,
									"登陆成功，欢迎进入!", "登陆成功",
									JOptionPane.CLOSED_OPTION);
							dispose();
							LibraryRootFrame LR = new LibraryRootFrame();
							LR.setVisible(true);
							LibraryRootFrame.USER_NAME = name;
						} else {
							JOptionPane.showMessageDialog(null, "账号或密码错误！");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jtfLoginName.setText("");
				jpfPWD.setText("");
			}
		});

		btnZhuce.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ZhuceFrame().setVisible(true);
			}
		});

		panBtn.add(btnSure);
		panBtn.add(btnCancel);
		panBtn.add(btnZhuce);
		// 把单选按钮加入组
		bg.add(JB1);
		bg.add(JB2);

		panMain.setLayout(null);
		panMain.add(labLoginName);
		panMain.add(labPWD);
		panMain.add(jtfLoginName);
		panMain.add(jpfPWD);
		panMain.add(JB1);
		panMain.add(JB2);
		ImageIcon img = new ImageIcon(
				"C:\\Users\\mac\\workspace\\liberty\\image\\ZhuCe.png");
		// 要设置的背景图片
		JLabel imgLabel = new JLabel(img);
		// 将背景图放在标签里。
		panMain.add(imgLabel, new Integer(Integer.MIN_VALUE));
		// 将背景标签添加到jfram的LayeredPane面板里。
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		// 设置背景标签的位置
		Container contain = this.getContentPane();
		((JPanel) contain).setOpaque(false);
	}

	public static void main(String[] args) {
		new LogIn().setVisible(true);
	}
}

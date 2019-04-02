package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import utils.DBUtil;

/**
 * 用户的注册界面
 * 
 * @author 何兴旺
 *
 */
public class ZhuceFrame extends JFrame {
	private JLabel labTitle = new JLabel("图书信息管理系统注册");
	private Font font = new Font("隶书", 0, 24);
	private JButton btnSure = new JButton("注册");
	private JButton btnCancel = new JButton("重置");
	private JPanel panBtn = new JPanel();

	private JLabel labLoginName = new JLabel("用户名:");
	private JLabel labPWD = new JLabel("密    码:");
	private JLabel labEmail = new JLabel("邮    箱:");
	private JLabel labAddr = new JLabel("住    址:");
	private JLabel labPhone = new JLabel("电    话:");
	private JTextField jtfLoginName, jtfAddr, jtfPhone, jtfEmail;
	private JPasswordField jpfPWD;
	private JPanel panMain = new JPanel();

	public ZhuceFrame() {
		setTitle("用户注册界面");
		this.setSize(400, 300);
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
		labTitle.setBackground(Color.BLUE);

		jtfLoginName = new JTextField("", 10);
		jpfPWD = new JPasswordField(16);
		jtfAddr = new JTextField("", 20);
		jtfEmail = new JTextField("", 20);
		jtfPhone = new JTextField("", 20);

		labLoginName.setSize(100, 80);
		labLoginName.setLocation(50, 20);
		labLoginName.setHorizontalAlignment(JLabel.RIGHT);

		labPWD.setSize(100, 80);
		labPWD.setLocation(50, 40);
		labPWD.setHorizontalAlignment(JLabel.RIGHT);

		labEmail.setSize(100, 80);
		labEmail.setLocation(50, 60);
		labEmail.setHorizontalAlignment(JLabel.RIGHT);

		labAddr.setSize(100, 80);
		labAddr.setLocation(50, 80);
		labAddr.setHorizontalAlignment(JLabel.RIGHT);

		labPhone.setSize(100, 80);
		labPhone.setLocation(50, 100);
		labPhone.setHorizontalAlignment(JLabel.RIGHT);

		jtfLoginName.setSize(150, 20);
		jtfLoginName.setLocation(160, 50);

		jpfPWD.setSize(150, 20);
		jpfPWD.setLocation(160, 70);
		jpfPWD.setEchoChar('*');

		jtfEmail.setSize(150, 20);
		jtfEmail.setLocation(160, 90);

		jtfAddr.setSize(150, 20);
		jtfAddr.setLocation(160, 110);

		jtfPhone.setSize(150, 20);
		jtfPhone.setLocation(160, 130);

		panBtn.add(btnSure);
		panBtn.add(btnCancel);
		panMain.setLayout(null);

		panMain.add(labLoginName);
		panMain.add(labPWD);
		panMain.add(labAddr);
		panMain.add(labEmail);
		panMain.add(labPhone);

		panMain.add(jtfLoginName);
		panMain.add(jpfPWD);
		panMain.add(jtfAddr);
		panMain.add(jtfPhone);
		panMain.add(jtfEmail);

		btnSure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 获取用户输入的信息
				String name = jtfLoginName.getText().trim();
				String addr = jtfAddr.getText().trim();
				String email = jtfEmail.getText().trim();
				String phone = jtfPhone.getText().trim();
				String password = jpfPWD.getText().trim();

				Statement stmt = DBUtil.getStatement();
				String sql = "select * from user where username='" + name
						+ "';";
				String sql2 = "insert into user(username,userpsw,useremail,useraddr,userphone) value('"
						+ name
						+ "','"
						+ password
						+ "','"
						+ email
						+ "','"
						+ addr + "','" + phone + "')";
				try {
					ResultSet rs = stmt.executeQuery(sql); // 查看用户是否重名
					if (!rs.next()) {
						stmt.execute(sql2);// 向数据库里添加用户信息，注册成功
						JOptionPane.showMessageDialog(null, "恭喜您，注册成功", "恭喜",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "账号已存在！");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jtfLoginName.setText("");
				jpfPWD.setText("");
				jtfAddr.setText("");
				jtfPhone.setText("");
				jtfEmail.setText("");
			}
		});
	}
}

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
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
 * 管理员增加用户的界面
 * 
 * @author 何兴旺
 *
 */
public class UserAddFrame extends JFrame {
	private JPanel panBtn = new JPanel();
	private JPanel panLab = new JPanel();
	private JLabel jLabel1 = new JLabel("用户名：");
	private JLabel jLabel2 = new JLabel("密    码：");
	private JLabel jLabel3 = new JLabel("邮    箱：");
	private JLabel jLabel4 = new JLabel("地    址：");
	private JLabel jLabel5 = new JLabel("电    话：");
	private JTextField jTextField1 = new JTextField();
	private JPasswordField jPasswordField = new JPasswordField();
	private JTextField jTextField3 = new JTextField();
	private JTextField jTextField4 = new JTextField();
	private JTextField jTextField5 = new JTextField();
	private JButton jButton1 = new JButton("添加");
	private JButton jButton2 = new JButton("重置");
	private JLabel jLabel6 = new JLabel();

	public UserAddFrame() {
		setTitle("添加用户");
		setSize(400, 300);
		setResizable(false); // 不可改变窗口大小
		// 获取屏幕大小和当前frame的大小
		Dimension thisScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension thisFrame = this.getSize();
		// 使启动窗口位于屏幕的正中心
		setLocation((thisScreen.width - thisFrame.width) / 2,
				(thisScreen.height - thisFrame.height) / 2);
		// 设置单击窗口的【关闭】按钮时将发生相应的动作
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		jLabel6.setFont(new Font("宋体", 0, 24));
		jLabel6.setHorizontalAlignment(JLabel.CENTER);
		jLabel6.setForeground(new Color(255, 51, 51));
		jLabel6.setText("添  加  用  户");

		jLabel1.setSize(100, 80);
		jLabel1.setLocation(50, 20);
		jLabel1.setHorizontalAlignment(JLabel.RIGHT);

		jLabel2.setSize(100, 80);
		jLabel2.setLocation(50, 40);
		jLabel2.setHorizontalAlignment(JLabel.RIGHT);

		jLabel3.setSize(100, 80);
		jLabel3.setLocation(50, 60);
		jLabel3.setHorizontalAlignment(JLabel.RIGHT);

		jLabel4.setSize(100, 80);
		jLabel4.setLocation(50, 80);
		jLabel4.setHorizontalAlignment(JLabel.RIGHT);

		jLabel5.setSize(100, 80);
		jLabel5.setLocation(50, 100);
		jLabel5.setHorizontalAlignment(JLabel.RIGHT);

		jTextField1.setSize(150, 20);
		jTextField1.setLocation(160, 50);

		jPasswordField.setSize(150, 20);
		jPasswordField.setLocation(160, 70);

		jTextField3.setSize(150, 20);
		jTextField3.setLocation(160, 90);

		jTextField4.setSize(150, 20);
		jTextField4.setLocation(160, 110);

		jTextField5.setSize(150, 20);
		jTextField5.setLocation(160, 130);

		panBtn.add(jButton1);
		panBtn.add(jButton2);
		panLab.setLayout(null);

		panLab.add(jLabel1);
		panLab.add(jLabel2);
		panLab.add(jLabel3);
		panLab.add(jLabel4);
		panLab.add(jLabel5);

		panLab.add(jTextField1);
		panLab.add(jPasswordField);
		panLab.add(jTextField3);
		panLab.add(jTextField4);
		panLab.add(jTextField5);

		add(jLabel6, BorderLayout.NORTH);
		add(panBtn, BorderLayout.SOUTH);
		add(panLab, BorderLayout.CENTER);

		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String username = jTextField1.getText().trim();
				String userpwd = jPasswordField.getText().trim();
				String email = jTextField3.getText().trim();
				String addr = jTextField4.getText().trim();
				String phone = jTextField5.getText().trim();

				Statement stmt = DBUtil.getStatement();
				String sql = "select * from user where username='" + username
						+ "';";
				String sql2 = "insert into user(username,userpsw,useremail,useraddr,userphone) value('"
						+ username
						+ "','"
						+ userpwd
						+ "','"
						+ email
						+ "','"
						+ addr + "','" + phone + "');";
				try {
					ResultSet rs = stmt.executeQuery(sql);
					if (!rs.next()) {
						stmt.executeUpdate(sql2);
						JOptionPane.showMessageDialog(null, "添加成功。");
					} else {
						JOptionPane.showMessageDialog(null, "用户名已存在！");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		jButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jTextField1.setText("");
				jPasswordField.setText("");
				jTextField3.setText("");
				jTextField4.setText("");
				jTextField5.setText("");
			}
		});
	}
}

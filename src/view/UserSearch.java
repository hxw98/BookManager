package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import utils.DBUtil;
import view.BookSearch.MyModel;

/**
 * 管理员查找用户的界面
 * 
 * @author 何兴旺
 *
 */
public class UserSearch extends JFrame {
	private JScrollPane jsp = new JScrollPane();
	private JTable table = new JTable();

	private int row;
	private MyModel model;
	private JButton jButton1 = new JButton("查找");
	private JLabel label1 = new JLabel("查找方式：");
	private JTextField jTextField = new JTextField();

	private JLabel labTitle = new JLabel("查  找  用  户");
	private Font font = new Font("隶书", Font.BOLD, 34);

	public UserSearch() {
		setTitle("用户查找");
		setSize(500, 400);
		setResizable(false); // 不可改变窗口大小
		// 获取屏幕大小和当前frame的大小
		Dimension thisScreen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension thisFrame = this.getSize();
		// 使启动窗口位于屏幕的正中心
		setLocation((thisScreen.width - thisFrame.width) / 2,
				(thisScreen.height - thisFrame.height) / 2);
		// 设置单击窗口的【关闭】按钮时将发生相应的动作
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		setLocationRelativeTo(null);

		labTitle.setFont(font);
		labTitle.setHorizontalAlignment(JLabel.CENTER);
		labTitle.setForeground(Color.RED);

		// 设置可见视图的接口
		jsp.setViewportView(table);
		// 定义表格 宽500，高度250
		jsp.setPreferredSize(new Dimension(500, 250));
		// 设置横向和垂直滚动条可见
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		JPanel jp = new JPanel();
		jp.setLayout(null);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("按用户名查找");
		comboBox.addItem("按用户电话查找");

		label1.setSize(80, 80);
		label1.setLocation(30, 5);

		comboBox.setSize(110, 30);
		comboBox.setLocation(100, 30);

		jTextField.setSize(130, 32);
		jTextField.setLocation(225, 30);

		jButton1.setSize(80, 30);
		jButton1.setLocation(370, 30);

		jp.add(label1);
		jp.add(comboBox);
		jp.add(jTextField);
		jp.add(jButton1);

		this.setLayout(new BorderLayout());
		this.add(labTitle, BorderLayout.NORTH);
		this.add(jp, BorderLayout.CENTER);
		this.add(jsp, BorderLayout.SOUTH);

		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// 获取comboBox下拉选择框的内容
				String com = (String) comboBox.getSelectedItem();

				if (com.equals("按用户名查找")) {
					String s = jTextField.getText().trim();
					String sql = "select * from user where username like '%"
							+ s + "%';";
					model = new MyModel(sql);
					table.setModel(model);
					table.getColumnModel().getColumn(0).setHeaderValue("用户ID");
					table.getColumnModel().getColumn(1).setHeaderValue("用 户 名");
					table.getColumnModel().getColumn(2).setHeaderValue("用户密码");
					table.getColumnModel().getColumn(3).setHeaderValue("用户邮箱");
					table.getColumnModel().getColumn(4).setHeaderValue("用户地址");
					table.getColumnModel().getColumn(5).setHeaderValue("用户电话");
				} else {
					String s = jTextField.getText().trim();
					String sql = "select * from user where userphone like '%"
							+ s + "%';";
					model = new MyModel(sql);
					table.setModel(model);
					table.getColumnModel().getColumn(0).setHeaderValue("用户ID");
					table.getColumnModel().getColumn(1).setHeaderValue("用 户 名");
					table.getColumnModel().getColumn(2).setHeaderValue("用户密码");
					table.getColumnModel().getColumn(3).setHeaderValue("用户邮箱");
					table.getColumnModel().getColumn(4).setHeaderValue("用户地址");
					table.getColumnModel().getColumn(5).setHeaderValue("用户电话");
				}
			}
		});
	}

	class MyModel extends AbstractTableModel {
		private int row;
		private int column;
		private ResultSet rs;
		private Statement stmt;

		public MyModel(String sql) {
			stmt = DBUtil.getStatement();
			try {
				rs = stmt.executeQuery(sql);
				rs.last();// 将光标移到最后一行
				row = rs.getRow();// 获取行号(最大行索引)
				ResultSetMetaData rsmd = rs.getMetaData();// 通过结果集对象来获取
				column = rsmd.getColumnCount();// 获取列数
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public int getColumnCount() {
			return column;
		}

		@Override
		public int getRowCount() {
			return row;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Object value = null;
			try {
				rs.absolute(rowIndex + 1);
				value = rs.getString(columnIndex + 1);// 获取表里的数据
			} catch (Exception e) {
				e.printStackTrace();
			}
			return value;
		}
	}
}

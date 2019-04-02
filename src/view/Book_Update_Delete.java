package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import utils.DBUtil;

/**
 * 管理员修改和删除书籍的类
 * 
 * @author 何兴旺
 *
 */
public class Book_Update_Delete extends JFrame {
	private JScrollPane jsp = new JScrollPane();
	private JTable table = new JTable();
	private JButton btn1 = new JButton("修改");
	private JButton btn2 = new JButton("删除");
	private int row;
	private MyModel model;

	private JLabel jLabel1 = new JLabel("图书编号：");
	private JLabel jLabel2 = new JLabel("图书名称：");
	private JLabel jLabel3 = new JLabel("图书作者：");
	private JLabel jLabel4 = new JLabel("图书类型：");
	private JLabel jLabel5 = new JLabel("图书价格：");
	private JTextField jTextField1 = new JTextField();
	private JTextField jTextField2 = new JTextField();
	private JTextField jTextField3 = new JTextField();
	private JTextField jTextField4 = new JTextField();
	private JTextField jTextField5 = new JTextField();

	private JButton jButton1 = new JButton("查找");
	private JTextField jTextField = new JTextField();

	private Panel panBtn = new Panel();
	private Panel panLab = new Panel();

	public Book_Update_Delete() {
		this.setSize(600, 500);
		setTitle("图书信息修改与删除");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		model = new MyModel("select * from book;");
		table.setModel(model);
		// 修改列名称
		table.getColumnModel().getColumn(0).setHeaderValue("书号");
		table.getColumnModel().getColumn(1).setHeaderValue("书名");
		table.getColumnModel().getColumn(2).setHeaderValue("作者");
		table.getColumnModel().getColumn(3).setHeaderValue("类型");
		table.getColumnModel().getColumn(4).setHeaderValue("价格");
		table.getColumnModel().getColumn(5).setHeaderValue("是否被借");

		// 设置可见视图的接口
		jsp.setViewportView(table);
		// 定义表格 宽600，高度200
		jsp.setPreferredSize(new Dimension(600, 200));
		// 设置横向和垂直滚动条可见
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		panBtn.add(btn1);
		panBtn.add(btn2);
		// 设置中间的panel布局为空
		panLab.setLayout(null);
		// 单选框
		JRadioButton JB1 = new JRadioButton("按书名查找");
		JRadioButton JB2 = new JRadioButton("按作者查找");
		JB1.setSelected(true); // 默认选择JB1
		// 加入组，避免出现可以两个都选择的情况
		ButtonGroup bg = new ButtonGroup();
		bg.add(JB1);
		bg.add(JB2);

		// 给组件设置位置
		JB1.setSize(100, 30);
		JB1.setLocation(40, 20);

		JB2.setSize(100, 30);
		JB2.setLocation(140, 20);

		jTextField.setSize(140, 30);
		jTextField.setLocation(250, 20);

		jButton1.setSize(80, 30);
		jButton1.setLocation(420, 20);

		jLabel1.setSize(100, 100);
		jLabel1.setLocation(10, 30);
		jLabel1.setHorizontalAlignment(JLabel.RIGHT);
		jTextField1.setSize(150, 30);
		jTextField1.setLocation(110, 65);

		jLabel2.setSize(100, 100);
		jLabel2.setLocation(280, 30);
		jLabel2.setHorizontalAlignment(JLabel.RIGHT);
		jTextField2.setSize(150, 30);
		jTextField2.setLocation(380, 65);

		jLabel3.setSize(100, 100);
		jLabel3.setLocation(10, 90);
		jLabel3.setHorizontalAlignment(JLabel.RIGHT);
		jTextField3.setSize(150, 30);
		jTextField3.setLocation(110, 125);

		jLabel4.setSize(100, 100);
		jLabel4.setLocation(280, 90);
		jLabel4.setHorizontalAlignment(JLabel.RIGHT);
		jTextField4.setSize(150, 30);
		jTextField4.setLocation(380, 125);

		jLabel5.setSize(100, 100);
		jLabel5.setLocation(10, 150);
		jLabel5.setHorizontalAlignment(JLabel.RIGHT);
		jTextField5.setSize(150, 30);
		jTextField5.setLocation(110, 185);
		// 把标签和文本框加到panLab面板中
		panLab.add(jLabel1);
		panLab.add(jLabel2);
		panLab.add(jLabel3);
		panLab.add(jLabel4);
		panLab.add(jLabel5);
		panLab.add(jTextField1);
		panLab.add(jTextField2);
		panLab.add(jTextField3);
		panLab.add(jTextField4);
		panLab.add(jTextField5);
		panLab.add(JB1);
		panLab.add(JB2);
		panLab.add(jTextField);
		panLab.add(jButton1);

		this.add(jsp, BorderLayout.NORTH);
		this.add(panLab, BorderLayout.CENTER);
		this.add(panBtn, BorderLayout.SOUTH);

		// 获取表里的值
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id, name, type, writter, price;
				int selRow = table.getSelectedRow();
				id = table.getValueAt(selRow, 0).toString().trim();
				name = table.getValueAt(selRow, 1).toString().trim();
				type = table.getValueAt(selRow, 2).toString().trim();
				writter = table.getValueAt(selRow, 3).toString().trim();
				price = table.getValueAt(selRow, 4).toString().trim();
				jTextField1.setText(id);
				jTextField2.setText(name);
				jTextField3.setText(type);
				jTextField4.setText(writter);
				jTextField5.setText(price);

			}

		});

		jTextField1.setEditable(false);

		jButton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (JB1.isSelected()) {
					String s = jTextField.getText().trim();
					String sql = "select * from book where BookName like '%"
							+ s + "%';";
					model = new MyModel(sql);
					table.setModel(model);
					table.getColumnModel().getColumn(0).setHeaderValue("书号");
					table.getColumnModel().getColumn(1).setHeaderValue("书名");
					table.getColumnModel().getColumn(2).setHeaderValue("作者");
					table.getColumnModel().getColumn(3).setHeaderValue("类型");
					table.getColumnModel().getColumn(4).setHeaderValue("价格");
					table.getColumnModel().getColumn(5).setHeaderValue("是否被借");
				} else {
					String s = jTextField.getText().trim();
					String sql = "select * from book where Writter like '%" + s
							+ "%';";
					model = new MyModel(sql);
					table.setModel(model);
					table.getColumnModel().getColumn(0).setHeaderValue("书号");
					table.getColumnModel().getColumn(1).setHeaderValue("书名");
					table.getColumnModel().getColumn(2).setHeaderValue("作者");
					table.getColumnModel().getColumn(3).setHeaderValue("类型");
					table.getColumnModel().getColumn(4).setHeaderValue("价格");
					table.getColumnModel().getColumn(5).setHeaderValue("是否被借");
				}
			}
		});

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = jTextField1.getText().trim();
				int i = Integer.parseInt(id);
				String name = jTextField2.getText().trim();
				String type = jTextField3.getText().trim();
				String writer = jTextField4.getText().trim();
				String price = jTextField5.getText().trim();
				double p = Double.parseDouble(price);

				Statement stmt = DBUtil.getStatement();
				String sql = "update book set BookName = '" + name
						+ "',BookType = '" + type + "',Writter = '" + writer
						+ "',Price = " + p + " where BookId = " + i + ";";
				try {
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "修改成功。");
					// 实时更新表
					// model.fireTableDataChanged();
					// model = new MyModel("select * from book;");
					// table.setModel(model);
					dispose();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String id = jTextField1.getText().trim();
				int i = Integer.parseInt(id);
				int result = JOptionPane.showConfirmDialog(null, "确定删除吗?",
						"提示", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					Statement stmt = DBUtil.getStatement();
					String sql = "delete from book where BookId = " + i + ";";
					try {
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog(null, "已删除！");
						// 实时更新表格数据
						// model = new MyModel();
						// table.setModel(model);
						dispose();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					dispose();
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

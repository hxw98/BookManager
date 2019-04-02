package Date;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import utils.DBUtil;

/**
 * 该类是获取借书信息（以条形图查看）
 * 
 * @author 何兴旺
 *
 */
public class BarChart {
	ChartPanel frame1;

	public BarChart() {
		CategoryDataset dataset = getDataSet();
		JFreeChart chart = ChartFactory.createBarChart3D("不同种类书的借阅量", // 图表标题
				"书的种类", // 目录轴的显示标签
				"借阅量", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				false, // 是否生成工具
				false // 是否生成URL链接
				);

		// 从这里开始
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

		frame1 = new ChartPanel(chart, true); // 这里也可以用chartFrame,可以直接生成一个独立的Frame

	}

	private static CategoryDataset getDataSet() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String sql1 = "select count(BookType = '青春文学' OR null) as '青春文学',count(BookType = '哲学' OR null) as '哲学' ,count(BookType = '科技类' OR null) as '科技类',count(BookType = '文学类' OR null) as '文学类',count(BookType = '人文自然' OR null) as '人文自然',count(BookType = '综合性' OR null) as '综合性',count(BookType = '技术类' OR null) as '技术类' from borrow;";
		Statement stmt = DBUtil.getStatement();
		try {
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				dataset.addValue(rs1.getInt(1), "青春文学", "青春文学");
				dataset.addValue(rs1.getInt(2), "哲学", "哲学");
				dataset.addValue(rs1.getInt(3), "科技类", "科技类");
				dataset.addValue(rs1.getInt(4), "文学类", "文学类");
				dataset.addValue(rs1.getInt(5), "人文自然", "人文自然");
				dataset.addValue(rs1.getInt(6), "综合性", "综合性");
				dataset.addValue(rs1.getInt(7), "技术类", "技术类");
				return dataset;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataset;
	}

	public ChartPanel getChartPanel() {
		return frame1;
	}
}

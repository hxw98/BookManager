package Date;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import utils.DBUtil;

/**
 * 该类是获取借书信息（以饼形图查看）
 * 
 * @author 何兴旺
 *   
 */
public class PieChart {
	ChartPanel frame1;

	public PieChart() {
		DefaultPieDataset data = getDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D("图书借阅量", data, true,
				false, false);
		// 设置百分比
		PiePlot pieplot = (PiePlot) chart.getPlot();
		DecimalFormat df = new DecimalFormat("0.00%");// 获得一个DecimalFormat对象，主要是设置小数问题
		NumberFormat nf = NumberFormat.getNumberInstance();// 获得一个NumberFormat对象
		StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator(
				"{0}  {2}", nf, df);// 获得StandardPieSectionLabelGenerator对象
		pieplot.setLabelGenerator(sp1);// 设置饼图显示百分比

		// 没有数据的时候显示的内容
		pieplot.setNoDataMessage("无数据显示");
		pieplot.setCircular(false);
		pieplot.setLabelGap(0.02D);

		pieplot.setIgnoreNullValues(true);// 设置不显示空值
		pieplot.setIgnoreZeroValues(true);// 设置不显示负值
		frame1 = new ChartPanel(chart, true);
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体
		PiePlot piePlot = (PiePlot) chart.getPlot();// 获取图表区域对象
		piePlot.setLabelFont(new Font("宋体", Font.BOLD, 10));// 解决乱码
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));
	}

	private static DefaultPieDataset getDataSet() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		String sql1 = "select count(BookType = '青春文学' OR null) as '青春文学',count(BookType = '哲学' OR null) as '哲学' ,count(BookType = '科技类' OR null) as '科技类',count(BookType = '文学类' OR null) as '文学类',count(BookType = '人文自然' OR null) as '人文自然',count(BookType = '综合性' OR null) as '综合性',count(BookType = '技术类' OR null) as '技术类' from borrow;";
		Statement stmt = DBUtil.getStatement();
		try {
			ResultSet rs1 = stmt.executeQuery(sql1);
			while (rs1.next()) {
				dataset.setValue("青春文学", rs1.getInt(1));
				dataset.setValue("哲学", rs1.getInt(2));
				dataset.setValue("科技类", rs1.getInt(3));
				dataset.setValue("文学类", rs1.getInt(4));
				dataset.setValue("人文自然", rs1.getInt(5));
				dataset.setValue("综合性", rs1.getInt(6));
				dataset.setValue("技术类", rs1.getInt(7));
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

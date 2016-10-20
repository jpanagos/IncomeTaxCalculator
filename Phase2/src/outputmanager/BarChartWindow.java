package outputmanager;

import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import datamanager.Taxpayer;

public class BarChartWindow {

	private DefaultCategoryDataset objectDataset;
	private JFreeChart barChart;
	private ChartFrame barChartFrame;
	private ValueAxis valueAxis;
	private LegendTitle legend;

	public BarChartWindow(Taxpayer taxpayer) {
		objectDataset = new DefaultCategoryDataset();
		createObjectDataset(taxpayer);
		createBarChart(objectDataset);
		createBarChartFrame(barChart);
		showBarChartFrame();
	}

	private void createObjectDataset(Taxpayer taxpayer) {
		objectDataset.setValue(
				taxpayer.getBasicTax(), taxpayer.getFullName(), "Basic Tax");
		objectDataset.setValue(taxpayer.getTaxIncrease(),
				taxpayer.getFullName(), "Tax Increase");
		objectDataset.setValue(
				taxpayer.getTotalTax(), taxpayer.getFullName(), "Total Tax");
	}

	private void createBarChart(DefaultCategoryDataset objectDataset) {
		barChart = ChartFactory.createBarChart("", "", "Tax analysis in $",
			    objectDataset,
			    PlotOrientation.VERTICAL,
			    true,
			    true,
			    false
			);
	}

	private void createBarChartFrame(JFreeChart barChart) {
		barChartFrame = new ChartFrame("Ραβδόγραμμα φόρων", barChart);
		barChartFrame.setDefaultCloseOperation(ChartFrame.DISPOSE_ON_CLOSE);
		initializeLabels();
	}

	private void initializeLabels() {
		valueAxis = barChart.getCategoryPlot().getRangeAxis();
		valueAxis.setLabelAngle(Math.PI / 2);
		valueAxis.setLabelFont(new Font("Verdana", Font.BOLD, 13));
		legend = barChart.getLegend();
		legend.setItemFont(new Font("Verdana", Font.PLAIN, 13));
	}

	private void showBarChartFrame() {
		barChartFrame.setModalExclusionType(
				ModalExclusionType.APPLICATION_EXCLUDE);
		barChartFrame.pack();
		barChartFrame.setLocationRelativeTo(null);
		barChartFrame.setVisible(true);
	}

}

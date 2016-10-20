package outputmanager;

import datamanager.Taxpayer;
import java.awt.Font;
import java.awt.Dialog.ModalExclusionType;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartWindow {
	
	private DefaultPieDataset objectDataset;
	private JFreeChart pieChart;
	private ChartFrame pieChartFrame;
	private LegendTitle legend;
	private PieSectionLabelGenerator labelGenerator;
	private PiePlot piePlot;
	
	
	public PieChartWindow(Taxpayer taxpayer) {
		objectDataset = new DefaultPieDataset();
		createObjectDataset(taxpayer);
		createPieChart(objectDataset);
		createPieChartFrame(pieChart);
		showPieChartFrame();
	}
	
	private void createObjectDataset(Taxpayer taxpayer) {
		objectDataset.setValue("Entertainment", taxpayer.getEntertainmentReceiptsSum());
		objectDataset.setValue("Basic", taxpayer.getBasicReceiptsSum());
		objectDataset.setValue("Travel", taxpayer.getTravelReceiptsSum());
		objectDataset.setValue("Health", taxpayer.getHealthReceiptsSum());
		objectDataset.setValue("Other", taxpayer.getOtherReceiptsSum());
	}
	
	private void createPieChart(DefaultPieDataset objectDataset) {
		pieChart = ChartFactory.createPieChart (
			    "",
			    objectDataset,
			    true,
			    true, 
			    false
			    );
	}
	
	private void createPieChartFrame(JFreeChart pieChart) {
		pieChartFrame = new ChartFrame("Διάγραμμα πίτας αποδείξεων", pieChart);
		pieChartFrame.setDefaultCloseOperation(ChartFrame.DISPOSE_ON_CLOSE);
		initializeLabels();
	}
	
	private void initializeLabels() {
		labelGenerator = new StandardPieSectionLabelGenerator("{0}; {1}");
		piePlot = (PiePlot) pieChart.getPlot();
		piePlot.setLabelGenerator(labelGenerator);
		legend = pieChart.getLegend();
		legend.setItemFont(new Font("Verdana", Font.PLAIN, 13));
	}
	
	private void showPieChartFrame() {
		pieChartFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		pieChartFrame.pack();
		pieChartFrame.setLocationRelativeTo(null);
		pieChartFrame.setVisible(true);
	}
	
}

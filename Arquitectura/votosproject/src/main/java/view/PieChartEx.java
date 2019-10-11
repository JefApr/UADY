package view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChartEx extends JFrame {

    public PieChartEx() {
        initUI();
    }

    private void initUI() {

        DefaultPieDataset dataset = createDataset();

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Pie chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private DefaultPieDataset createDataset() {

        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Apache", 10);
        dataset.setValue("Nginx", 10);
        dataset.setValue("IIS", 100);
        return dataset;
    }

    private JFreeChart createChart(DefaultPieDataset dataset) {

        JFreeChart barChart = ChartFactory.createPieChart(
                "Web servers market share",
                dataset,
                true, true, true);

        return barChart;
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PieChartEx ex = new PieChartEx();
                ex.setVisible(true);
                BarChartEx x2 = new BarChartEx();
                x2.setVisible(true);
            }
        });
    }
}
package view;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

import Interfaces.Observer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartEx extends JFrame  implements Observer {

    public BarChartEx() {

        initUI();
    }

    private void initUI() {
        Map map= new HashMap<String, Integer>();
        map.put("c1", 2);
        map.put("c2", 1);
        map.put("c3", 5);

        add(createPanel(map));
        pack();
        setTitle("Bar chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private ChartPanel createPanel(Map<String, Integer> votes){
        CategoryDataset dataset = createDataset(votes);

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        return chartPanel;
    }

    private CategoryDataset createDataset(Map<String, Integer> votes) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Integer> vote : votes.entrySet()) {
            dataset.setValue(vote.getValue(), "Votes", vote.getKey());
            System.out.println("value: " + vote.getValue()+" key: "+vote.getKey());
        }
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "Olympic gold medals in London",
                "",
                "Gold medals",
                dataset,
                PlotOrientation.HORIZONTAL,
                true, true, true);

        return barChart;
    }


    @Override
    public void update(Object o) {
        Map map= (HashMap<String, Integer>)o;

    removeAll();
    revalidate();
    ChartPanel chartPanel= createPanel(map);
        add(chartPanel);
    repaint();
        System.out.println(map.get("c1").toString());
    }
}
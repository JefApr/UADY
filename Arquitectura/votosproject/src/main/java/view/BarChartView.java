package view;

import Interfaces.Observer;
import controller.Controller;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class BarChartView extends View implements Observer{

    public BarChartView(Controller controller){
        super(controller);
    }


    @Override
    public void startGUI(String title, String[] items) {
        container= new BarChartPanel();
        ((BarChartPanel) container).createEmptyChart(items);
        GUI= new JFrame(title);
        GUI.add(container);
        GUI.setSize(600, 600);
        GUI.setVisible(true);
    }




    @Override
    public void update(Object o) {
        ((BarChartPanel) container).updateChart((HashMap<Object, Object>) o);
    }

    private class BarChartPanel extends ChartPanel{

        private DefaultCategoryDataset data;

        public BarChartPanel() {
            super(null);
        }

        public void createEmptyChart(String[] items){
            data= new DefaultCategoryDataset();
            for(String item : items){
                data.setValue(0, item,"" );
            }
        }

        public void updateChart(HashMap<Object, Object> newData){
            for(Map.Entry<Object, Object> entry : newData.entrySet()) {
                data.setValue((Integer) entry.getValue(),(Comparable) entry.getKey(),"" );
            }
            setChart(ChartFactory.createBarChart(
                    "Gráfica de barras", "","",data, PlotOrientation.VERTICAL, true, true, true
            ));
        }


    }


}

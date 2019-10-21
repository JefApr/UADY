package view;

import controller.Controller;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import Interfaces.Observer;

public class PieChartView extends View implements Observer {



    public  PieChartView(Controller controller) {
        super(controller);
    }


    @Override
    public void startGUI(String title, String[] items) {
        container= new PieChartPanel();
        ((PieChartPanel) container).createEmptyChart(items);
        GUI= new JFrame(title);
        GUI.add(container);
        GUI.setSize(600, 600);
        GUI.setVisible(true);
    }

    @Override
    public void update(Object arg) {
        ((PieChartPanel) container).updateChart((HashMap<Object, Object>) arg);
    }


    private class PieChartPanel extends ChartPanel{

        private DefaultPieDataset data;

        public PieChartPanel() {
            super(null);
        }

        public void createEmptyChart(String[] items){
            data= new DefaultPieDataset();
            for(String item : items){
                data.setValue(item, 0);
            }
        }

        public void updateChart(HashMap<Object, Object> newData){
            for(Map.Entry<Object, Object> entry : newData.entrySet()) {
                data.setValue((Comparable) entry.getKey(),(Integer)entry.getValue());
            }
            setChart(ChartFactory.createPieChart(
                    "Gráfica de pastel", data, true, true, true
            ));
        }
    }

}

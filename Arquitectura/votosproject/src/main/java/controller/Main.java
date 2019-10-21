package controller;

import model.VoteModel;
import view.BarChartView;
import view.PieChartView;
import view.VoteView;

public class Main {
    public static void main(String[] args) {
        VoteModel model = new VoteModel();
        Controller controller = new Controller(model);

        VoteView voteView = new VoteView(controller);
        voteView.startGUI("Votos", model.getCandidates());

        PieChartView pieChart= new PieChartView(controller);
        pieChart.startGUI("Pie Chart", model.getCandidates());

        BarChartView barChart= new BarChartView(controller);
        barChart.startGUI("bar chart", model.getCandidates());

        controller.addObserver(pieChart);
        controller.addObserver(barChart);
        controller.addObserver(voteView);

    }

}

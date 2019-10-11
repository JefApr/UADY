package view;

import Interfaces.Controller;
import Interfaces.Observer;
import Interfaces.View;
import controller.VoteController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class countView extends View {
    private JButton addc1;
    private JButton subsc1;
    private JButton addc2;
    private JButton addc3;
    private JButton subsc2;
    private JButton subsc3;
    private JPanel panel;
    private String[] candidates;

    public countView(Controller controller){
        super(controller);

        candidates= new String[]{"c1", "c2", "c3"};
        try {
            ((VoteController)controller).setCandidates(candidates);
        } catch (Exception e) {
            e.printStackTrace();
        }
        addc1.addActionListener(new Action("addVote", candidates[0]));
        addc2.addActionListener(new Action("addVote", candidates[1]));
        addc3.addActionListener(new Action("addVote", candidates[2]));
        subsc1.addActionListener(new Action("substractVote", candidates[0]));
        subsc2.addActionListener(new Action("substractVote", candidates[1]));
        subsc3.addActionListener(new Action("substractVote", candidates[2]));

        pack();
        setTitle("Votes");
        setSize(250,150);
        add(panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    @Override
    public void sendRequestToController(String request, Object content) {
        try {
            controller.sendRequestToModel(new Event(request, content));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Action implements ActionListener{
        private String request;
        private Object content;

        public Action(String request, Object content) {
            this.request = request;
            this.content = content;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                sendRequestToController(request, content);
        }
    }


    public static void main(String[] args) {
        VoteController controller= new VoteController();
        PieChartEx ex = new PieChartEx();
        BarChartEx x2 = new BarChartEx();
        controller.addObserver(x2);
        countView x3= new countView(controller);




        SwingUtilities.invokeLater(new Runnable() {
            public void run() {


                ex.setVisible(true);

                x2.setVisible(true);

                x3.setVisible(true);
            }
        });
    }
}


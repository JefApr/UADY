package view;


import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class VoteView extends View {

    JFrame frame;

    public VoteView(Controller controller) {
        super(controller);
    }

    @Override
    public void startGUI(String title, String[] items) {
        frame= new JFrame("Votos");
        JPanel panel=new JPanel(new GridBagLayout());

        Arrays.stream(items).forEach(item -> {
            JButton button = new JButton(item);
            button.addActionListener(new AddVoteListener());
            panel.add(button);
        });

        frame.add(panel);
        frame.setSize(400,100);
        frame.setVisible(true);
    }

    private class AddVoteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            sendRequestToController(
                    "addVote",
                    ((JButton) e.getSource()).getText()
            );
        }
    }

    @Override
    public void update(Object o) {
        System.out.println(o.toString());
    }

}

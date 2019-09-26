import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class ScoreGui extends JDialog implements ActionListener {

    public ScoreGui() {
        super();
    }

    // the ScoreGui Method displays the scores in order from lowest to highest.
    public void ScoreGui() {
        Container cp = getContentPane();
        JButton ok = new JButton("OK");
        ok.setActionCommand("OK");
        ok.addActionListener(this);
        int lineNum = 0;
        cp.add(ok, BorderLayout.SOUTH);
        try {
            String line = "";
            String[] myScoreArray = new String[100];
            for (int i = 0; i < myScoreArray.length; i++)
                myScoreArray[i] = " ";
            String line1 = ""; 
             // read in the scores data                                    
            BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("scores.txt")));
                           
            int recordsCount = 0;
            while ((line = br1.readLine()) != null) {
                line = br1.readLine();
                if (line != "") {
                    recordsCount += 1;
                    // use the star to indicate the next charator is going to be the maze level which we will sort by.
                    int tempPOS = line.indexOf("*");
                    String pos = line.substring(tempPOS + 1);
                    int index = Integer.parseInt(pos);
                    if (myScoreArray[index] == " ")
                        myScoreArray[index] = line;
                    else {
                        for (int i = 0; i < myScoreArray.length; i++) {
                            // prevent array out of bounds errors.
                            if (index + i < myScoreArray.length)
                            {
                                if (myScoreArray[index + i].equals(" ")) {
                                    // add in a score to the next available area of the array
                                    myScoreArray[index + 1] = line;
                                }
                            }
                        }
                    }

                    JPanel scorePanel = new JPanel();
                    scorePanel.setLayout(new GridLayout(recordsCount, recordsCount));
                    for (int i = 0; i < myScoreArray.length; i++) {
                        if (myScoreArray[i] != " ") {
                            mainLabel = new JLabel(myScoreArray[i], JLabel.LEFT);
                            scorePanel.add(mainLabel);
                        }
                    }
                    cp.add(scorePanel);
                }
            }
        }
        catch (IOException ex) {
            JFrame frame = new JFrame("Alert");
            JOptionPane.showMessageDialog(frame, "Problem with scores.txt file.  Cant load high Scores");
        }

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        dispose();
    }

    private JLabel mainLabel;
}// end class
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

public class GameGui extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new GameGui();
    }

    public GameGui() {
        // call super to initilize title bar of G.U.I.
        super("Maze, a game of wondering"); 

        cp = getContentPane();
        shagLabel = new JLabel("", new ImageIcon("yeababyyea.jpg"), JLabel.LEFT);// GUI background for initial load
        cp.add(shagLabel);
        
        // press CTRL+X to exit if you want
        itemExit = new JMenuItem("Exit");
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
       
        // press CTRL+S to save high score if you want
        itemSaveScore = new JMenuItem("Save High Score");
        itemSaveScore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        
        // press CTRL+H to view  high score if you want
        itemHighScore = new JMenuItem("High Score");
        itemHighScore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK));
        
        // press CTRL+N to enter your name if you want        
        itemEnterName = new JMenuItem("Enter Player Name");

        itemEnterName.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        newGameItem = new JMenuItem("New Game");
        
        // press CTRL+O to open a level if you want
        openFileItem = new JMenuItem("Open Maze File.");
        openFileItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));

        newGameItem.setActionCommand("New Game");
        newGameItem.addActionListener(this);
        itemEnterName.setActionCommand("EnterName");
        itemEnterName.addActionListener(this);
        itemSaveScore.setActionCommand("SaveScore");
        itemSaveScore.addActionListener(this);
        itemHighScore.setActionCommand("HighScore");
        itemHighScore.addActionListener(this);
        itemExit.setActionCommand("Exit");
        itemExit.addActionListener(this);
        openFileItem.setActionCommand("Open");
        openFileItem.addActionListener(this);

        newMenu = new JMenu("File");
        newMenu.add(newGameItem);
        newMenu.add(itemEnterName);
        newMenu.add(openFileItem);
        newMenu.add(itemHighScore);
        newMenu.add(itemSaveScore);
        newMenu.add(itemExit);

        menuBar = new JMenuBar();
        menuBar.add(newMenu);
        setJMenuBar(menuBar);
        // Add Menu Bar
        newPanel = new JPanel();
        hs = new HighScore();
        tk = new TimeKeeper();
        pack();
        setVisible(true);
    }
    // captures arrow keys movement
    private class MyKeyHandler extends KeyAdapter 
    {
        public void keyPressed(KeyEvent theEvent) {
            switch (theEvent.getKeyCode()) {
                case KeyEvent.VK_UP: {
                    
                    // let the Architect know we moved, along with the current matrix
                    theArc.playerMove(-1, 0, scrapMatrix, fl.dimondCount());
                    loadMatrixGui("updateLoad");// reload the gui to show the move
                    if (theArc.getLevel() == true) {
                        nextLevelLoad();// if the player hit an exit door, load the next level
                    }
                    break;
                }

                case KeyEvent.VK_DOWN: {
                    theArc.playerMove(1, 0, scrapMatrix, fl.dimondCount());
                    loadMatrixGui("updateLoad");
                    if (theArc.getLevel() == true)
                    {
                        nextLevelLoad();
                    }
                    break;
                }

                case KeyEvent.VK_LEFT: {
                    theArc.playerMove(0, -1, scrapMatrix, fl.dimondCount());
                    loadMatrixGui("updateLoad");
                    if (theArc.getLevel() == true)
                    {
                        nextLevelLoad();
                    }
                    break;
                }

                case KeyEvent.VK_RIGHT: {
                    theArc.playerMove(0, 1, scrapMatrix, fl.dimondCount());
                    loadMatrixGui("updateLoad");
                    if (theArc.getLevel() == true) {
                        nextLevelLoad();
                    }
                    break;
                }
            }
            JLabel mainLabel = new JLabel("Total Dimonds Left to Collect" 
            + theArc.getDimondsLeft() 
            + "", JLabel.CENTER);
            JPanel dimondsPanel = new JPanel();
            dimondsPanel.add(mainLabel);
            cp.add(dimondsPanel, BorderLayout.SOUTH);
        }
    }// end inner class

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Exit"))
        {
            new Timer(1000, updateCursorAction).stop();
            System.exit(0);
        } else 
            // new game on the menu bar
            if (e.getActionCommand().equals("New Game"))
            {
            return;
            } else 
                // Allows user to enter their name for high score
                if (e.getActionCommand().equals("EnterName"))
                {
                    JOptionPane optionPane = new JOptionPane();
                    playerName = optionPane.showInputDialog("Please Enter your Earth Name");
                } else 
                // Displays the high scores
                if (e.getActionCommand().equals("HighScore"))
                {
                    ScoreGui sg = new ScoreGui();
                    sg.ScoreGui();
                } else 
                    // allows the user to save their score at any time.
                    if (e.getActionCommand().equals("SaveScore"))
                    {
                        hs.addHighScore(playerName, tk.getMinutes(), tk.getSeconds(), levelNum);
                    } else 
                    // to start the game you have to open a maze file. this is on the menu
                    if (e.getActionCommand().equals("Open"))
                    {
                        JFileChooser chooser = new JFileChooser();
                        int returnVal = chooser.showOpenDialog(this);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            fl.loadFile(chooser.getSelectedFile().getName());// load the file we need
                            theArc.setExit(fl.ExitXCord(), fl.ExitYCord());
                            loadMatrixGui("newLoad");
                        }
                    }
    }// end actionPerformed method

    public void loadMatrixGui(String event) {
        if (event == "newLoad") {
            // remove the previous level's game from the screen
            remove(newPanel);
            if (progBarPanel != null)
                // remove the progress bar from the gui as long as its already been created.
                remove(progBarPanel);
            String[][] temp = fl.getGameMatrix();
            scrapMatrix = new String[fl.getMatrixSizeRow()][fl.getMatrixSizeColumn()];
            for (int i = 0; i < scrapMatrix.length; i++) {
                for (int j = 0; j < scrapMatrix[i].length; j++) {
                    scrapMatrix[i][j] = temp[i][j];
                }
            }

            timeCalc = new TimeCalculator();
            timeCalc.calcTimeforMaze(fl.dimondCount(), fl.getMatrixSizeRow(), fl.getMatrixSizeColumn());
            //^^^ Let time calculator know the parameters of the game ^^^
            timeLeft = timeCalc.getMinutes();
            ix = timeCalc.getSeconds();
            jx = 0;// reset the variable used for keeping time to zero since its a new level
            timely = new Timer(1000, updateCursorAction);
            timely.start();
            progBarPanel = new JPanel();// panel for progress bar
            
            // minutes returns a single digit, we have to  multiply it for Bar.
            progressBar = new JProgressBar(0, timeCalc.getMinutes() * 100);
            progressBar.setStringPainted(true);
            progBarPanel.add(progressBar);
            cp.add(progBarPanel, BorderLayout.NORTH);
            newPanel = new JPanel();
            newPanel.setLayout(new GridLayout(fl.getMatrixSizeRow(), fl.getMatrixSizeColumn()));
            labelMatrix = new JLabel[fl.getMatrixSizeRow()][fl.getMatrixSizeColumn()];
            newPanel.addKeyListener(new MyKeyHandler());
        }
        else 
            if (event == "updateLoad")// every time the player moves the gui must be updated.
            {
                scrapMatrix = theArc.getUpdatedMatrix();// get the new matrix to be displayed from the architect
                remove(newPanel);// remove the old game
                newPanel = new JPanel();
                newPanel.setLayout(new GridLayout(fl.getMatrixSizeRow(), fl.getMatrixSizeColumn()));
                newPanel.addKeyListener(new MyKeyHandler());
                newPanel.grabFocus();
            }

        for (int i = 0; i < labelMatrix.length; i++) {
            for (int j = 0; j < labelMatrix[i].length; j++) {
                labelMatrix[i][j] = mo = new mazeObject(scrapMatrix[i][j]);// add our maze images into the gui
            }
        }

        cp.add(newPanel);
        remove(shagLabel);
        System.gc();// force java to clean up memory use.
        pack();
        setVisible(true);
        newPanel.grabFocus();
    }
    
    // inner class for each maze object, aka wall, player etc
    public class mazeObject extends JLabel
    {
        private JLabel imageLabel;

        public mazeObject(String fileName) {
            fileName += ".png";
            JLabel fancyLabel;
            fancyLabel = new JLabel("", new ImageIcon(fileName), JLabel.LEFT);
            newPanel.add(fancyLabel);
        }
    }// end inner class

    public void nextLevelLoad() {
        levelNum += 1;
        // The TimeKeeper object keeps a running tab of the total time the player has used.(for high score)
        tk.TimeKeeper(timeLeft, ix);

        timely.stop();// dont count while we are loading the next level.
        theArc = new TheArchitect();// flush everything from TheArchitect so we dont get goffee results
        catFileName += 01;// the next file to be loaded (number)
        
        String fileName = "level" + catFileName + ".maz";
        System.gc();
        fl.loadFile(fileName);
        scrapMatrix = fl.getGameMatrix();// get the new matrix from the fileloader for the next level.
        theArc.setExit(fl.ExitXCord(), fl.ExitYCord());
        loadMatrixGui("newLoad");
    }

    Action updateCursorAction = new AbstractAction() {
        // this inner class generates an exeption if the player takes to long to finish a level
        public void actionPerformed(ActionEvent e) throws SlowAssPlayer 
        {
            ix -= 1;
            jx += 1;
            if (ix < 0) {
                ix = 60;
                timeLeft -= 1;
            }
            if (timeLeft == 0 && ix == 0) {
                timely.stop();
                JLabel yousuckLabel = new JLabel("", new ImageIcon("yousuck.jpg"), JLabel.LEFT);
                cp.add(yousuckLabel);
                remove(newPanel);
                remove(progBarPanel);
                pack();
                setVisible(true);
                timely.stop();
                catFileName -= 01;
                if (catFileName < 01)
                    throw new SlowAssPlayer("Slow ass took to long.");
                else
                    loadMatrixGui("newLoad");
            } 

            progressBar.setValue(jx);
            progressBar.setString(timeLeft + ":" + ix);
        }
    };// end class

    private class SlowAssPlayer extends RuntimeException {
        public SlowAssPlayer(String event) {
            // the game is over, here we must tell our high score method to recond the details.
            hs.addHighScore(playerName, tk.getMinutes(), tk.getSeconds(), levelNum);
            JFrame frame = new JFrame("Warning");
            // the entire  game has ended.
            JOptionPane.showMessageDialog(frame, 
            "You Stupid Ass, Did you eat to much for dinner?  Move Faster!");
        }
    }

    private HighScore hs;
    private int catFileName = 01;
    private Container cp;
    private FileLoader fl = new FileLoader();
    // create menu items
    private JMenuBar menuBar;
    private JMenu newMenu;
    private JMenuItem itemExit;
    private JMenuItem newGameItem;
    private JMenuItem openFileItem;
    private JMenuItem itemEnterName;
    private JMenuItem itemHighScore;
    private JMenuItem itemSaveScore;
    // end create menu items
    private JLabel shagLabel;
    private int ix;
    private int jx;
    private int timeLeft;
    private JPanel progBarPanel;
    private JLabel[][] labelMatrix;
    private TimeCalculator timeCalc;
    private JProgressBar progressBar;
    private mazeObject mo;
    private JPanel newPanel;// = new JPanel();
    private TheArchitect theArc = new TheArchitect();
    private String[][] scrapMatrix;
    private Timer timely;
    private TimeKeeper tk;
    private String playerName;
    private int levelNum = 1;
}// end class
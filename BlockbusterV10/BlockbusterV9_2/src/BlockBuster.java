import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class BlockBuster extends JFrame {
    //Board dimensions and cell size
    public static final int BOARD_ROWS = 8;
    public static final int BOARD_COLS = 8;
    public static final int CELL_SIZE = 50;

    //Main board panels with cells
    public static JPanel gridPanel;
    private Cell[][] cells;


    // Bottom panel that holds the draggable pieces
    public static JPanel bankPanel;

    //Score Display
    private int score = 0;

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    private JLabel scoreLabel = new JLabel("Score: " + score);


    public int getScoreLabel(){return score;}

    // Count of pieces placed in the current round
    private int placedCount = 0;

    //end game button
    public static JButton resetButton = new JButton("Reset");

    //Score screen button
     public static JButton highscorebutton = new JButton("");

     //settings button
    public static JButton settingsbutton = new JButton("Settings");

    public int finalScore = 0;
    public int getFinalScore() {
        return finalScore;
    }

    //For Highscore Screen, sets everything up
    String filePath = Paths.get(".").toAbsolutePath().toString().replace(".", "");  // Get folder path

    ScoreKeeper scoreKeeper = new ScoreKeeper(filePath + "HighScores.txt");
    private PriorityQueue<Integer> ScoreQueue;
    private int maxSize = 5;

    public static int score0;
    public void setScore0(int score0) {
        this.score0 = score0;
    }

    public static int score1;
    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public static int score2;
    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public static int score3;
    public void setScore3(int score3) {
        this.score3 = score3;
    }

    public static int score4;
    public void setScore4(int score4) {
        this.score4 = score4;
    }

    // Score Panel (Placed at Top)
    public static JPanel scorePanel = new JPanel();

    public BlockBuster() {
        //Gets Highscores and adds them to queues
        scoreKeeper.getScore();
        ScoreQueue = new PriorityQueue<>();
        ScoreQueue.add(scoreKeeper.getScore0());
        ScoreQueue.add(scoreKeeper.getScore1());
        ScoreQueue.add(scoreKeeper.getScore2());
        ScoreQueue.add(scoreKeeper.getScore3());
        ScoreQueue.add(scoreKeeper.getScore4());
        setScore0(scoreKeeper.getScore0());
        setScore1(scoreKeeper.getScore1());
        setScore2(scoreKeeper.getScore2());
        setScore3(scoreKeeper.getScore3());
        setScore4(scoreKeeper.getScore4());


        setTitle("BlockBuster");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(BOARD_COLS * CELL_SIZE, BOARD_ROWS * CELL_SIZE + 150);


        //highScore
        scorePanel.add(highscorebutton);
        add(scorePanel, BorderLayout.NORTH);
        highscorebutton.setText("Score: " + String.valueOf(score));
        highscorebutton.addActionListener(e -> {
            HighscoreScreen highscoreScreen = new HighscoreScreen();
//            gridPanel.setVisible(false);
            highscoreScreen.setColor();
        });
        // reset
        scorePanel.add(resetButton);
        resetButton.addActionListener(e -> {
            finalScore = score;
//            this.dispose();
            ScoreQueue.add(score);
            if (ScoreQueue.size() > maxSize) {
                ScoreQueue.poll();
            }
            List<Integer> sortedScores = new ArrayList<>(ScoreQueue);
            Collections.sort(sortedScores);
            Collections.reverse(sortedScores);
            System.out.println(sortedScores);
            setScore0(sortedScores.get(0));
            setScore1(sortedScores.get(1));
            setScore2(sortedScores.get(2));
            setScore3(sortedScores.get(3));
            setScore4(sortedScores.get(4));
            try{
                scoreKeeper.saveScores();
            } catch (IOException r) {
                throw new RuntimeException(r);
            }

            gridPanel.setBackground(Colors.selectedColor);
            gridPanel.repaint();
            bankPanel.setBackground(Colors.selectedColor);
            bankPanel.repaint();
            scorePanel.setBackground(Colors.selectedColor);
            scorePanel.repaint();

            for (Component component : gridPanel.getComponents()) {
                if (component instanceof Cell) {
                    ((Cell)component).clear();
                }
            }
            gridPanel.revalidate();
            gridPanel.repaint();
            score = 0;
            highscorebutton.setText("Score: " + String.valueOf(score));
            refreshBank();

        });

        //settings
        scorePanel.add(settingsbutton);
        settingsbutton.setText("Settings");
        settingsbutton.addActionListener(e -> {
            SettingsScreen settingsScreen = new SettingsScreen();
        });



        //Grid Panel (Center)
        gridPanel = new JPanel(new GridLayout(BOARD_ROWS, BOARD_COLS));
        cells = new Cell[BOARD_ROWS][BOARD_COLS];
        for (int ii = 0; ii < BOARD_ROWS; ii++) {
            for (int jj = 0; jj < BOARD_COLS; jj++) {
                cells[ii][jj] = new Cell();
                cells[ii][jj].setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                gridPanel.add(cells[ii][jj]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        // Bank Panel (Bottom)
        bankPanel = new JPanel(null); // using absolute positioning
        bankPanel.setPreferredSize(new Dimension(BOARD_COLS * CELL_SIZE, 100));
        bankPanel.setBackground(Color.WHITE);
        add(bankPanel, BorderLayout.SOUTH);

        // Generate the first three random pieces in the bank
        refreshBank();

        setLocationRelativeTo(null);
        setVisible(true);
    }





    // Removes any existing pieces in the bank and adds three new random pieces.
    public void refreshBank() {
        bankPanel.removeAll();
        placedCount = 0;
        // Define fixed slots for the three pieces.
        int[] positionsX = {50, 200, 350};
        int posY = 10;
        for (int kk = 0; kk < 3; kk++) {
            BlockShape shape = BlockShape.getRandomShape();
            DraggableBlock block = new DraggableBlock(this, shape);
            Rectangle slot = new Rectangle(positionsX[kk], posY, 100, 50);
            block.setInitialBounds(slot);
            block.setBounds(slot);
            bankPanel.add(block);
        }
        bankPanel.revalidate();
        bankPanel.repaint();
    }

    // Checks if the given piece can be placed on the board at the specified grid cell. (Got help from Chat)
    public boolean canPlaceBlock(BlockShape shape, int startRow, int startCol) {
        boolean[][] shapeArr = shape.getShape();
        for (int ll = 0; ll < shapeArr.length; ll++) {
            for (int pp = 0; pp < shapeArr[ll].length; pp++) {
                if (shapeArr[ll][pp]) {
                    int rr = startRow + ll;
                    int cc = startCol + pp;
                    if (rr < 0 || rr >= BOARD_ROWS || cc < 0 || cc >= BOARD_COLS) {
                        return false;
                    }
                    if (cells[rr][cc].isOccupied()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Places the piece on the board at the given starting cell; updates score. (About the same structure as canPlaceBlock)
    public void placeBlock(BlockShape shape, int startRow, int startCol) {
        boolean[][] shapeArr = shape.getShape();
        int cellCount = 0;
        for (int ii = 0; ii < shapeArr.length; ii++) {
            for (int jj = 0; jj < shapeArr[ii].length; jj++) {
                if (shapeArr[ii][jj]) {
                    int rr = startRow + ii;
                    int cc = startCol + jj;
                    if (rr >= 0 && rr < BOARD_ROWS && cc >= 0 && cc < BOARD_COLS) {
                        cells[rr][cc].setOccupied(true, "Piece", shape.getColor());
                        cellCount++;
                    }
                }
            }
        }
        // Increase score based on cells covered, updates displayed scores and score queue
        score += cellCount * 10;
        highscorebutton.setText("Score: " + String.valueOf(score));






        // Check if any full rows or columns need clearing.
        checkAndClear();
    }

    // Checks and clears any full row or full column; awards bonus points.
    private void checkAndClear() {
        boolean cleared = false;
        // Check rows:
        for (int rr = 0; rr < BOARD_ROWS; rr++) {
            boolean full = true;
            for (int cc = 0; cc < BOARD_COLS; cc++) {
                if (!cells[rr][cc].isOccupied()) {
                    full = false;
                    break;
                }
            }
            if (full) {
                for (int cc = 0; cc < BOARD_COLS; cc++) {
                    cells[rr][cc].clear();
                }
                cleared = true;
            }
        }
        // Check columns:
        for (int cc = 0; cc < BOARD_COLS; cc++) {
            boolean full = true;
            for (int rr = 0; rr < BOARD_ROWS; rr++) {
                if (!cells[rr][cc].isOccupied()) {
                    full = false;
                    break;
                }
            }
            if (full) {
                for (int rr = 0; rr < BOARD_ROWS; rr++) {
                    cells[rr][cc].clear();
                }
                cleared = true;
            }
        }
        if (cleared) {
            score += 50; // bonus points for clearing a line
            highscorebutton.setText("Score: " + score);
        }
    }

    // Called by a draggable block after it has been successfully placed.
    public void piecePlaced() {
        placedCount++;
        if (placedCount >= 3) {
            // Once all three pieces are placed, refresh the bank after a short delay (about 1 second).
            Timer timer = new Timer(1000, e -> {
                refreshBank();
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    public JPanel getGridPanel() {
        return gridPanel;
    }

    public JPanel getBankPanel() {
        return bankPanel;
    }

    public JFrame getFrame() {
        return this;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HomeScreen();
            }
        }
        );
        //Soundtrack - Possible that this isn't the best placement
        /*try {
            new Soundtrack();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
    }

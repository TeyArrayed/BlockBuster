import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public  class HighscoreScreen extends JFrame {
    public static int holdScore = 100;

    static JFrame frame;
    public HighscoreScreen() {
//        ScoreKeeper scoreKeeper = new ScoreKeeper("HighScores.txt");
//        scoreKeeper.getScore();



        JLabel WelcomeLabel = new JLabel("Highscores");
        WelcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        WelcomeLabel.setFont(new Font("Serif", Font.PLAIN, 20));

        JLabel Score1Label = new JLabel("Score 1: " + BlockBuster.score0);
        Score1Label.setFont(new Font("Serif", Font.PLAIN, 18));
        JLabel Score2Label = new JLabel("Score 2: " + BlockBuster.score1);
        Score2Label.setFont(new Font("Serif", Font.PLAIN, 18));
        JLabel Score3Label = new JLabel("Score 3: " + BlockBuster.score2);
        Score3Label.setFont(new Font("Serif", Font.PLAIN, 18));
        JLabel Score4Label = new JLabel("Score 4: " + BlockBuster.score3);
        Score4Label.setFont(new Font("Serif", Font.PLAIN, 18));
        JLabel Score5Label = new JLabel("Score 5: " + BlockBuster.score4) ;
        Score5Label.setFont(new Font("Serif", Font.PLAIN, 18));



        Container scroe = getContentPane();
        scroe.setLayout(new BoxLayout(scroe, BoxLayout.Y_AXIS));
        scroe.add(Score1Label);
        scroe.add(Score2Label);
        scroe.add(Score3Label);
        scroe.add(Score4Label);
        scroe.add(Score5Label);

        frame = new JFrame("Highscores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 550);

        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        WelcomeLabel.setBackground(Colors.selectedColor);
        scroe.setBackground(Colors.selectedColor);



        // Create and add your components.
        JButton backToGame = new JButton("Back to game");
        backToGame.setBackground(Colors.selectedColor);

        // Add an ActionListener to go back to game
        backToGame.addActionListener(e -> {
            System.out.println("Back to game button clicked!");
            frame.dispose();
        });

        // Add the button to the frame in your desired location.
        frame.add(backToGame, BorderLayout.SOUTH);
        frame.add(WelcomeLabel, BorderLayout.NORTH);
        frame.add(scroe, BorderLayout.CENTER);




        // Make the frame visible after all components are added.
        frame.setVisible(true);

    }


    public static void setColor(){
        frame.setBackground(Colors.selectedColor);
        frame.repaint();
    }

}

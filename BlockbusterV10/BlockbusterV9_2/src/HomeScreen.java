import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

public class HomeScreen {
    public HomeScreen() {
        // 1) Frame setup
        JFrame frame = new JFrame("Block Buster");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 2) Black content pane
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(Color.BLACK);
        frame.setContentPane(content);

        // 3) Load your splash image
        String filePath = Paths.get(".").toAbsolutePath().toString().replace(".", "");  // Get folder path

        ImageIcon icon = new ImageIcon(filePath + "start.png");


        // 4) Scale it to 380×380 before adding
        Image img = icon.getImage()
                .getScaledInstance(330, 400, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);

        // 5) Wrap the scaled icon in a JLabel
        JLabel imageLabel = new JLabel(icon, SwingConstants.CENTER);
        imageLabel.setOpaque(false);   // keep it transparent so the PNG shows fully
        content.add(imageLabel, BorderLayout.CENTER);

        // 6) Start button
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            new BlockBuster();
            frame.dispose();
        });
        JPanel south = new JPanel();
        south.setBackground(Color.BLACK);
        south.add(startButton);
        content.add(south, BorderLayout.SOUTH);

        // 7) Pack to fit the scaled image + button, then show
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HomeScreen::new);
    }
}

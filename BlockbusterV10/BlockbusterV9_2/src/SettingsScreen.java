import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.util.Arrays;

public  class SettingsScreen {
    JFrame frame;
    public SettingsScreen() {
        frame = new JFrame("Settings Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 550);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());



        // Create and add your components.
        JButton backToGame = new JButton("Back to game");


        // Add an ActionListener to go back to game
        backToGame.addActionListener(e -> {
            System.out.println("Back to game button clicked!");
            BlockBuster.gridPanel.setVisible(true);
            frame.dispose();
        });

        //Creating a place for the settings button to rest
        JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("Settings");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
        buttonPanel.add(titleLabel);
        JColorChooser colorChooser = new JColorChooser();
        buttonPanel.add(colorChooser);
        Color selectedColor = colorChooser.getColor();
        colorChooser.setColor(selectedColor);
        colorChooser.getSelectionModel().addChangeListener(e -> {
            Color newColor = colorChooser.getColor();
            frame.setBackground(newColor);
            buttonPanel.setBackground(newColor);
            Colors.selectedColor = newColor;
            BlockBuster.gridPanel.setBackground(newColor);
            BlockBuster.bankPanel.setBackground(newColor);
            BlockBuster.scorePanel.setBackground(newColor);
        });


        JButton Dark = new JButton("Dark");
        buttonPanel.add(Dark);
        Dark.addActionListener(e -> {
            Colors.BackgroundColor = Color.BLACK;
            Colors.BorderColor = Color.WHITE;
            for (Component comp : BlockBuster.gridPanel.getComponents()) {
                if (comp instanceof JComponent) {
                    ((JComponent) comp).setBorder(BorderFactory.createLineBorder(Colors.BorderColor));
                }
            }
            BlockBuster.gridPanel.revalidate();
            BlockBuster.gridPanel.repaint();
            System.out.println("Dark button clicked!");
            System.out.println("Background Color: " + Colors.BackgroundColor);
            System.out.println("Border Color: " + Colors.BorderColor);
        });

        JButton Light = new JButton("Light");
        buttonPanel.add(Light);
        Light.addActionListener(e -> {
            Colors.BackgroundColor = Color.WHITE;
            Colors.BorderColor = Color.BLACK;
            for (Component comp : BlockBuster.gridPanel.getComponents()) {
                if (comp instanceof JComponent) {
                    ((JComponent) comp).setBorder(BorderFactory.createLineBorder(Colors.BorderColor));
                }
            }
            BlockBuster.gridPanel.revalidate();
            BlockBuster.gridPanel.repaint();
            System.out.println("Light button clicked!");
            System.out.println("Background Color: " + Colors.BackgroundColor);
            System.out.println("Border Color: " + Colors.BorderColor);
        });

        JLabel resetgameWarningLabel = new JLabel("Reset Needed after changing mode!");
        buttonPanel.add(resetgameWarningLabel);





        frame.add(backToGame, BorderLayout.SOUTH);
        frame.setBackground(Colors.selectedColor);
        buttonPanel.setBackground(Colors.selectedColor);

        //Make the frame visible after all components are added.
        frame.setVisible(true);
    }

}

import javax.swing.*;
import java.awt.*;

public  class EndScreen {
    JFrame frame;
    public EndScreen() {
        frame = new JFrame("End Screen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 550);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());



         //Create and add your components.
        JButton restartButton = new JButton("restart Game");
        JButton homeButton = new JButton("Home");
        JButton settingsButton = new JButton("Settings");

         //Add an ActionListener
        restartButton.addActionListener(e -> {
            // Code to launch the game goes here.
            BlockBuster blockBuster = new BlockBuster();
            frame.dispose();
        });

        homeButton.addActionListener(e -> {
            HomeScreen homeScreen = new HomeScreen();
            frame.dispose();
        });

//        settingsButton.addActionListener(e -> {
//            SettingsScreen settingsScreen = new SettingsScreen();
//            frame.dispose();
//        });

         //Add the button to the frame in your desired location.
        frame.add(restartButton, BorderLayout.CENTER);
        frame.add(homeButton, BorderLayout.NORTH);
//        frame.add(settingsButton, BorderLayout.CENTER);

         //Make the frame visible after all components are added.
        frame.setVisible(true);
    }

}

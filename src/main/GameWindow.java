package main;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameWindow extends JPanel {

    // ArrayList to hold game data: y value, x value, and time in seconds
    private static ArrayList<Integer> gameData = new ArrayList<>();
    private static JLabel timeLabel;

    // Timer to update time every second
    private Timer timer;

    public GameWindow() {
        // Initialize the game data: y value, x value, and time in seconds
        gameData.add(0);  // Slot 1: y value (initially 0)
        gameData.add(0);  // Slot 2: x value (initially 0)
        gameData.add(0);  // Slot 3: time in seconds (initially 0)

        // Set up the JPanel layout and appearance
        this.setLayout(null);  // Use absolute positioning
        this.setBackground(Color.BLACK);  // Set background to black for the game
        this.setPreferredSize(new Dimension(800, 600));  // Set panel size

        // Create and set up the JLabel to display time
        timeLabel = new JLabel("Time: 0 seconds", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 30));  // Set font size
        timeLabel.setBounds(250, 50, 300, 50);  // Positioning the label on the panel
        timeLabel.setForeground(Color.WHITE);  // Set text color to white

        // Add the label to the panel
        this.add(timeLabel);

        // Start the timer to increase the time every second
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                increaseTimeByOne();  // Increase time every second
            }
        }, 0, 1000);  // 0 delay, 1000ms (1 second)
    }

    // Function to increase the time (3rd slot) every second and update the label
    private void increaseTimeByOne() {
        // Increase the time (3rd slot in the list)
        gameData.set(2, gameData.get(2) + 1);

        // Update the label text with the current time
        timeLabel.setText("Time: " + gameData.get(2) + " seconds");

        // Repaint the panel to update the display
        repaint();
    }

    // You can add the game loop or rendering logic here
    public void startGameThread() {
        // Start the game thread or game loop here
        // This would contain your game logic, like movement, rendering, etc.
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Your rendering code for the game goes here
        // For example, drawing the game world, characters, etc.
    }
}

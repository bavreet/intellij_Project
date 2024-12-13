package main;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ScoreListWithGUI {
    private static ArrayList<Integer> gameData = new ArrayList<>();
    private static JLabel timeLabel;  // Label to display time in the window

    public static void main(String[] args) {
        // Initialize the list with [y, x, timeInSeconds] all starting at 0
        gameData.add(0);   // Slot 1: y value (initially 0)
        gameData.add(0);   // Slot 2: x value (initially 0)
        gameData.add(0);   // Slot 3: seconds counter (initially 0)

        // Create the main window
        JFrame frame = new JFrame("Game Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);  // Set window size
        frame.setLayout(null);

        // Create a label to display the time
        timeLabel = new JLabel("Time: 0 seconds", SwingConstants.CENTER);
        timeLabel.setBounds(100, 50, 200, 50);  // Position and size of the label
        timeLabel.setFont(timeLabel.getFont().deriveFont(30.0f));  // Set font size
        frame.add(timeLabel);

        // Make the window visible
        frame.setVisible(true);

        // Start the Timer to increase the third slot (timeInSeconds) every second
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                increaseTimeByOne();  // Increase time every second and update the label
            }
        }, 0, 1000);  // 0 delay, 1000ms (1 second)
    }

    // Function to increase the time (3rd slot) every second and update the label
    private static void increaseTimeByOne() {
        // Increase the time (3rd slot in the list)
        gameData.set(2, gameData.get(2) + 1);

        // Update the label text with the current time
        timeLabel.setText("Time: " + gameData.get(2) + " seconds");
    }
}
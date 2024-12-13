package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

import entity.Player;
import tile.tileManager;

public class gamePanel extends JPanel implements Runnable {
    // Screen settings
    final int originalTitleSize = 40; // tile
    final int backgroundTileSize = 80;
    final int scale = 3; // scale factor

    public final int tileSize = originalTitleSize * scale; // scales
    public final int BackgroundTileSIze = backgroundTileSize * scale;
    final int maxScreenCol = 8; // 8 tiles horizontally
    final int maxScreenRow = 4; // 4 tiles vertically
    final int screenWitdh = tileSize * maxScreenCol; // 48*16 = 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 48*12 = 576 pixels
    // FPS
    int FPS = 60;

    tileManager tileM = new tileManager(this);
    keyHandler keyH = new keyHandler();
    public collisionChecker cChecker = new collisionChecker(this);
    Thread gameThread;
    Player player = new Player(this, keyH);

    // Timer-related variables
    private static ArrayList<Integer> gameData = new ArrayList<>();
    private Timer timer;

    public gamePanel() {
        this.setPreferredSize(new Dimension(screenWitdh, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // When true, program can receive keyboard input when the user interacts with it.

        // Initialize game data: y value, x value, and time in seconds
        gameData.add(0);  // Slot 1: y value (initially 0)
        gameData.add(0);  // Slot 2: x value (initially 0)
        gameData.add(0);  // Slot 3: time in seconds (initially 0)

        // Start the timer to increase the time every second
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                increaseTimeByOne();
            }
        }, 0, 1000);  // 0 delay, 1000ms (1 second)
    }

    public void startGameThread() {
        gameThread = new Thread(this); // passing GamePanel Class to this thread's constructor
        gameThread.start(); // automatically call the 'run' method
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 1 billion nanoseconds or 1 second / 60 FPS.
        double nextDrawTime = System.nanoTime() + drawInterval; // draw the screen every 0.1666 secs.

        while (gameThread != null) { // As long as this gameThread exists, it repeats the process.
            // 1. Update information such as character position.
            update();
            // 2. Draw the screen with the updated information.
            repaint(); // calls paintComponent method

            try {
                double remainingTime = nextDrawTime - System.nanoTime(); // how much time remaining until next draw time
                remainingTime = remainingTime / 1000000; // convert nanosecond to millisecond
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval; // guarantees the game loop keeps up with the specified frame rate.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    // Function to increase the time (3rd slot) every second and update the label
    private void increaseTimeByOne() {
        // Increase the time (3rd slot in the list)
        gameData.set(2, gameData.get(2) + 1);
        repaint(); // Update the display
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // super means parent class of this class
        Graphics2D g2 = (Graphics2D) g; // Graphics2D extends the Graphics class to provide more functions.
        tileM.draw(g2);
        player.draw(g2);

        // Draw the time in the top-left corner
        g2.setColor(Color.WHITE); // Set text color to white
        g2.setFont(new Font("Arial", Font.BOLD, 30)); // Set font size
        g2.drawString("Time: " + gameData.get(2) + " seconds", 10, 30); // Draw the time text

        g2.dispose(); // Release resources to avoid memory leaks
    }
}   
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

    final int originalTitleSize = 40;
    final int backgroundTileSize = 80;
    final int scale = 3;

    public final int tileSize = originalTitleSize * scale;
    public final int BackgroundTileSIze = backgroundTileSize * scale;
    final int maxScreenCol = 8;
    final int maxScreenRow = 4;
    final int screenWitdh = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int FPS = 60;

    tileManager tileM = new tileManager(this);
    keyHandler keyH = new keyHandler();

    Thread gameThread;
    Player player = new Player(this, keyH);


    private static ArrayList<Integer> gameData = new ArrayList<>();
    private Timer timer;

    public gamePanel() {
        this.setPreferredSize(new Dimension(screenWitdh, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // When true, program can receive keyboard input when the user interacts with it.


        gameData.add(0);
        gameData.add(0);
        gameData.add(0);


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

        while (gameThread != null) {

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }


    private void increaseTimeByOne() {
        gameData.set(2, gameData.get(2) + 1);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);


        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.drawString("Time: " + gameData.get(2) + " seconds", 10, 30);

        g2.dispose(); // Release resources to avoid memory leaks
    }
}
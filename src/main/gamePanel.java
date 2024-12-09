package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.tileManager;

public class gamePanel extends JPanel implements Runnable{
    //screen settings
    final int originalTitleSize = 40; //  tile
    final int backgroundTileSize = 80;
    final int scale = 3; //

    public final int tileSize = originalTitleSize * scale; // scales
    public final int BackgroundTileSIze = backgroundTileSize *scale;
    final int maxScreenCol = 8; // 8 tiles horizontally
    final int maxScreenRow = 4; // 4 tiles vertically
    final int screenWitdh = tileSize * maxScreenCol; // 48*16 = 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 48*12 = 576 pixels
    //FPS
    int FPS = 60;

    tileManager tileM = new tileManager(this);
    keyHandler keyH =  new keyHandler();
    public collisionChecker cChecker = new collisionChecker(this);
    Thread gameThread;
    Player player = new Player(this,keyH);




    public  gamePanel(){

        this.setPreferredSize(new Dimension(screenWitdh, screenHeight));
        this.setBackground(Color.BLACK);this.setDoubleBuffered(true);
        this.setDoubleBuffered(true);// if set to true, all the drawing from this component will be done in an offscreen painting buffer
        // can improve rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); // when true, program can receive keyboard input when the user interacts with it.

    }

    public void startGameThread(){ // allows multiple things to run at concurrently

        gameThread = new Thread(this); // passing GamePanel Class to this thread's constructor
        gameThread.start(); // automatically call the 'run' method
    }


    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; //1 billion nanosecond or 1 second/60 FPS.
        double nextDrawTime = System.nanoTime() + drawInterval; // draw the screen every 0.1666 secs.

        while (gameThread != null){ //as long as this gameThread exists, it repeats the process that is written inside of these brackets.


            //1. Update information such as character position.
            update();
            //2. Draw the screen with the updated information.
            repaint(); // calls paintComponent method

            try {
                double remaningTime = nextDrawTime - System.nanoTime(); // how much time remaning until next draw time, thread will SLEEP during the remaning time
                remaningTime = remaningTime/1000000; //convert nanosecond to millisecond becasuse 'sleep' does the math in millisecond.

                if(remaningTime < 0){
                    remaningTime = 0;
                }

                Thread.sleep((long) remaningTime);

                nextDrawTime +=  drawInterval; //  guarantees that the game loop keeps up with the specified frame rate.

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }


    public void update(){

        player.update();

    }


    public void paintComponent(Graphics g){

        super.paintComponent(g); //super means parent class of this class

        Graphics2D g2 = (Graphics2D)g; // Graphics2D extends the Graphics class to provide more functions.
        tileM.draw(g2);
        player.draw(g2);

        g2.dispose(); // Release resources to avoid memory leaks or unnecessary resource usage.

    }

}


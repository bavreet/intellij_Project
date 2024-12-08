package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.gamePanel;
import main.keyHandler;

public class Player extends Entity{

    gamePanel gp;
    keyHandler keyH;

    public Player(gamePanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 2;
        direction = "Idle";
    }

    public void getPlayerImage(){
        try {
            Idle = ImageIO.read(getClass().getResourceAsStream("/resources_Idle.png"));

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void update(){
        if(keyH.leftPressed == true){
            direction = "Idle";
            x -= speed;
        }
        if(keyH.rightPressed == true){
            direction = "Idle";
            x += speed;

        }

        if(keyH.shiftPressed == true){
            direction = "Idle";
            speed = 5;
        }

    }

    public void draw(Graphics2D g2){

        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, gp.tileSize, gp.tileSize);



    }

}


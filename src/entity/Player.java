package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
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

    public void getPlayerImage() {
        try {
            Idle = ImageIO.read(getClass().getResourceAsStream("/player/Idle.png"));
            run1 = ImageIO.read(getClass().getResourceAsStream("/player/run1.png"));
            run2 = ImageIO.read(getClass().getResourceAsStream("/player/run2.png"));
            run3 = ImageIO.read(getClass().getResourceAsStream("/player/run3.png"));
            run4 = ImageIO.read(getClass().getResourceAsStream("/player/run4.png"));
            run5 = ImageIO.read(getClass().getResourceAsStream("/player/run5.png"));
            run6 = ImageIO.read(getClass().getResourceAsStream("/player/run6.png"));
            run7 = ImageIO.read(getClass().getResourceAsStream("/player/run7.png"));
            run8 = ImageIO.read(getClass().getResourceAsStream("/player/run8.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update () {
        if (keyH.leftPressed) {
            direction = "run1";
            x -= speed;
        }
        if (keyH.rightPressed) {
            direction = "run1";
            x += speed;

        }
        if (keyH.shiftPressed) {
            direction = "run1";
            speed = 4;
        }

        if (keyH.shiftPressed == false) {
            speed = 2;
        }
        if(keyH.rightPressed == false && keyH.leftPressed == false){
            direction = "Idle";
        }

        spriteCounter++;
        if(spriteCounter >12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 5;
            } else if (spriteNum == 5) {
                spriteNum = 6;
            }else if (spriteNum == 6) {
                spriteNum = 7;
            }else if (spriteNum == 7) {
                spriteNum = 8;
            }else if (spriteNum == 8) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

        public void draw (Graphics2D g2){

//            g2.setColor(Color.WHITE);
//            g2.fillRect(x, y, gp.tileSize, gp.tileSize);
            BufferedImage image = null;
            switch(direction){
                case "run1":
                    if(spriteNum == 1){
                        image = run1;
                    }
                    if(spriteNum == 2){
                        image = run2 ;
                    }
                    if(spriteNum == 3){
                        image = run3;
                    }
                    if(spriteNum == 4){
                        image = run4;
                    }
                    if(spriteNum == 5){
                        image = run5;
                    }
                    if(spriteNum == 6) {
                        image = run6;
                    }
                    if(spriteNum == 7){
                        image = run8;
                    }
                    if(spriteNum == 8){
                        image = run8;
                    }
                    break;

                case "Idle":
                    image = Idle;

            }
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        }
    }


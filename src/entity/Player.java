package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.gamePanel;
import main.keyHandler;

public class Player extends Entity{
    public long jumpingTime = 200;
    gamePanel gp;
    keyHandler keyH;

    public Player(gamePanel gp, keyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 32;
        solidArea.width = 15- gp.tileSize;
        solidArea.height = 20 - gp.tileSize;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 270;
        y = 270;
        speed = 2;
        direction = "run1";
        isAttacking = false;
        attackCounter = 0;
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

            jump1 = ImageIO.read(getClass().getResourceAsStream("/player/jump1.png"));
            jump2 = ImageIO.read(getClass().getResourceAsStream("/player/jump2.png"));
            jump3 = ImageIO.read(getClass().getResourceAsStream("/player/jump3.png"));
            jump4 = ImageIO.read(getClass().getResourceAsStream("/player/jump4.png"));
            jump5 = ImageIO.read(getClass().getResourceAsStream("/player/jump5.png"));
            jump6 = ImageIO.read(getClass().getResourceAsStream("/player/jump6.png"));
            jump7 = ImageIO.read(getClass().getResourceAsStream("/player/jump7.png"));
            jump8 = ImageIO.read(getClass().getResourceAsStream("/player/jump8.png"));
            jump9 = ImageIO.read(getClass().getResourceAsStream("/player/jump9.png"));
            jump10 = ImageIO.read(getClass().getResourceAsStream("/player/jump10.png"));
            jump11 = ImageIO.read(getClass().getResourceAsStream("/player/jump11.png"));
            jump12 = ImageIO.read(getClass().getResourceAsStream("/player/jump12.png"));
            jump13 = ImageIO.read(getClass().getResourceAsStream("/player/jump13.png"));
            jump14 = ImageIO.read(getClass().getResourceAsStream("/player/jump14.png"));
            jump15 = ImageIO.read(getClass().getResourceAsStream("/player/jump15.png"));

            attack1 = ImageIO.read(getClass().getResourceAsStream("/player/attack1.png"));
            attack2 = ImageIO.read(getClass().getResourceAsStream("/player/attack2.png"));
            attack3 = ImageIO.read(getClass().getResourceAsStream("/player/attack3.png"));
            attack4 = ImageIO.read(getClass().getResourceAsStream("/player/attack4.png"));
            attack5 = ImageIO.read(getClass().getResourceAsStream("/player/attack5.png"));
            attack6 = ImageIO.read(getClass().getResourceAsStream("/player/attack6.png"));
            attack7 = ImageIO.read(getClass().getResourceAsStream("/player/attack7.png"));
            attack8 = ImageIO.read(getClass().getResourceAsStream("/player/attack8.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update () {

        if(keyH.rPressed){
            if (!isAttacking) {
                direction = "Attack";
                attackCounter = 0;
                attackNum = 1;
                isAttacking = true;
                System.out.println("R Clicked");

            }
        }
        if (isAttacking) {
            attackCounter++;
            if (attackCounter > 12) {
                attackNum++;
                if (attackNum > 8) {
                    isAttacking = false;
                    direction = "Idle";
                }
                attackCounter = 0;
            }
        }

        if(x <= -40 && keyH.leftPressed){
            x=-40;
        }
        if(x >= 870 && keyH.rightPressed){
            x=870;
        }
        if(y>= 270 && !keyH.spacePressed){
            y=270;
            isJumping = false;
            jumpCounter = 0;

        }
        if(keyH.spacePressed && y == 270) {
            direction = "jump";
            isJumping = true;
            new Thread(new JumpThread(this)).start();

        }
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
        }else{
            speed = 2;
        }

        if(!keyH.rightPressed && !keyH.leftPressed && !keyH.spacePressed){
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
        attackCounter++;
        if(attackCounter >12) {
            if (attackNum == 1) {
                attackNum = 2;
            } else if (attackNum == 2) {
                attackNum = 3;
            } else if (attackNum == 3) {
                attackNum = 4;
            } else if (attackNum == 4) {
                attackNum = 5;
            } else if (attackNum == 5) {
                attackNum = 6;
            } else if (attackNum == 6) {
                attackNum = 7;
            } else if (attackNum == 7) {
                attackNum = 8;
            } else if (attackNum == 8) {
                direction = "Idle";
            }
            attackCounter = 0;
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
            case "Attack":
                System.out.println("Attack frame: " + attackNum);
                if (attackNum == 1) {
                    System.out.println("Attack triggered");
                    image = attack1;
                } else if (attackNum == 2) {
                    image = attack2;
                } else if (attackNum == 3) {
                    image = attack3;
                } else if (attackNum == 4) {
                    image = attack4;
                } else if (attackNum == 5) {
                    image = attack5;
                } else if (attackNum == 6) {
                    image = attack6;
                } else if (attackNum == 7) {
                    image = attack7;
                } else if (attackNum == 8) {
                    image = attack8;
                }
                break;

            case "Idle":
                image = Idle;

        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }
}
class JumpThread implements Runnable {
    private Player player;

    public JumpThread(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        int jumpHeight = 40; // The maximum height the player will reach
        int fallHeight = 20; // The maximum fall height after the jump

        // Perform the jump action
        for (int i = 0; i < jumpHeight; i++) {
            if (!player.isJumping) break;
            player.y -= 4;  // Move the player up
            try {
                Thread.sleep(10);  // Delay for jump animation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Begin the fall after reaching the peak
        for (int i = 0; i < fallHeight; i++) {
            if (!player.isJumping) break;
            player.y += 4;  // Move the player down
            try {
                Thread.sleep(10);  // Delay for fall animation
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Ensure the player lands on the ground
        player.y = 270;
        player.isJumping = false;  // Reset jump state
    }
}
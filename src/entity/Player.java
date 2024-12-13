package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.gamePanel;
import main.keyHandler;

public class Player extends Entity {
    gamePanel gp;
    keyHandler keyH;
    public long jumpingTime = 200;

    public Player(gamePanel gp, keyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 32;
        solidArea.width = 15 - gp.tileSize;
        solidArea.height = 20 - gp.tileSize;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 270;
        y = 270;
        speed = 2;
        direction = "run1";
        isAttacking = false;
        attackCounter = 0;
        attackNum = 1;
        isJumping = false;
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

    public void update() {
        if (keyH.rPressed && !isAttacking) {
            isAttacking = true;
            attackCounter = 0;
            attackNum = 1;
            direction = "Attack";
            System.out.println("Attack started");
        }

        if (isAttacking) {
            attackCounter++;
            if (attackCounter > 12) {
                attackNum++;
                if (attackNum > 8) {
                    isAttacking = false;
                    direction = "Idle"; // Go back to idle after attack
                }
                attackCounter = 0;
            }
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
            speed = 4;
        } else {
            speed = 2;
        }

        if (!keyH.rightPressed && !keyH.leftPressed && !keyH.spacePressed && !keyH.rPressed) {
            direction = "Idle"; // If no keys are pressed, set idle state
        }

        if (y >= 270 && !keyH.spacePressed) {
            y = 270;
            isJumping = false;
            jumpCounter = 0;
        }

        if (keyH.spacePressed && y == 270 && !isJumping) {
            direction = "jump";
            isJumping = true;
            new Thread(new JumpThread(this)).start();
        }

        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 8) {
                spriteNum = 1;
            } else {
                spriteNum++;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "run1":
                if (spriteNum == 1) {
                    image = run1;
                } else if (spriteNum == 2) {
                    image = run2;
                } else if (spriteNum == 3) {
                    image = run3;
                } else if (spriteNum == 4) {
                    image = run4;
                } else if (spriteNum == 5) {
                    image = run5;
                } else if (spriteNum == 6) {
                    image = run6;
                } else if (spriteNum == 7) {
                    image = run7;
                } else if (spriteNum == 8) {
                    image = run8;
                }
                break;

            case "Attack":
                if (attackNum == 1) {
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
                break;

            case "jump":
                if (spriteNum == 1) {
                    image = jump1;
                } else if (spriteNum == 2) {
                    image = jump2;
                } else if (spriteNum == 3) {
                    image = jump3;
                } else if (spriteNum == 4) {
                    image = jump4;
                } else if (spriteNum == 5) {
                    image = jump5;
                } else if (spriteNum == 6) {
                    image = jump6;
                } else if (spriteNum == 7) {
                    image = jump7;
                } else if (spriteNum == 8) {
                    image = jump8;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    class JumpThread implements Runnable {
        private Player player;

        public JumpThread(Player player) {
            this.player = player;
        }

        @Override
        public void run() {
            int jumpHeight = 50;
            int fallHeight = 30;

            // Jump up
            for (int i = 0; i < jumpHeight; i++) {
                if (!player.isJumping) break;
                player.y -= 2;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Fall down
            for (int i = 0; i < fallHeight; i++) {
                if (!player.isJumping) break;
                player.y += 2;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            player.y = 270;
            player.isJumping = false;
        }
    }
}
package entity;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage Idle, run1, run2, run3, run4, run5, run6, run7, run8;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public int gravity = 1;
    public int jumpSpeed = -20;
    public boolean isJumping =false;
    boolean isFalling = false;
    boolean isGrounded = true;


}

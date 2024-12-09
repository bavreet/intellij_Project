package entity;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage Idle, run1, run2, run3, run4, run5, run6, run7, run8, jump1, jump2, jump3, jump4, jump5, jump6, jump7, jump8, jump9, jump10, jump11, jump12, jump13, jump14, jump15, attack1, attack2, attack3, attack4, attack5, attack6, attack7, attack8;
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
    public int attackCounter = 0;
    public int attackNum =1;
    public boolean isAttacking = false;
    public int jumpCounter = 0;

}

package entity;
import java.awt.image.BufferedImage;
public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage Idle, run1, run2, run3, run4, run5, run6, run7, run8;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}

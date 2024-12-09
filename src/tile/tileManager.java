package tile;

import main.gamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class tileManager {

    gamePanel gp;
    Tile[] tile;

    public tileManager(gamePanel gp){

        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
    }

    public void getTileImage(){

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Background/grass.png"));
//            tile[1].collision = true;
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Background/dirt.png"));


        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void draw(Graphics2D g2){

    g2.drawImage(tile[0].image, -10, 370, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[0].image, 110, 370, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[0].image, 230, 370, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[0].image, 350, 370, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[0].image, 470, 370, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[0].image, 590, 370, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[0].image, 710, 370, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[0].image, 830, 370, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[0].image, 950, 370, gp.tileSize, gp.tileSize, null);


    }

}

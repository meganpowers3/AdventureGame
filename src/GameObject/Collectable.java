/**
 *
 * Collectable.java
 * Written by: Megan (Em) Powers
 *
 * Represents visible powerups on the map.
 *
 */

package GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

// b is intact unpushable wall, g is cracked unpushable wall, h is horizontal pushable, v is vertical pushable, e is door
// u is breakable, h is gun, n is scarab, papyrus is +1 life (book of dead)

public class Collectable extends PlayerObject {
    public boolean gun= false;
    public boolean scarab = false;
    public boolean ankh = false;
    public boolean statue = false;
    public boolean papyrus = false;
    public boolean sword = false;
    public boolean potion = false;
    boolean exists = true;
    static private BufferedImage gunImg;
    static private BufferedImage scarabImg;
    static private BufferedImage ankhImg;
    static private BufferedImage statueImg;
    static private BufferedImage papyrusImg;
    static private BufferedImage swordImg;
    static private BufferedImage potionImg;
    public BufferedImage currImage;
    private boolean isShowing;

    public Collectable(int x, int y, boolean gun, boolean scarab, boolean ankh, boolean statue, boolean papyrus, boolean sword, boolean potion, boolean visible){
        this.x = x;
        this.y = y;
        this.hitBoxRectangle = new Rectangle(x, y, 30, 30);
        this.gun = gun;
        this.scarab = scarab;
        this.ankh = ankh;
        this.statue = statue;
        this.papyrus = papyrus;
        this.sword = sword;
        this.potion = potion;
        this.isShowing = visible;
    }

    public boolean getShowing(){
        return this.isShowing;
    }

    public void setShowing(boolean s){
        this.isShowing = s;
    }

    public static void setGunImg(BufferedImage image){
        Collectable.gunImg = image;
    }

    public static void setScarabImg(BufferedImage image){
        Collectable.scarabImg = image;
    }

    public static void setAnkhImg(BufferedImage image){
        Collectable.ankhImg = image;
    }

    public static void setStatueImg(BufferedImage image){
        Collectable.statueImg = image;
    }

    public static void setPapyrusImg(BufferedImage image){
        Collectable.papyrusImg = image;
    }

    public static void setSwordImg(BufferedImage image){
        Collectable.swordImg = image;
    }

    public static void setPotionImg(BufferedImage image){
        Collectable.potionImg = image;
    }

    public void update(){

    }

    public void drawImage(Graphics2D g) {
        if(this.isShowing) {
            if (this.gun) {
                g.drawImage(gunImg, x, y, 30, 30, null);
            }
            if (this.scarab) {
                g.drawImage(scarabImg, x, y, 30, 30, null);
            }
            if (this.ankh) {
                g.drawImage(ankhImg, x, y, 30, 30, null);
            }

            if (this.statue) {
                g.drawImage(statueImg, x, y, 30, 30, null);
            }

            if (this.papyrus) {
                g.drawImage(papyrusImg, x, y, 30, 30, null);
            }
            if (this.sword) {
                g.drawImage(swordImg, x, y, 30, 30, null);
            }

            if (this.potion) {
                g.drawImage(potionImg, x, y, 30, 30, null);
            }
        }
    }

    public void collided() {
        this.exists = false;
    }

}

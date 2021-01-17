
/**
 *
 * UnbreakableWall.java
 * @author anthony-pc
 * Written by: Megan (Em) Powers
 *
 * Represents an unbreakable wall.
 *
 */

package GameObject;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UnpushableWall extends Wall {
    private int wallType;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean wallOne;
    private boolean wallTwo;
    private boolean isVisible = true;
    private static BufferedImage uncrackedWall, crackedWall;
    private Rectangle hitBox;

    public UnpushableWall(int width, int height, int x, int y, boolean wallOne, boolean WallTwo) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.wallOne = wallOne;
        this.wallTwo = wallTwo;
        wallType = 1;
        this.hitBox = new Rectangle(x, y, this.uncrackedWall.getWidth(), this.crackedWall.getHeight());
    }
    public void setWallType(){
        this.wallType = 0;
    }


    public static void setUnpushableUncracked(BufferedImage image){
        uncrackedWall = image;
    }


    public static void setUnpushableCracked(BufferedImage image){
        crackedWall = image;
    }

    public int getWallType(){
        return this.wallType;
    }

    public Rectangle getHitBox(){
        return hitBox.getBounds();
    }

    public boolean getShowing(){
        return isVisible;
    }

    @Override
    public void update() {

    }

    public void drawImage(Graphics2D g) {
        if(this.wallOne) {
            g.drawImage(uncrackedWall, x, y, null);
        }else if(this.wallTwo) {
            g.drawImage(crackedWall, x, y, null);
        }
    }

}

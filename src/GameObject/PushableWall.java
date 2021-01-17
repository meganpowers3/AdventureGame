
/**
 *
 * BreakableWall.java
 * Written by: Megan (Em) Powers
 *
 * Represents a breakable wall.
 *
 */

package GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PushableWall extends Wall {
    private int wallType;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean verticalPushable;
    private boolean horizontalPushable;
    private boolean specialPushable;
    private int numHits = 3;
    private boolean isVisible = true;
    private static BufferedImage verticalImage, horizontalImage, specialImage;
    private Rectangle hitBox;

    private boolean explorerPushing = false;


    private boolean isPushed = false;

    Music m1;
    String smallSFX;

    public PushableWall(int width, int height, int x, int y, boolean wallOne, boolean wallTwo, boolean wallThree) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.verticalPushable = wallOne;
        this.horizontalPushable = wallTwo;
        this.specialPushable = wallThree;
        wallType = 1;
        smallSFX = "Block.wav";
        m1 = new Music(2, smallSFX);
        this.hitBox = new Rectangle(x, y, 32, 32);
    }

    public void setWallType(){
        this.wallType = 1;
    }

    public boolean getCollision(){
        return isPushed;
    }

    public int getWallType(){
        return this.wallType;
    }

    public void setNumHits(){
        this.numHits = 3;
    }

    public int getNumHits(){
        return numHits;
    }

    public static void setVerticalPushable(BufferedImage img){
        verticalImage = img;
    }

    public static void setHorizontalPushable(BufferedImage img){
        horizontalImage = img;
    }

    public static void setSpecialPushable(BufferedImage img){
        specialImage = img;
    }

    public boolean getVerticalPushable(){
        return verticalPushable;
    }

    public boolean getHorizontalPushable(){
        return horizontalPushable;
    }

    public boolean getExplorerPushing(){
        return explorerPushing;
    }

    public void setExplorerPushing(boolean pushing){
        this.explorerPushing = pushing;
    }

    public boolean getSpecialPushable(){
        return specialPushable;
    }

    public void setVerticalPushableWall(boolean wallType){
        verticalPushable = wallType;
    }

    public void setHorizontalPushableWall(boolean wallType){
        horizontalPushable = wallType;
    }

    public void setSpecialPushableWall(boolean wallType){
        specialPushable = wallType;
    }

    public boolean isBeingPushed(){
        return isPushed;
    }

    public void setBeingPushed(boolean pushed){
        this.isPushed = pushed;
    }

    private void subtractHits(int n){
        this.numHits = numHits - n;
    }

    public void pushHorizontalWall(int vxIncrement){
        if(!isPushed){
            m1.play();
            x = x + vxIncrement;
            this.hitBox.setLocation(this.x, this.y);
        }
    }

    public void pushVerticalWall(int vyIncrement){
        if(!isPushed){
            m1.play();
            y = y + vyIncrement;
            this.hitBox.setLocation(this.x, this.y);
        }
    }

    public Rectangle getHitBox(){
        return hitBox.getBounds();
    }

    public boolean getShowing(){
        return isVisible;
    }

    @Override
    public void update() {
        this.isPushed = false;
    }

    public void drawImage(Graphics2D g) {
        if(this.verticalPushable) {
            g.drawImage(verticalImage, x, y, null);
        }else if(this.horizontalPushable) {
            g.drawImage(horizontalImage, x, y, null);
        }else if(this.specialPushable) {
            g.drawImage(specialImage, x, y, null);
        }
    }
}

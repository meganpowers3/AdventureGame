package GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Scorpion extends Enemy {
    private static BufferedImage animatedLeft;
    private static BufferedImage animatedRight;
    private static int playerXPos;
    private static int playerYPos;
    private int vx;
    private int previousPosition;
    private boolean setDirection = false;
    private boolean defaultLeftImage;
    private boolean defaultRightImage;
    private int scorpionHP = 10;
    Rectangle hitBox;

    private boolean isShowing = true;

    Music m1;
    String smallSFX;


    Scorpion (int spawnXPos, int spawnYPos, int vx, boolean isUpwards){
        this.x = spawnXPos;
        this.y = spawnYPos;
        this.vx = vx;
        this.setDirection = isUpwards;
        this.hitBox = new Rectangle(x, y, 50, 50);
        smallSFX = "Scorpion.wav";
        m1 = new Music(2, smallSFX);
    }

    public static void playerPosition(int playerXPos, int playerYPos){
        Scorpion.playerXPos = playerXPos;
        Scorpion.playerYPos = playerYPos;
    }

    public static void setScorpionAnimation(BufferedImage imageLeft, BufferedImage imageRight){
        Scorpion.animatedLeft = imageLeft;
        Scorpion.animatedRight = imageRight;
    }

    public void reduceHP(int h){
        this.scorpionHP = this.scorpionHP - h;
    }

    public void scorpionDie(){
        if(scorpionHP <= 0){
            this.isShowing = false;
        }
    }

    public boolean getShowing(){
        return this.isShowing;
    }

    public void setShowing(boolean s){
        this.isShowing = s;
    }


    public int getPreviousPosition(){
        return previousPosition;
    }

    public boolean getDirectionFacing(){
        return setDirection;
    }

    public Rectangle getHitBox(){
        return hitBox;
    }

    @Override
    public void switchMotionDirection() {
        this.setDirection = !this.setDirection;
    }

    @Override
    public void update() {
        this.previousPosition = this.x;
        if(this.setDirection){
            if(y <= playerYPos + 15 && y >= playerYPos - 15 && playerXPos < x){
                m1.play();
                x = x -(2 * vx);
            }else{
                x = (x - vx);
            }
        }else{
            if(y <= playerYPos + 15 && y >= playerYPos - 15 && playerXPos > x){
                m1.play();
                x = x + (2 * vx);
            }else{
                x = (x + vx);
            }
        }
        hitBox.setLocation(x, y);
    }

    public void collision(){

    }

    @Override
    public void drawImage(Graphics2D g) {
        if(isShowing == true) {
            if (setDirection) {
                if (defaultLeftImage) {
                    this.defaultLeftImage = false;
                }
                g.drawImage(animatedLeft, x, y, null);
            } else {
                if (defaultRightImage) {
                    this.defaultRightImage = false;
                }
                g.drawImage(animatedRight, x, y, null);
            }
        }
        if (scorpionHP <= 0) {
            this.isShowing = false;
        }
    }
}

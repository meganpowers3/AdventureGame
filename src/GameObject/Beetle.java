package GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Beetle extends Enemy {
    private static BufferedImage animatedUpwards;
    private static BufferedImage animatedDownwards;
    private static int playerXPos;
    private static int playerYPos;
    private int vy;
    private int previousPosition;
    private boolean setDirection = false;
    private boolean defaultUpImage;
    private boolean defaultDownImage;
    Rectangle hitBox;
    private int beetleHP = 10;

    private boolean isShowing = true;

    Music m1;
    String smallSFX;

    Beetle(int spawnXPos, int spawnYPos, int vy, boolean isUpwards){
        this.x = spawnXPos;
        this.y = spawnYPos;
        this.vy = vy;
        this.setDirection = isUpwards;
        this.hitBox = new Rectangle(x, y, 32, 32);
        smallSFX = "Beetle.wav";
        m1 = new Music(2, smallSFX);
    }

    public static void playerPosition(int playerXPos, int playerYPos){
        Beetle.playerXPos = playerXPos;
        Beetle.playerYPos = playerYPos;
    }

    public static void setBeetleAnimation(BufferedImage imageUp, BufferedImage imageDown){
        Beetle.animatedUpwards = imageUp;
        Beetle.animatedDownwards = imageDown;
    }

    public void reduceHP(int h){
        this.beetleHP = this.beetleHP - h;
    }

    public void scorpionDie(){
        if(beetleHP <= 0){
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

    public Rectangle getHitBox(){
        return hitBox;
    }

    public boolean getDirectionFacing(){
        return setDirection;
    }

    @Override
    public void switchMotionDirection() {
        this.setDirection = !this.setDirection;
    }

    @Override
    public void update() {
        this.previousPosition = this.y;
        if(this.setDirection){
            if(x <= playerXPos + 15 && x >= playerXPos - 15 && playerYPos < y){
                m1.play();
                y -= 2 * vy;
            }else{
                y -= vy;
            }
        }else{
            if(x <= playerXPos + 15 && x >= playerXPos - 15 && playerYPos > y){
                m1.play();
                y += 2 * vy;
            }else{
                y += vy;
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
                if (defaultUpImage) {
                    this.defaultUpImage = false;
                }
                g.drawImage(animatedUpwards, x, y, null);
            } else {
                if (defaultDownImage) {
                    this.defaultDownImage = false;
                }
                g.drawImage(animatedDownwards, x, y, null);
            }
        }
        if (beetleHP <= 0) {
            this.isShowing = false;
        }

    }
}

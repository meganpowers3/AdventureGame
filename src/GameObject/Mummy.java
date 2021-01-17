package GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Mummy extends Enemy {
    private static BufferedImage animatedUpwards;
    private static BufferedImage animatedDownwards;
    private static BufferedImage animatedRight;
    private static BufferedImage animatedLeft;
    private static int playerXPos;
    private static int playerYPos;
    int xBottomBorder;
    int yBottomBorder;
    int xTop;
    int yTop;
    private int previousPosition;
    private boolean setDirection;
    private boolean defaultUpImage = false;
    private boolean defaultDownImage = false;
    private boolean defaultLeftImage = false;
    private boolean defaultRightImage = false;

    private static BufferedImage animatedDownArray[] = new BufferedImage[4];
    private static BufferedImage animatedUpArray[] = new BufferedImage[4];
    private static BufferedImage animatedLeftArray[] = new BufferedImage[4];
    private static BufferedImage animatedRightArray[] = new BufferedImage[4];

    private boolean mummyUp;
    private boolean mummyDown;
    private boolean mummyLeft;
    private boolean mummyRight;

    private static boolean canDamage = false;
    private static long canDamageCounter = 0;

    boolean canMove = true;

    private boolean isShowing = true;

    Rectangle hitBox;

    Music m1;
    String smallSFX;

    public long leftIterator = 0;
    public long rightIterator = 0;
    public long upIterator = 0;
    public long downIterator = 0;
    private int imageIterator1 = 0;
    private int imageIterator2 = 0;
    private int imageIterator3 = 0;
    private int imageIterator4 = 0;


    Mummy(int spawnXPos, int spawnYPos, int vx, int vy, int xBottomBorder, int yBottomBorder, int xTop, int yTop){
        this.x = spawnXPos;
        this.y = spawnYPos;
        this.vx = vx;
        this.vy = vy;
        this.xBottomBorder = xBottomBorder;
        this.yBottomBorder = yBottomBorder;
        this.xTop = xTop;
        this.yTop = yTop;
        this.setMummyDirection(true, false, false, false);
        this.hitBox = new Rectangle(x, y, 32, 32);
        smallSFX = "Scared.wav";
        m1 = new Music(2, smallSFX);
    }


    public boolean getShowing(){
        return this.isShowing;
    }

    public void setShowing(boolean s){
        this.isShowing = s;
    }

    public static void playerPosition(int playerXPos, int playerYPos){
        Mummy.playerXPos = playerXPos;
        Mummy.playerYPos = playerYPos;
    }

    public static void setMummyAnimation(BufferedImage imageUp, BufferedImage imageDown, BufferedImage imageLeft, BufferedImage imageRight){
        Mummy.animatedUpwards = imageUp;
        Mummy.animatedDownwards = imageDown;
        Mummy.animatedLeft = imageLeft;
        Mummy.animatedRight = imageRight;
    }


    public static void setTimeCounter(long t){
        Mummy.canDamageCounter = t;
    }

    public static void canDamageMummy(boolean damageMummy){
        Mummy.canDamage = damageMummy;
    }

    public static boolean getCanDamageMummy(){
        return Mummy.canDamage;
    }

    public int getPreviousPosition(){
        return previousPosition;
    }

    public boolean getDirectionFacing(){
        return setDirection;
    }

    @Override
    public void switchMotionDirection() {
        this.setDirection = !this.setDirection;
    }

    public void setMotion(boolean canMove){
        this.canMove = canMove;
    }

    public Rectangle getHitBox(){
        return hitBox;
    }

    public Rectangle mummyCollision(){
        return new Rectangle(x + vx, y + vy, 20, 20);
    }

    public void setMummyDirection(boolean up, boolean down, boolean left, boolean right){
        this.mummyUp = up;
        this.mummyDown = down;
        this.mummyLeft = left;
        this.mummyRight = right;
    }

    @Override
    public void update() {
        double playerDistance;
        playerDistance = sqrt(  pow((playerXPos - x), 2) +  pow((playerYPos - y), 2)  );
        if(canMove) {

            if (Mummy.canDamage == false) {
                if (playerDistance < 200) {
                    if (playerXPos < this.x && this.x - vx >= xBottomBorder) {
                        x = x - vx;
                        this.setMummyDirection(false, false, true, false);
                    }
                    if (playerXPos > this.x && this.x + vx <= xTop) {
                        x = x + vx;
                        this.setMummyDirection(false, false, false, true);
                    }
                    if (playerYPos < this.y && this.y - vy >= yBottomBorder) {
                        y = y - vy;
                        this.setMummyDirection(true, false, false, false);
                    }
                    if (playerYPos > this.y && this.y + vy <= yTop) {
                        y = y + vy;
                        this.setMummyDirection(false, true, false, false);
                    }
                }
            } else {
                if (System.currentTimeMillis() - canDamageCounter < 5000) {
                    if (playerXPos < this.x && this.x + vx <= xTop) {
                        m1.play();
                        x = x + vx;
                        this.setMummyDirection(false, false, false, true);
                    }
                    if (playerXPos > this.x && this.x - vx >= xBottomBorder) {
                        m1.play();
                        x = x - vx;
                        this.setMummyDirection(false, false, true, false);
                    }
                    if (playerYPos < this.y && this.y + vy <= yTop) {
                        m1.play();
                        y = y + vy;
                        this.setMummyDirection(false, true, false, false);
                    }
                    if (playerYPos > this.y && this.y - vy >= yBottomBorder) {
                        m1.play();
                        y = y - vy;
                        this.setMummyDirection(true, false, false, false);
                    }
                } else {
                    Mummy.canDamage = false;
                }
            }
        }
        hitBox.setLocation(x, y);
        canMove = true;
    }

    public void collision(){

    }

    @Override
    public void drawImage(Graphics2D g) {
        if(this.isShowing) {
            if (this.mummyUp == true) {
                g.drawImage(animatedUpwards, x, y, null);
            } else if (this.mummyDown == true) {
                g.drawImage(animatedDownwards, x, y, null);
            } else if (this.mummyLeft == true) {
                g.drawImage(animatedLeft, x, y, null);
            } else if (this.mummyRight == true) {
                g.drawImage(animatedRight, x, y, null);
            } else {
                g.drawImage(animatedUpwards, x, y, null);
            }
        }
    }
}

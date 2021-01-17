/**
 *
 * Shell.java
 * Written by: Megan (Em) Powers
 *
 * Acts as an abstract class for weapon powerups.
 *
 */

package GameObject;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Shell extends Weapon {

    private int damage;
    private int bulletXSpeed;
    private int bulletYSpeed;
    private boolean isShowing = false;
    private static BufferedImage bulletImage;
    private Rectangle hitBox;
    private static BufferedImage smallExplosionImg, largeExplosionImg;
    public static boolean smallExplosion = false;
    public static boolean largeExplosion = false;
    public boolean collision = false;
    public int timetoCollide = 0;
    String largeSFX, smallSFX;
    Music m1, m2;

    private int vx;
    private int vy;
    private int angle;
    private final int R = 2;

    public Shell(int damage, int bulletXSpeed, int bulletYSpeed, int vx, int vy, int angle, int x, int y, BufferedImage bulletImage) {
        super(damage, bulletXSpeed, bulletYSpeed, vx, vy, angle, x, y, bulletImage);
        this.isShowing = true;
        this.hitBox = new Rectangle(x, y, bulletImage.getWidth(), bulletImage.getHeight());
      //  largeSFX = "Explosion_large.wav";
      //  m1 = new Music(2, largeSFX);
     //   smallSFX = "Explosion_small.wav";
     //   m2 = new Music(2, smallSFX);
    }

    public boolean getShowing(){
        return this.isShowing;
    }

    public void setShowing(boolean s){
        this.isShowing = s;
    }

    public static void setSmallExplosionImg(BufferedImage smallE){
        smallExplosionImg = smallE;
    }

    public static void setLargeExplosionImg(BufferedImage largeE){
        largeExplosionImg = largeE;
    }

    public static void setSmallExplosion(boolean hasExploded){
        smallExplosion = hasExploded;
    }

    public static void setLargeExplosion(boolean hasExploded){
        largeExplosion = hasExploded;
    }

    public static boolean getSmallExplosion(){
        return smallExplosion;
    }

    public boolean getLargeExplosion(){
        return largeExplosion;
    }

    public static void setImg(BufferedImage img){
        bulletImage = img;
    }

    private int getDamage() {
        return this.damage;
    }

    public Rectangle getHitBox(){
        return hitBox.getBounds();
    }

    // getters and setters for motion

    public void setVX(int vx){
        this.vx = vx;
    }

    public void setVY(int vy){
        this.vy = vy;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    @Override
    public void update() {

    }

    public void setAngle(int angle){
        this.angle = angle;
    }

    // see whether or not the bullet has collided

    public void hitObject(boolean collided){
        collision = collided;
    }


    public void fireBullet(int w, int h) {
        if(y < h-40 && y > 0 && x > 0 && x < w-40 && !collision){
            x += Math.round(Math.cos(Math.toRadians(angle)));
            y += Math.round( Math.sin(Math.toRadians(angle)));
            this.hitBox.setLocation(x, y);
        }
        else{
            timetoCollide = timetoCollide + 1;
        }
    }


    public void drawImage(Graphics2D g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);

        rotation.rotate(Math.toRadians(angle), this.bulletImage.getWidth() / 2.0, this.bulletImage.getHeight() / 2.0);

        if(isShowing == true) {
            if (collision == true) {
//                m2.play();
                g.drawImage(this.bulletImage, rotation, null);
                g.drawImage(smallExplosionImg, rotation, null);

                this.isShowing = false;
            } else {
                g.drawImage(this.bulletImage, rotation, null);
            }
        }
    }
}

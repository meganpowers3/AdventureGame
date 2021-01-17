
/**
 *
 * Weapon.java
 * Written by: @ajsouza
 * Contributions by: Megan (Em) Powers
 *
 * Acts as an abstract class for weapon powerups.
 *
 */

package GameObject;

import static java.lang.Math.abs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static javax.imageio.ImageIO.read;

public class Player extends PlayerObject {
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int stationaryVx;
    private int stationaryVy;
    private int angle;
    private int health = 100;
    private int shellDamage;
    private int shellSpeed;
    public static int lives;
    public static int score = 0;
    private boolean canMove = true;

    boolean hasPlayed = false, hasPlayed2 = false;

    private static BufferedImage playerDownImage, playerUpImage, playerLeftImage, playerRightImage, currentImage;
    private static BufferedImage animatedDown[] = new BufferedImage[4];
    private static BufferedImage animatedUp[] = new BufferedImage[4];
    private static BufferedImage animatedLeft[] = new BufferedImage[4];
    private static BufferedImage animatedRight[] = new BufferedImage[4];
    private BufferedImage bulletImage;

    private static boolean stationaryUp = false;
    private static boolean stationaryDown = false;
    private static boolean stationaryLeft = false;
    private static boolean stationaryRight = false;

    private BufferedImage [] healthBarImg;
    private HealthComponent healthBar;
    private Rectangle hitBox;

    private static boolean UpPressed;
    private static boolean DownPressed;
    private boolean RightPressed;
    private static boolean LeftPressed;
    private boolean shootShell;
    private Shell playerShell;
    private ArrayList<Shell> shellList;
    private static int scarabList;
    private boolean scarabActive = false;
    private boolean hasSword = false;
    private boolean swordActive = false;

    int previousXPos;
    int previousYPos;

    public long leftIterator = 0;
    public long rightIterator = 0;
    public long upIterator = 0;
    public long downIterator = 0;
    private int imageIterator1 = 0;
    private int imageIterator2 = 0;
    private int imageIterator3 = 0;
    private int imageIterator4 = 0;

    private static BufferedImage lightImg, lightImg2;

    private long timeDelayScarab = 0;
    private long timeDelayScore = 0;

    private int hits = 0;

    private MainRoom room;

    private boolean canShootGun = false;


    Timer timer = new Timer();


    //plane game
    String smallSFX1, smallSFX2, smallSFX3;
    Music m1, m2, m3;

    public Player(int x, int y, int vx, int vy, int currentLives, int health, int bulletDamage, BufferedImage left, BufferedImage right, BufferedImage up, BufferedImage down) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.stationaryVx = abs(vx);
        this.stationaryVy = abs(vy);
        this.lives = 3;
        this.health = 100;
        this.healthBarImg = new BufferedImage[5];
        this.shellDamage = 5;
        this.shellSpeed = 1;
        this.playerDownImage = down;
        this.playerUpImage = up;
        this.playerLeftImage = left;
        this.playerRightImage = right;

        smallSFX1 = "Die.wav";
        m1 = new Music(2, smallSFX1);
        smallSFX2 = "PowerStart.wav";
        m2 = new Music(2, smallSFX2);
        smallSFX3 = "PowerEnd.wav";
        m3 = new Music(2, smallSFX3);

        this.angle = angle;
        this.score = 0;
        this.hitBox = new Rectangle(x, y, 32, 32);


        this.shellList = new ArrayList<Shell>(100);

        try {
            bulletImage = read(MainRoom.class.getClassLoader().getResource("Bullet.gif"));
            healthBarImg[0] = read(MainRoom.class.getClassLoader().getResource("Health_Full.png"));
            healthBarImg[1] = read(MainRoom.class.getClassLoader().getResource("Health_80.png"));
            healthBarImg[2] = read(MainRoom.class.getClassLoader().getResource("Health_60.png"));
            healthBarImg[3] = read(MainRoom.class.getClassLoader().getResource("Health_40.png"));
            healthBarImg[4] = read(MainRoom.class.getClassLoader().getResource("Health_20.png"));
            Shell.setImg(bulletImage);
        } catch (IOException e) {
            System.out.println("Assets not found, please try again.");
            e.printStackTrace();
        }
    }

    public static void setDownwardFacingImage(BufferedImage img){
        Player.playerDownImage = img;
    }

    public static void setUpwardFacingImage(BufferedImage img){
        Player.playerUpImage = img;
    }

    public static void setLeftFacingImage(BufferedImage img){
        Player.playerLeftImage = img;
    }

    public static void setRightFacingImage(BufferedImage img){
        Player.playerRightImage = img;
    }


    public void setRoom(MainRoom room){
        this.room = room;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public Rectangle getHitBox(){
        return hitBox.getBounds();
    }

    public int getScarabNumber(){
        return this.scarabList;
    }

    public void setScarabNumber(int num){
        this.scarabList = num;
    }

    public void addScarab(int num){
        if(this.scarabList < 3) {
            this.scarabList = this.scarabList + num;
        }
    }

    private void removeScarab(int num){
        if (this.scarabList - num < 1 ){
            this.scarabList = 0;
        }else{
            this.scarabList -= num;
        }
    }

    public void toggleUpPressed() {
        this.UpPressed = true;
    }

    public void toggleDownPressed() {
        this.DownPressed = true;
    }

    public void toggleRightPressed() {
        this.RightPressed = true;
    }

    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    public void toggleShoot() {
        this.shootShell = true;
    }

    public void activateScarab(){
        this.scarabActive = true;
    }

    public void deactivateScarab(){
        this.scarabActive = false;
        hasPlayed = false;
    }

    public void playerHasSword(){
        this.hasSword = true;
    }

    public void activateSword(){
        if(this.score > 0) {
            this.swordActive = true;
        }else{
            this.swordActive = false;
        }
    }

    public void setPreviousX(int x){
        this.previousXPos = x;
    }

    public int getPreviousX(){
       return previousXPos;
    }

    public void setPreviousY(int Y){
        this.previousYPos = y;
    }

    public int getPreviousY(){
        return previousYPos;
    }


    public boolean getActiveSword(){
        return this.hasSword;
    }

    public static void setLight(BufferedImage light, BufferedImage light2){
        lightImg = light;
        lightImg2 = light2;
    }

    public void deactivateSword(){
        this.swordActive = false;
        hasPlayed = false;
    }

    public ArrayList<Shell> getShells(){
        return this.shellList;
    }

    public void unToggleUpPressed() {
        this.UpPressed = false;
    }

    public void unToggleDownPressed() {
        this.DownPressed = false;
    }

    public void unToggleRightPressed() {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    public void unToggleShoot() {
        this.shootShell = false;
    }

    public static boolean getUp() {
        return stationaryUp;
    }

    public static boolean getDown() {
        return stationaryDown;
    }

    public static boolean getLeft() {
        return stationaryLeft;
    }

    public void setShoot(boolean shootPistol){
        this.canShootGun = shootPistol;
    }

    public boolean canShoot(){
        return shootShell;
    }

    public void setScore(int s){
        this.score = this.score + s;
    }

    public static int getScore(){
        return score;
    }

    public void addScore(int s){
        this.score = s;
    }

    public void removeScore(int scoreDecrement){
        if(this.score - scoreDecrement >= 0){
            this.score = score - scoreDecrement;
        }else{
            this.score = 0;
        }
    }

    public void addLife(int s){
        this.lives = this.lives + s;
    }

    public static int getLives(){
        return lives;
    }

    public static int getNumScarabs(){
        return scarabList;
    }

    public void update() {

        this.previousXPos = x;
        this.previousYPos = y;
        this.hitBox.setLocation(x, y);

        if (this.UpPressed) {
            currentImage = playerUpImage;
            this.moveUp();
        }else if (this.DownPressed) {
            currentImage = playerDownImage;
            this.moveDown();
        }else if (this.LeftPressed) {
            currentImage = playerLeftImage;
            this.moveLeft();
        } else if (this.RightPressed) {
            currentImage = playerRightImage;
            this.moveRight();
        }else{
            if(currentImage == playerDownImage){
                stationaryDown = true;
            }else if(currentImage == playerUpImage){
                stationaryUp = true;
            }else if(currentImage == playerLeftImage){
                stationaryLeft = true;
            }else if(currentImage == playerRightImage) {
                stationaryRight = true;
            }
        }

        if(this.shootShell && this.canShootGun){
            fireShell();
            shootShell = false;
        }

        if(this.swordActive){
            activateSword();
        }

        if(this.score < 1){
            this.swordActive = false;
        }

        if(this.scarabActive == true && this.scarabList > 0 && (System.currentTimeMillis() - timeDelayScarab) > 1000){
            if(!hasPlayed2){
                m2.play();
                hasPlayed2 = true;
            }
            this.removeScarab(1);
            Mummy.canDamageMummy(true);
            Mummy.setTimeCounter(System.currentTimeMillis());
            timeDelayScarab = System.currentTimeMillis();
        }

        if(this.swordActive == true){
            if(!hasPlayed){
                m2.play();
                hasPlayed = true;
            }
            Mummy.canDamageMummy(true);
        }

        if(this.swordActive == true && System.currentTimeMillis() - timeDelayScore > 100){
            this.removeScore(1);
            this.timeDelayScore = System.currentTimeMillis();
        }

        if(this.scarabActive == true && (System.currentTimeMillis() - timeDelayScarab) < 1025){
            m3.play();
        }

        Mummy.playerPosition(this.x, this.y);
        Scorpion.playerPosition(this.x, this.y);
        Beetle.playerPosition(this.x, this.y);

        canMove = true;
    }

    public Rectangle playerCollision(){
        return new Rectangle(x + vx, y + vy, 40, 40);
    }

    private void fireShell(){

        playerShell = new Shell(shellDamage, 1, 1, vx, vy, angle, x, y, bulletImage);
        playerShell.setX(x + vx);
        playerShell.setY(y + vy / 2);
        playerShell.setVX(vx);
        playerShell.setVX(vy);
        playerShell.setAngle(angle);
        room.addObjects(playerShell);
        shellList.add(playerShell);
    }

    public Shell getShell(){
        return playerShell;
    }

    private void moveDown() {
        vx = 0;
        vy = this.stationaryVy;
        angle = 90;

        if(canMove == true) {
            y += vy;
        }
    }

    private void moveUp() {
        vx = 0;
        vy = -this.stationaryVy;
        angle = 270;
        if(canMove == true) {
            y += vy;
        }
    }

    private void moveLeft() {
        vx = -this.stationaryVx;
        vy = 0;
        angle = 180;
        if(canMove == true) {
            x += vx;
        }
    }

    private void moveRight() {
        vx = this.stationaryVx;
        vy = 0;
        angle = 0;
        if(canMove == true) {
            x += vx;
        }
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }


    public int getVX(){
        return vx;
    }

    public int getVY(){
        return vy;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + ", angle=" + angle;
    }

    public void loseHealth(int h){

        if(hits < 1) {
            if (health - h > 0) {
                this.health = this.health - h;
            } else {
                health = 0;
                if(lives > 0) {
                    m1.play();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else{
            hits = hits - 1;
        }
    }

    public void addHealth(int addedHealth){
        this.health = health + addedHealth;
    }

    public int getHealth(){
        return this.health;
    }

    public void drawHealthBar(Graphics2D g2d){

        if(this.health > 80){
            healthBar = new HealthComponent(healthBarImg[0], x+50, y + 20);
            healthBar.drawImage(g2d);
        }else if(this.health > 60 && this.health <= 80){
            healthBar = new HealthComponent(healthBarImg[1], x+50, y + 20);
            healthBar.drawImage(g2d);
        }else if(this.health > 40 && this.health <= 60){
            healthBar = new HealthComponent(healthBarImg[2], x+50, y + 20);
            healthBar.drawImage(g2d);
        }else if(this.health > 20 && this.health <= 40){
            healthBar = new HealthComponent(healthBarImg[3], x+50, y + 20);
            healthBar.drawImage(g2d);
        }else if(this.health > 0 && this.health <= 20) {
            healthBar = new HealthComponent(healthBarImg[4], x + 50, y + 20);
            healthBar.drawImage(g2d);
        }
    }

    public void setMotion(boolean canMove){
        this.canMove = canMove;
    }

    public boolean getMotion() {
        return canMove;
    }

    @Override
    public void drawImage(Graphics2D g2d) {

        if(hasSword && !swordActive){
            g2d.drawImage(lightImg, (x - lightImg.getWidth()/2 + 40), (y-lightImg.getHeight()/2 + 20), null);
        }else if(hasSword && swordActive){
            g2d.drawImage(lightImg2, (x - lightImg.getWidth()/2 + 40), (y-lightImg.getHeight()/2 + 20), null);
        }

        if(currentImage == playerDownImage){
            g2d.drawImage(playerDownImage, x, y, null);
        }else if(currentImage == playerUpImage){
            g2d.drawImage(playerUpImage, x, y, null);
        }else if(currentImage == playerLeftImage){
            g2d.drawImage(playerLeftImage, x, y, null);
        }else if(currentImage == playerRightImage){
            g2d.drawImage(playerRightImage, x, y, null);
        }else{
            g2d.drawImage(playerDownImage, x, y, null);
        }

        if(health < 1) {
            try {
                Font font = new Font(Font.SANS_SERIF, Font.BOLD, 40);
                g2d.setFont(font);
                g2d.drawString("LIFE LOST", x, y);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        drawHealthBar(g2d);
    }
}

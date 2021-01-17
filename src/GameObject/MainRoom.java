package GameObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static javax.imageio.ImageIO.read;

/**
 * Main driver class of Tank Example.
 * Class is responsible for loading resources and
 * initializing game objects. Once completed, control will
 * be given to infinite loop which will act as our game loop.
 * A very simple game loop.
 * @author anthony-pc
 * @author Em Powers
 */

public class MainRoom extends JPanel  {

    public static final int SCREEN_WIDTH = 638;
    public static final int SCREEN_HEIGHT = 479;
    public static final int WORLD_WIDTH = 2260;
    public static final int WORLD_HEIGHT = 2606;

    private BufferedImage world;
    private Graphics2D buffer;
    private JFrame jFrame;
    private Player playerCharacter;
    private Collectable test;

    //Check if tank hit
    public static boolean tankOneHit = false;
    public static boolean tankTwoHit = false;
    private Background backgroundImage;

    // Images
    private BufferedImage backgroundTile;
    private BufferedImage horizontalPushableImg;
    private BufferedImage verticalPushableImg;
    private BufferedImage specialPushableImg;
    private BufferedImage breakableImg;
    private BufferedImage menuImg;

    private BufferedImage loadScreen;
    private BufferedImage enemyImg;
    private BufferedImage powerupImg;
    private BufferedImage wallImg;
    private BufferedImage victoryImg;
    private BufferedImage playerLostImg;

    private BufferedImage livesImg;
    private BufferedImage pistolImg;
    private BufferedImage swordImg;
    private BufferedImage scarabImg;
    private BufferedImage ankhTreasureImg;
    private BufferedImage statueTreasureImg;
    private BufferedImage doorImg;
    private static BufferedImage panelImg;
    private BufferedImage potionImg;
    private BufferedImage scrollImg;
    private BufferedImage startScreen;
    private BufferedImage victoryScreen;
    private BufferedImage gameOverScreen;
    private BufferedImage mainLogo;
    private BufferedImage helpButton;
    private BufferedImage loadButton;
    private BufferedImage quitButton;
    private BufferedImage scoresButton;
    private BufferedImage startButton;
    private BufferedImage unpushableOneImg;
    private BufferedImage unpushableTwoImg;
    private BufferedImage lightImage;
    private BufferedImage lightImage2;
    private BufferedImage scoreImg;

    private static final MainRoom tankGame = new MainRoom();
    public MapInterpreter mapFile;
    public EnemySpawner enemyFile;

    public int i = 1;

    //Player starting positions
    public static final int PLAYER_START_X = 60;
    public static final int PLAYER_START_Y = 60;

    // Menu
    public static MainMenu menu;

    //GameScore
    GameScore playerScore;

    //Audio
    Music m;
    static Music m1;
    static String smallSFX;

    private static Collision collisionChecker;

    enum GameState{
        TITLE_STATE,
        ABOUT_STATE,
        MAIN_STATE,
        MENU_STATE,
        POWERUP_STATE,
        ENEMY_STATE,
        SCORE_STATE,
        WALL_STATE,
        VICTORY_STATE,
        LOSE_STATE
    }

    static GameState currentState = GameState.TITLE_STATE;

    public static void setState(GameState g){
        currentState = g;
    }

    public static ArrayList<PlayerObject> mapObjects = new ArrayList<>();

    public void fillMapArray(ArrayList<PlayerObject> mapObjects){
        this.mapObjects = mapObjects;
    }

    public void addObjects(PlayerObject currObject){
        this.mapObjects.add(currObject);
    }

    {
        try {
            menuImg = read(MainRoom.class.getClassLoader().getResource("MainMenu.png"));
            scoreImg = read(MainRoom.class.getClassLoader().getResource("ScoreScreen.png"));
            backgroundTile = read(MainRoom.class.getClassLoader().getResource("Background2.bmp"));
            horizontalPushableImg = read(MainRoom.class.getClassLoader().getResource("Block_hor.gif"));
            verticalPushableImg = read(MainRoom.class.getClassLoader().getResource("Block_vert.gif"));
            specialPushableImg = read(MainRoom.class.getClassLoader().getResource("Door.gif"));
            breakableImg = read(MainRoom.class.getClassLoader().getResource("Wall2.gif"));
            startScreen = read(MainRoom.class.getClassLoader().getResource("Background1.bmp"));
            loadScreen = read(MainRoom.class.getClassLoader().getResource("LoadScreen.bmp"));
            //to do     helpImg = read(MainRoom.class.getClassLoader().getResource("Instruction_Screen.png"));
            panelImg = read(MainRoom.class.getClassLoader().getResource("Panel.gif"));
            livesImg = read(MainRoom.class.getClassLoader().getResource("Lives.gif"));
            pistolImg = read(MainRoom.class.getClassLoader().getResource("Pistol.gif"));
            swordImg = read(MainRoom.class.getClassLoader().getResource("Sword.gif"));
            scarabImg = read(MainRoom.class.getClassLoader().getResource("Scarab.gif"));
            ankhTreasureImg = read(MainRoom.class.getClassLoader().getResource("Treasure1.gif"));
            statueTreasureImg = read(MainRoom.class.getClassLoader().getResource("Treasure2.gif"));
            potionImg = read(MainRoom.class.getClassLoader().getResource("Potion.gif"));
            scrollImg = read(MainRoom.class.getClassLoader().getResource("Scroll.gif"));
            ankhTreasureImg = read(MainRoom.class.getClassLoader().getResource("Treasure1.gif"));
            victoryScreen = read(MainRoom.class.getClassLoader().getResource("Congratulation.gif"));
            //make one          gameOverScreen = read(MainRoom.class.getClassLoader().getResource("Treasure1.gif"));
            mainLogo = read(MainRoom.class.getClassLoader().getResource("Title.gif"));
            helpButton = read(MainRoom.class.getClassLoader().getResource("Button_help.gif"));
            loadButton = read(MainRoom.class.getClassLoader().getResource("Button_load.gif"));
            quitButton = read(MainRoom.class.getClassLoader().getResource("Button_quit.gif"));
            scoresButton = read(MainRoom.class.getClassLoader().getResource("Button_scores.gif"));
            startButton = read(MainRoom.class.getClassLoader().getResource("Button_start.gif"));
            unpushableOneImg = read(MainRoom.class.getClassLoader().getResource("Wall1.gif"));
            unpushableTwoImg = read(MainRoom.class.getClassLoader().getResource("Wall2.gif"));
            loadScreen = read(MainRoom.class.getClassLoader().getResource("LoadScreen.bmp")); // about game
            enemyImg = read(MainRoom.class.getClassLoader().getResource("EnemyScreen.png"));
            powerupImg = read(MainRoom.class.getClassLoader().getResource("PowerupScreen.png"));
            wallImg = read(MainRoom.class.getClassLoader().getResource("WallScreen.png"));
            victoryImg = read(MainRoom.class.getClassLoader().getResource("GameOverOne.png"));
            playerLostImg  = read(MainRoom.class.getClassLoader().getResource("GameOverTwo.png"));
            lightImage = read(MainRoom.class.getClassLoader().getResource("Light.png"));
            lightImage2 = read(MainRoom.class.getClassLoader().getResource("LightActive.png"));

            // for the walls
            PushableWall.setHorizontalPushable(horizontalPushableImg);
            PushableWall.setVerticalPushable(verticalPushableImg);
            PushableWall.setSpecialPushable(specialPushableImg);
            UnpushableWall.setUnpushableUncracked(unpushableOneImg);
            UnpushableWall.setUnpushableCracked(unpushableTwoImg);
            BreakableWall.setBreakable(breakableImg);

            smallSFX = "Game over.wav";

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MainRoom getRoom(){
        return tankGame;
    }

    public static void main(String[] args) {
        MainRoom pyramidPanicExample = new MainRoom();
        pyramidPanicExample.init();
        pyramidPanicExample.collisionChecker = new Collision();

        m1 = new Music(2, smallSFX);

        try {
            while (true) {
                pyramidPanicExample.repaint();
                if (MainRoom.currentState == GameState.MAIN_STATE) {

                    pyramidPanicExample.repaint();

                    for(int i = 0; i < mapObjects.size(); i++){
                        for(int j = 0; j < mapObjects.size(); j++){
                            PlayerObject o1 = mapObjects.get(i);
                            PlayerObject o2 = mapObjects.get(j);
                            pyramidPanicExample.collisionChecker.collisionCheck(o1, o2);
                        }
                    }

                    for (int i = 0; i < mapObjects.size(); i++) {
                        if (MainRoom.mapObjects.get(i) instanceof BreakableWall) {
                            if (((BreakableWall) MainRoom.mapObjects.get(i)).getShowing() == false) {
                                MainRoom.mapObjects.remove(i);
                            }
                        }
                    }

                    for (int i = 0; i < mapObjects.size(); i++) {
                        if(MainRoom.mapObjects.get(i) instanceof Shell) {
                            if(((Shell) MainRoom.mapObjects.get(i)).getShowing() == false) {
                                MainRoom.mapObjects.remove(i);
                            }
                        }
                    }
                    for (int i = 0; i < mapObjects.size(); i++) {
                        if (MainRoom.mapObjects.get(i) instanceof Collectable) {
                            if (((Collectable) MainRoom.mapObjects.get(i)).getShowing() == false) {
                                MainRoom.mapObjects.remove(i);
                            }
                        }
                    }

                    for (int i = 0; i < mapObjects.size(); i++) {
                        if (MainRoom.mapObjects.get(i) instanceof Mummy) {
                            if (((Mummy) MainRoom.mapObjects.get(i)).getShowing() == false) {
                                MainRoom.mapObjects.remove(i);
                            }
                        }
                    }

                    if (pyramidPanicExample.playerCharacter.getHealth() == 0) {
                        if (pyramidPanicExample.playerCharacter.lives > 0) {
                            pyramidPanicExample.playerCharacter.lives--;
                            pyramidPanicExample.playerCharacter.setHealth(100);
                            pyramidPanicExample.playerCharacter.setX(pyramidPanicExample.playerCharacter.getX() + 25);
                            pyramidPanicExample.playerCharacter.setY(pyramidPanicExample.playerCharacter.getY() + 25);
                        }
                    }

                    for(int i = 0; i < mapObjects.size(); i++){
                        if( pyramidPanicExample.mapObjects.get(i) instanceof Enemy) {
                            pyramidPanicExample.mapObjects.get(i).update();
                        }else if( pyramidPanicExample.mapObjects.get(i) instanceof PushableWall) {
                            pyramidPanicExample.mapObjects.get(i).update();
                        }
                    }
                    if(pyramidPanicExample.playerCharacter.getLives() <= 0){
                        MainRoom.currentState = GameState.LOSE_STATE;
                    }
                    pyramidPanicExample.playerCharacter.update();

                    Thread.sleep(1000 / 144);
                }
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored);
        }
    }



    private void init() {
        this.jFrame = new JFrame("Pyramid Panic");
        this.world = new BufferedImage(MainRoom.WORLD_WIDTH, MainRoom.WORLD_HEIGHT, BufferedImage.TYPE_INT_RGB);
        BufferedImage playerSpriteDown = null;
        BufferedImage playerSpriteRight = null;
        BufferedImage playerSpriteLeft = null;
        BufferedImage playerSpriteUp = null;

        // for beetle animation

        BufferedImage beetleDown, beetleDown2, beetleDown3, beetleDown4;
        BufferedImage beetleUp, beetleUp2, beetleUp3, beetleUp4;

        // for scorpion animation

        BufferedImage scorpionLeft, scorpionRight2, scorpionRight3, scorpionRight4;
        BufferedImage scorpionRight, scorpionLeft2, scorpionLeft3, scorpionLeft4;

        // for mummy animation

        BufferedImage mummyDown, mummyDown2, mummyDown3;
        BufferedImage mummyRight, mummyRight2, mummyRight3;
        BufferedImage mummyLeft, mummyLeft2, mummyLeft3;
        BufferedImage mummyUp, mummyUp2, mummyUp3;

        // player animation

        BufferedImage animatedplayerDown,  animatedplayerDown2,  animatedplayerDown3;
        BufferedImage animatedplayerUp,  animatedplayerUp2,  animatedplayerUp3;
        BufferedImage animatedplayerLeft,  animatedplayerLeft2,  animatedplayerLeft3;
        BufferedImage animatedplayerRight,  animatedplayerRight2,  animatedplayerRight3;


        BufferedImage tankDownImg = null;
        BufferedImage backgroundSprite = null;
        BufferedImage backgroundTile = null;
        menu = new MainMenu();

        try {
            /*
             * There is a subtle difference between using class
             * loaders and File objects to read in resources for your
             * tank games. First, File objects when given to input readers
             * will use your project's working directory as the base path for
             * finding the file. Class loaders will use the class path of the project
             * as the base path for finding files. For Intellij, this will be in the out
             * folder. if you expand the out folder, the expand the production folder, you
             * will find a folder that has the same name as your project. This is the folder
             * where your class path points to by default. Resources, will need to be stored
             * in here for class loaders to load resources correctly.
             *
             * Also one important thing to keep in mind, Java input readers given File objects
             * cannot be used to access file in jar files. So when building your jar, if you want
             * all files to be stored inside the jar, you'll need to class loaders and not File
             * objects.
             *
             */
            //Using class loaders to read in resources
            mapFile = new MapInterpreter("worldmap.txt");
            mapFile.load();

            enemyFile = new EnemySpawner("enemymap.txt");
            enemyFile.load();

            menuImg = read(MainRoom.class.getClassLoader().getResource("ScoreScreen.png"));

            // player

            playerSpriteLeft = read(MainRoom.class.getClassLoader().getResource("Explorer_left.gif"));
            playerSpriteRight = read(MainRoom.class.getClassLoader().getResource("Explorer_right.gif"));
            playerSpriteUp = read(MainRoom.class.getClassLoader().getResource("Explorer_up.gif"));
            playerSpriteDown = read(MainRoom.class.getClassLoader().getResource("Explorer_down.gif"));
            animatedplayerDown = read(MainRoom.class.getClassLoader().getResource("PlayerDown1.png"));
            animatedplayerDown2 = read(MainRoom.class.getClassLoader().getResource("PlayerDown2.png"));
            animatedplayerDown3 = read(MainRoom.class.getClassLoader().getResource("PlayerDown3.png"));
            animatedplayerUp = read(MainRoom.class.getClassLoader().getResource("PlayerUp1.png"));
            animatedplayerUp2 = read(MainRoom.class.getClassLoader().getResource("PlayerUp2.png"));
            animatedplayerUp3 = read(MainRoom.class.getClassLoader().getResource("PlayerUp3.png"));
            animatedplayerRight = read(MainRoom.class.getClassLoader().getResource("PlayerRight1.png"));
            animatedplayerRight2 = read(MainRoom.class.getClassLoader().getResource("PlayerRight2.png"));
            animatedplayerRight3 = read(MainRoom.class.getClassLoader().getResource("PlayerRight3.png"));
            animatedplayerLeft = read(MainRoom.class.getClassLoader().getResource("PlayerLeft1.png"));
            animatedplayerLeft2 = read(MainRoom.class.getClassLoader().getResource("PlayerLeft2.png"));
            animatedplayerLeft3 = read(MainRoom.class.getClassLoader().getResource("PlayerLeft3.png"));

            Player.setDownwardFacingImage(playerSpriteDown);
            Player.setRightFacingImage(playerSpriteRight);
            Player.setLeftFacingImage(playerSpriteLeft);
            Player.setUpwardFacingImage(playerSpriteUp);

            // enemies

            beetleDown = read(MainRoom.class.getClassLoader().getResource("Beetle_down.gif"));
            beetleUp = read(MainRoom.class.getClassLoader().getResource("Beetle_up.gif"));

            Beetle.setBeetleAnimation(beetleUp, beetleDown);

            mummyDown = read(MainRoom.class.getClassLoader().getResource("Mummy_down.gif"));
            mummyUp = read(MainRoom.class.getClassLoader().getResource("Mummy_up.gif"));
            mummyLeft = read(MainRoom.class.getClassLoader().getResource("Mummy_left.gif"));
            mummyRight = read(MainRoom.class.getClassLoader().getResource("Mummy_right.gif"));

            mummyDown = read(MainRoom.class.getClassLoader().getResource("MummyDown1.png"));
            mummyDown2 = read(MainRoom.class.getClassLoader().getResource("MummyDown2.png"));
            mummyDown3  = read(MainRoom.class.getClassLoader().getResource("MummyDown3.png"));
            mummyUp = read(MainRoom.class.getClassLoader().getResource("MummyUp1.png"));
            mummyUp2 = read(MainRoom.class.getClassLoader().getResource("MummyUp2.png"));
            mummyUp3 = read(MainRoom.class.getClassLoader().getResource("MummyUp3.png"));
            mummyLeft = read(MainRoom.class.getClassLoader().getResource("MummyLeft1.png"));
            mummyLeft2 = read(MainRoom.class.getClassLoader().getResource("MummyLeft2.png"));
            mummyLeft3 = read(MainRoom.class.getClassLoader().getResource("MummyLeft3.png"));
            mummyRight = read(MainRoom.class.getClassLoader().getResource("MummyRight1.png"));
            mummyRight2 = read(MainRoom.class.getClassLoader().getResource("MummyRight2.png"));
            mummyRight3 = read(MainRoom.class.getClassLoader().getResource("MummyRight3.png"));


            Mummy.setMummyAnimation(mummyUp, mummyDown, mummyLeft, mummyRight);

            scorpionLeft = read(MainRoom.class.getClassLoader().getResource("Scorpion_left.gif"));
            scorpionRight = read(MainRoom.class.getClassLoader().getResource("Scorpion_right.gif"));

            Scorpion.setScorpionAnimation(scorpionLeft, scorpionRight);

            backgroundTile = read(MainRoom.class.getClassLoader().getResource("Background2.bmp"));
            menuImg = read(MainRoom.class.getClassLoader().getResource("MainMenu.png"));
            backgroundTile = read(MainRoom.class.getClassLoader().getResource("Background2.bmp"));
            breakableImg = read(MainRoom.class.getClassLoader().getResource("Wall2.gif"));
            startScreen = read(MainRoom.class.getClassLoader().getResource("Background1.bmp"));
            victoryScreen = read(MainRoom.class.getClassLoader().getResource("Congratulation.gif"));
            mainLogo = read(MainRoom.class.getClassLoader().getResource("Title.gif"));
            helpButton = read(MainRoom.class.getClassLoader().getResource("Button_help.gif"));
            loadButton = read(MainRoom.class.getClassLoader().getResource("Button_load.gif"));
            quitButton = read(MainRoom.class.getClassLoader().getResource("Button_quit.gif"));
            scoresButton = read(MainRoom.class.getClassLoader().getResource("Button_scores.gif"));
            startButton = read(MainRoom.class.getClassLoader().getResource("Button_start.gif"));


            lightImage = read(MainRoom.class.getClassLoader().getResource("Light.png"));
            lightImage2 = read(MainRoom.class.getClassLoader().getResource("LightActive.png"));
            Player.setLight(lightImage, lightImage2);

            // for panel

            panelImg = read(MainRoom.class.getClassLoader().getResource("Panel.gif"));
            livesImg = read(MainRoom.class.getClassLoader().getResource("Lives.gif"));

            // collectables

            pistolImg = read(MainRoom.class.getClassLoader().getResource("Pistol.gif"));
            swordImg = read(MainRoom.class.getClassLoader().getResource("Sword.gif"));
            scarabImg = read(MainRoom.class.getClassLoader().getResource("Scarab.gif"));
            ankhTreasureImg = read(MainRoom.class.getClassLoader().getResource("Treasure1.gif"));
            statueTreasureImg = read(MainRoom.class.getClassLoader().getResource("Treasure2.gif"));
            potionImg = read(MainRoom.class.getClassLoader().getResource("Potion.gif"));
            scrollImg = read(MainRoom.class.getClassLoader().getResource("Scroll.gif"));

            Collectable.setGunImg(pistolImg);
            Collectable.setScarabImg(scarabImg);
            Collectable.setAnkhImg(ankhTreasureImg);
            Collectable.setStatueImg(statueTreasureImg);
            Collectable.setPapyrusImg(scrollImg);
            Collectable.setSwordImg(swordImg);
            Collectable.setPotionImg(potionImg);

            // walls

            horizontalPushableImg = read(MainRoom.class.getClassLoader().getResource("Block_hor.gif"));
            verticalPushableImg = read(MainRoom.class.getClassLoader().getResource("Block_vert.gif"));
            specialPushableImg = read(MainRoom.class.getClassLoader().getResource("Door.gif"));
            unpushableOneImg = read(MainRoom.class.getClassLoader().getResource("Wall1.gif"));
            unpushableTwoImg = read(MainRoom.class.getClassLoader().getResource("Wall2.gif"));

            PushableWall.setHorizontalPushable(horizontalPushableImg);
            PushableWall.setVerticalPushable(verticalPushableImg);
            PushableWall.setSpecialPushable(specialPushableImg);
            UnpushableWall.setUnpushableUncracked(unpushableOneImg);
            UnpushableWall.setUnpushableCracked(unpushableTwoImg);
            BreakableWall.setBreakable(breakableImg);

            loadScreen = read(MainRoom.class.getClassLoader().getResource("LoadScreen.bmp")); // about game
            enemyImg = read(MainRoom.class.getClassLoader().getResource("EnemyScreen.png"));
            powerupImg = read(MainRoom.class.getClassLoader().getResource("PowerupScreen.png"));
            wallImg = read(MainRoom.class.getClassLoader().getResource("WallScreen.png"));
            victoryImg = read(MainRoom.class.getClassLoader().getResource("GameOverOne.png"));
            playerLostImg  = read(MainRoom.class.getClassLoader().getResource("GameOverTwo.png"));


            //Using file objects to read in resources.
            //tankImage = read(new File("tank1.png"));

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        playerCharacter = new Player(PLAYER_START_X, PLAYER_START_Y, 3, 3, 3,10, 50, playerSpriteLeft, playerSpriteRight, playerSpriteUp, playerSpriteDown);


        backgroundImage = new Background(300, 300, 0, 0, 0, backgroundSprite);
        playerScore = new GameScore(this, 525, 465, playerCharacter);

        Controller playerController = new Controller(playerCharacter, KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_A,
                KeyEvent.VK_D,
                KeyEvent.VK_ENTER,
                KeyEvent.VK_SHIFT,
                KeyEvent.VK_SPACE);

        mapObjects.add(playerCharacter);
        playerCharacter.setRoom(this);

        m = new Music(1, "Music.mid");
        this.jFrame.setLayout(new BorderLayout());
        this.jFrame.add(this);
        this.jFrame.addKeyListener(playerController);
        this.addMouseListener(new MouseReader());

       this.jFrame.setSize(MainRoom.SCREEN_WIDTH, MainRoom.SCREEN_HEIGHT+15);

        this.jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);

        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setVisible(true);

    }

    //The following method is borrowed from the gameWorld class of the Airstrike game, with modifications. It is not mine.

    public void drawBackGroundWithTileImage(Graphics2D g2) {
        int TileWidth = backgroundTile.getWidth(this);
        int TileHeight = backgroundTile.getHeight(this);
        int move = 1;

        int NumberX = (int) (WORLD_WIDTH / TileWidth);
        int NumberY = (int) (WORLD_HEIGHT / TileHeight);

        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g2.drawImage(backgroundTile, j * TileWidth,
                        i * TileHeight + (move % TileHeight), TileWidth,
                        TileHeight, this);
            }
        }
        move += 1;
    }

    public void launchGame(){

        for(int i = 0; i < playerCharacter.getShells().size(); i++){
            playerCharacter.getShells().get(i).fireBullet(WORLD_WIDTH, WORLD_HEIGHT);
            if((playerCharacter.getShells().get(i)).getShowing()) {
                playerCharacter.getShells().get(i).fireBullet(WORLD_WIDTH, WORLD_HEIGHT);
            }else{
                playerCharacter.getShells().remove(i);
            }
        }
    }

    public void drawScreen(Graphics2D g2){
        int playerXCoordinate = playerCharacter.getX();
        int playerYCoordinate = playerCharacter.getY();

        if (playerXCoordinate < SCREEN_WIDTH / 2) {
            playerXCoordinate = SCREEN_WIDTH / 2;
        }
        if (playerXCoordinate > WORLD_WIDTH - SCREEN_WIDTH / 2) {
            playerXCoordinate = WORLD_WIDTH - SCREEN_WIDTH / 2;
        }
        if (playerYCoordinate < SCREEN_HEIGHT / 2) {
            playerYCoordinate = SCREEN_HEIGHT / 2;
        }
        if (playerYCoordinate > WORLD_HEIGHT - SCREEN_HEIGHT / 2) {
            playerYCoordinate = WORLD_HEIGHT - SCREEN_HEIGHT / 2;
        }

        BufferedImage window = world.getSubimage(playerXCoordinate - SCREEN_WIDTH / 2, playerYCoordinate - SCREEN_HEIGHT / 2, SCREEN_WIDTH, SCREEN_HEIGHT);
        g2.drawImage(window, 0, 0, null);
    }

    public void drawBottomBar(Graphics2D g2) {
        g2.drawImage(MainRoom.panelImg, 0, SCREEN_HEIGHT-39, SCREEN_WIDTH, MainRoom.panelImg.getHeight(), null);
        for(int i = 0; i < Player.getLives(); i++){
            g2.drawImage(livesImg, 80 + i * (livesImg.getWidth() + 2), SCREEN_HEIGHT - 39, livesImg.getHeight(), (livesImg.getHeight() + 5), null);
        }

        for(int i = 0; i < Player.getNumScarabs(); i++){
            g2.drawImage(scarabImg, 240 + i * (scarabImg.getWidth() + 2), SCREEN_HEIGHT - 39, scarabImg.getHeight(), (scarabImg.getHeight() + 5), null);
        }

        playerScore.drawImage(g2, this);

        //   g2.drawImage(after, 500, 600, null);
    }


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);

        if (MainRoom.currentState == GameState.TITLE_STATE) {
            g.drawImage(menuImg, 0, 0, menuImg.getWidth(), menuImg.getHeight(), null);
        }if(MainRoom.currentState == GameState.MENU_STATE) {
            g.drawImage(enemyImg, 0, 0, enemyImg.getWidth(), enemyImg.getHeight(), null);
        }else if(MainRoom.currentState == GameState.POWERUP_STATE){
            g.drawImage(powerupImg, 0, 0, powerupImg.getWidth(), powerupImg.getHeight(), null);
        }else if(MainRoom.currentState == GameState.ABOUT_STATE){
            g.drawImage(loadScreen, 0, 0, powerupImg.getWidth(), powerupImg.getHeight(), null);
        }else if(MainRoom.currentState == GameState.WALL_STATE){
            g.drawImage(wallImg, 0, 0, wallImg.getWidth(), wallImg.getHeight(), null);
        }else if(MainRoom.currentState == GameState.MAIN_STATE) {
            drawBackGroundWithTileImage(buffer);
            launchGame();

            for (int i = 0; i < mapObjects.size(); i++) {
                mapObjects.get(i).drawImage(buffer);
            }

            this.playerCharacter.drawImage(buffer);
            drawScreen(g2);
            drawBottomBar(g2);

        }else if(MainRoom.currentState == GameState.VICTORY_STATE){


            FileWriter wr = null;
            try {
                wr = new FileWriter("scorelist.txt");
          //      wr.write(Player.getScore() +System.getProperty( "line.separator" ));
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            m1.play();
            g.drawImage(victoryImg, 0, 0, menuImg.getWidth(), menuImg.getHeight(), null);
            playerCharacter.setScore(0);
        }else if(MainRoom.currentState == GameState.LOSE_STATE){

            FileWriter wr = null;
            try {
                String s=String.valueOf(Player.getScore());
                wr = new FileWriter("scorelist.txt");
            //    wr.write(s +System.getProperty( "line.separator" ));
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            m1.play();
            g.drawImage(playerLostImg, 0, 0, menuImg.getWidth(), menuImg.getHeight(), null);
            playerCharacter.setScore(0);
        }else if(MainRoom.currentState == GameState.SCORE_STATE){
            g.drawImage(scoreImg, 0, 0, scoreImg.getWidth(), scoreImg.getHeight(), null);
        }
    }
}
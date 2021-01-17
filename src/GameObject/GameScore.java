
/**
 *
 * GameScore.java
 * Written by: Megan (Em) Powers
 *
 * Keeps track of and displays the game score.
 *
 */

package GameObject;

import java.awt.*;
import java.awt.image.ImageObserver;

public class GameScore implements ImageObserver {

    private MainRoom game;
    int x;
    int y;
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 25);
    Player playerCharacter;
    int currScore;

    public GameScore(MainRoom game, int x, int y, Player playerCharacter){
        this.game = game;
        this.x = x;
        this.y = y;
        this.playerCharacter = playerCharacter;
        this.currScore = playerCharacter.getScore();
    }

    public void drawImage(Graphics2D g, ImageObserver o){
        g.setFont(font);
       // g.drawString("SCORE: " + playerCharacter.getScore(), x, y);
        g.drawString(Integer.toString(playerCharacter.getScore()), x, y);
    }


    public void drawScore(Graphics2D g, ImageObserver o){
        g.setFont(font);
        // g.drawString("SCORE: " + playerCharacter.getScore(), x, y);
        g.drawString(Integer.toString(playerCharacter.getScore()), x, y);
    }


    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }

}

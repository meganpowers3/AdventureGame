package GameObject;

import javax.swing.*;
import java.awt.*;

public abstract class Enemy extends PlayerObject{
    int vx;
    int vy;
    private static ImageIcon baseImage;

    public abstract void switchMotionDirection();
    public abstract void update();
    public abstract void drawImage(Graphics2D g);
}

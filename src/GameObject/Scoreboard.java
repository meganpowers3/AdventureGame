
/**
 *
 * MapInterpreter.java
 * Written by: Megan (Em) Powers
 *
 * Acts to read in an external text file and utilize certain characters as markers for object placement.
 *
 */

package GameObject;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Scoreboard implements ImageObserver {
    int begin;
    Integer currentLetter;
    String mapFile;
    BufferedReader map;
    int width, height;

    int x;
    int y;
    Font font = new Font(Font.SANS_SERIF, Font.BOLD, 25);

    public Scoreboard(String mapFile){
        this.mapFile = mapFile;
        String currLine;
        try {
            map = new BufferedReader(new InputStreamReader(MainRoom.class.getClassLoader().getResource("scorelist.txt").openStream()));
            currLine = map.readLine();
            width = currLine.length();
            height = 0;

            while(currLine != null){
                height = height + 1;
                currLine = map.readLine();
            }
            map.close();
        } catch (IOException e) {
            System.out.println("Resource not found");
            e.printStackTrace();
        }
    }

    public void read(Object theObject) {

    }

    public void load(){
        MainRoom mainGame = MainRoom.getRoom();

        try {
            map = new BufferedReader(new InputStreamReader(MainRoom.class.getClassLoader().getResource("scorelist.txt").openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String currLine;

        try {
            currLine = map.readLine();
            width = currLine.length();

            while(currLine != null){
                for(int i = 0, j = currLine.length(); i < j; i++){
              //     String c = currLine.charAt(i);


                }
                currLine = map.readLine();
            }
            map.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}

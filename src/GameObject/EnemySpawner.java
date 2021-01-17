
/**
 *
 * EnemySpawner.java
 * Written by: Megan (Em) Powers
 *
 * Acts to read in an external text file and utilize certain characters as markers for object placement.
 *
 */

package GameObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EnemySpawner{
    int begin;
    Integer currentLetter;
    String mapFile;
    BufferedReader map;
    int width, height;

    public EnemySpawner(String mapFile){
        this.mapFile = mapFile;
        String currLine;
        try {
            map = new BufferedReader(new InputStreamReader(MainRoom.class.getClassLoader().getResource("enemymap.txt").openStream()));
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
            map = new BufferedReader(new InputStreamReader(MainRoom.class.getClassLoader().getResource("enemymap.txt").openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String currLine;

        try {
            currLine = map.readLine();
            width = currLine.length();
            height = -30;

            while(currLine != null){
                for(int i = 0, j = currLine.length(); i < j; i++){
                    char c = currLine.charAt(i);

                    // b is intact unpushable wall, l is cracked unpushable wall, h is horizontal pushable, v is vertical pushable, e is door
                    // u is breakable, h is gun, n is scarab, papyrus is +1 life (book of dead), s is sword of ra, c is potion

                    if(c == '1'){
                        Scorpion scorpion = new Scorpion( i*30, i*25, 1, false);
                        mainGame.addObjects(scorpion);
                    }
                    if(c == '2'){
                        Beetle beetle = new Beetle(i*30, i*25, 1, false);
                        mainGame.addObjects(beetle);
                    }
                    if(c == '3'){
                        Mummy mummy = new Mummy( i*60,  i*60, 1, 1, (i*30)-120, (i*30)-120,(i*30)+120, (i*30)+120);
                        mainGame.addObjects(mummy);
                    }

                }
                height = height + 30;
                currLine = map.readLine();
            }
            map.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

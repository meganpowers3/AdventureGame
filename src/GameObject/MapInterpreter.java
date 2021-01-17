
/**
 *
 * MapInterpreter.java
 * Written by: Megan (Em) Powers
 *
 * Acts to read in an external text file and utilize certain characters as markers for object placement.
 *
*/

package GameObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MapInterpreter{
    int begin;
    Integer currentLetter;
    String mapFile;
    BufferedReader map;
    int width, height;

    public MapInterpreter(String mapFile){
        this.mapFile = mapFile;
        String currLine;
        try {
            map = new BufferedReader(new InputStreamReader(MainRoom.class.getClassLoader().getResource("worldmap.txt").openStream()));
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
            map = new BufferedReader(new InputStreamReader(MainRoom.class.getClassLoader().getResource("worldmap.txt").openStream()));
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

                    if(c == 'b'){
                        UnpushableWall wall = new UnpushableWall(1, 1, i*30, height, true, false);
                        mainGame.addObjects(wall);
                    }
                    if(c == 'l'){
                        UnpushableWall wall = new UnpushableWall(1, 1, i*30, height, false, true);
                        mainGame.addObjects(wall);
                    }
                    if(c == 'h'){
                        PushableWall wall = new PushableWall(1, 1, i*30, height, true, false, false);
                        mainGame.addObjects(wall);
                    }

                    if(c == 'v'){
                        PushableWall wall = new PushableWall(1, 1, i*30, height, false, true, false);
                        mainGame.addObjects(wall);
                    }
                    if(c == 'e'){
                        PushableWall wall = new PushableWall(1, 1, i*30, height, false, false, true);
                        mainGame.addObjects(wall);
                    }
                    if(c == 'u'){
                        BreakableWall wall = new BreakableWall(1, 1, i*30, height);
                        mainGame.addObjects(wall);
                    }

                    if(c == 'g'){
                        Collectable pistol = new Collectable(i*30, height, true, false, false, false, false, false, false, true);
                        mainGame.addObjects(pistol);
                    }
                    if(c == 'n'){
                        Collectable scarab = new Collectable(i*30, height, false, true, false, false, false, false, false, true);
                        mainGame.addObjects(scarab);
                    }

                    if(c == 'm'){
                        Collectable ankh = new Collectable(i*30, height, false, false, true, false, false, false, false, true);
                        mainGame.addObjects(ankh);
                    }
                    if(c == 'r'){
                        Collectable statue = new Collectable(i*30, height, false, false, false, true, false, false, false,  true);
                        mainGame.addObjects(statue);
                    }
                    if(c == 'o'){
                        Collectable papyrus = new Collectable(i*30, height, false, false, false, false, true,false, false, true);
                        mainGame.addObjects(papyrus);
                    }
                    if(c == 's'){
                        Collectable sword = new Collectable(i*30, height, false, false, false, false, false,true, false, true);
                        mainGame.addObjects(sword);
                    }
                    if(c == 'c'){
                        Collectable potion = new Collectable(i*30, height, false, false, false, false, false,false, true, true);
                        mainGame.addObjects(potion);
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

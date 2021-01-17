
/**
 *
 * Music.java
 * Written by: @Airstrike
 * The following class is heavily borrowed from the SoundPlayer class of the Airstrike game, with modifications.
 *
 * Represents an audio player.
 *
 */

package GameObject;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Music {
    private AudioInputStream soundStream;
    private String soundFile;
    private Clip clip;
    private int category;

    public Music(int category, String soundFile){
        this.category = category;
        this.soundFile = soundFile;
        try {
            soundStream = AudioSystem.getAudioInputStream(Music.class.getClassLoader().getResource(soundFile));
            clip = AudioSystem.getClip();
            clip.open(soundStream);
        } catch (Exception e) {
            System.out.println("No audio files are found.");
            e.printStackTrace();
        }

        if(this.category == 1){
            Runnable runnableFile = new Runnable(){
                @Override
                public void run() {
                    while(true){
                        clip.start();
                        clip.loop(clip.LOOP_CONTINUOUSLY);
                        try{
                            Thread.sleep(10000);
                        }catch (InterruptedException e){
                            Logger.getLogger(Music.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                }
            };
            Thread t = new Thread(runnableFile);
            t.start();
        }
    }

    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

}

package game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPick {

    private static Clip musicClip;
    private static Clip soundClip;
    private static boolean first = true;

    public static void musicStart(String pick, String soundType) {
        try {
            if (!first && soundType == "music" ) {
                musicClip.stop();
            }
            if (soundType == "music"){
                File yourFile = new File(pick + ".wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(yourFile);
                musicClip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioIn.getFormat()));
                musicClip.open(audioIn);
                FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-30.0f); //ändrar volym
                musicClip.start();
                //ljudeffecter fuckar upp denna
                //clip.loop(Clip.LOOP_CONTINUOUSLY);
                first = false;
            }

            else if (soundType != "music") {
                File yourFile = new File(pick + ".wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(yourFile);
                soundClip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioIn.getFormat()));
                soundClip.open(audioIn);
                FloatControl gainControl = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-30.0f); //ändrar volym
                soundClip.start();
                //ljudeffecter fuckar upp denna
                //clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

        } catch (Exception e) {
            //whatevers
        }
    }
    public static void musicStop(){
        musicClip.stop();
    }
}







/*
package game;

        import javax.sound.sampled.*;
        import java.io.File;

public class MusicPick {

    private static Clip clip;
    private static int i = 0;

    public static void musicStart(String pick, String soundType) {
        try {
            if (i > 0 && soundType.equals("music")) {
                clip.stop();
            }
            File yourFile = new File(pick + ".wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(yourFile);
            clip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioIn.getFormat()));
            clip.open(audioIn);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-30.0f); //ändrar volym
            clip.start();
            //ljudeffecter fuckar upp denna
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            i = 5;

        } catch (Exception e) {
            //whatevers
        }
    }
    public static void musicStop() {
        clip.stop();
    }

*/

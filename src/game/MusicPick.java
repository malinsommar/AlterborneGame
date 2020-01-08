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
            if (!first && soundType.equals("music")) {
                musicClip.stop();
            }
            if (soundType.equals("music")){
                File yourFile = new File(pick + ".wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(yourFile);
                musicClip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioIn.getFormat()));
                musicClip.open(audioIn);
                FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-40.0f); //change volume
                musicClip.start();
                //clip.loop(Clip.LOOP_CONTINUOUSLY);
                first = false;
            }

            else {
                File yourFile = new File(pick + ".wav");
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(yourFile);
                soundClip = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, audioIn.getFormat()));
                soundClip.open(audioIn);
                FloatControl gainControl = (FloatControl) soundClip.getControl(FloatControl.Type.MASTER_GAIN);
                if (soundType.equals("lowvol")){ //used for sounds with low volume
                    gainControl.setValue(+10.0f);
                }
                else{gainControl.setValue(-30.0f);}
                soundClip.start();
            }

        } catch (Exception e) {
            //whatevers
        }
    }
    public static void musicStop(){
        musicClip.stop();
    }
}

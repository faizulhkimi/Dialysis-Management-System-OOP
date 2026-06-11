import javax.sound.sampled.*;
import java.io.File;

public class SoundPatientForm {

    private static Clip clip;

    public static void playLoop(String fileName) {

        try {

            AudioInputStream audio =
                AudioSystem.getAudioInputStream(
                    new File("audio/" +  "Relax.wav")
                );

            clip = AudioSystem.getClip();
            clip.open(audio);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop() {

        if(clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
import javax.sound.sampled.*;
import java.io.File;

public class SoundPlayer {

    private static Clip clip;

    public static void playLoop(String fileName) {
        try {
            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(new File("audio/" + fileName));

            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch (Exception e) {
            System.out.println("Audio not found: " + fileName);
        }
    }

    public static void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
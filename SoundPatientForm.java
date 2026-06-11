import java.io.File;
import javax.sound.sampled.*;

public class SoundPatientForm {

    private static Clip clip;
    private static Clip clickClip;

    public static void playLoop(String fileName) {

        try {

            if(clip != null && clip.isRunning()) {
                return;
            }

            AudioInputStream audio =
                AudioSystem.getAudioInputStream(
                    new File("Audio/" +  fileName)
                );

            clip = AudioSystem.getClip();
            clip.open(audio);

            FloatControl volume =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-20.0f);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void playClick() {

        try {

            if(clickClip == null) {
                AudioInputStream audio =
                    AudioSystem.getAudioInputStream(
                        new File("Audio/" + "click.wav")
                    );

                clickClip = AudioSystem.getClip();
                clickClip.open(audio);
            }

            if(clickClip.isRunning()) {
                clickClip.stop();
            }

            clickClip.setFramePosition(0);
            clickClip.start();

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

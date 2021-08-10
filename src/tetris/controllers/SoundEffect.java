package tetris.controllers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;

public class SoundEffect {

    private final String fileName;
    private Clip clip;
    private final boolean repeat;

    public SoundEffect(String fileName, boolean repeat) {
        this.fileName = fileName;
        this.repeat = repeat;
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.fileName).getAbsoluteFile());
            this.clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void play() {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.fileName).getAbsoluteFile());
            this.clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            this.clip.start();
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public void stop() {
        this.clip.stop();
    }

    public void resume() {
        if (this.repeat) {
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        this.clip.start();
    }

    public void restart() {
        this.play();
    }
}


package tetris.controllers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;

public class SoundEffect {

    private final String fileName;
    private String fileURL;

    public SoundEffect(String fileName) {
        this.fileName = fileName;
    }

//    public void setURL(String url) {
//        URL url = getClass().getClassLoader().getResource(url);
//        AudioClip sound = Applet.newAudioClip(url);
//        sound.play();
//
//    }

    public void play() {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.fileName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
//            AudioClip soundEffect = new AudioClip(this.fileName);
//            AudioClip.play();

        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

//    public void setRate(double rate) {
//        this.playSpeed = rate;
//        this.play(this.playSpeed);
//
//    }

}


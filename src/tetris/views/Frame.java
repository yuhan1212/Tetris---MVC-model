package tetris.views;
import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
    private final int frameWidth = 200;
    private final int frameHeight = 400;
    private final String gameName = "Rainbow Tetris";

    public Frame(){
        setSize(frameWidth, frameHeight);
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setTitle(gameName);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}

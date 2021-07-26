package tetris.views;
import tetris.controllers.KeyBoardHandler;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
    private final int frameWidth = 300;
    private final int frameHeight = 700;
    private final String gameName = "Rainbow Tetris";
    private Board board;
    private StatusAttributes statusAttributes;
    private JLabel statusBar;

    public Frame(KeyBoardHandler keyBoardHandler){
        setTitle(gameName);
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        board = new Board();
        board.addKeyListener(keyBoardHandler);
        statusBar = new JLabel();
        statusBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusAttributes = new StatusAttributes();
        String status = statusAttributes.fitLabel();
        statusBar.setText(status);

        add(board, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        setResizable(false);
    }

    public void updateJLabel() {
        String status = statusAttributes.fitLabel();
        statusBar.setText(status);

    }

    public void updateBoard(Color[][] colors) {

        board.paint(colors);

    }

    public void gameOver() {

    }

}

package tetris.views;
import tetris.controllers.KeyBoardHandler;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
    private final int frameWidth = 300;
    private final int frameHeight = 700;
    private final int StatusBarBoarder = 10;
    private final String gameName = "Tetris";
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
        statusBar.setBorder(BorderFactory.createEmptyBorder(StatusBarBoarder, StatusBarBoarder, StatusBarBoarder, StatusBarBoarder));
        statusAttributes = new StatusAttributes();
        String status = statusAttributes.fitLabel();
        statusBar.setText(status);

        add(board, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        setResizable(false);
    }

    public void update(boolean isGameOver,
                       boolean isPaused,
                       Color[][] colors,
                       double score,
                       int level,
                       int lines) {
        this.board.setGameOver(isGameOver);
        this.statusAttributes.setGameOver(isGameOver);
        this.board.setPaused(isPaused);
        this.statusAttributes.setPaused(isPaused);
        this.board.setColors(colors);
        this.statusAttributes.setRecords(score, level, lines);

        String status = statusAttributes.fitLabel();
        statusBar.setText(status);
        board.paint();
    }


}

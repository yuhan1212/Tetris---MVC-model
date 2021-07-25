package tetris.controllers;

import tetris.models.Piece;
import tetris.views.Board;
import tetris.views.StatusAttributes;

import java.awt.*;
import javax.swing.*;


/**
 * TetrisController will control the game board for Tetris.
 */
public class Controller {

    /**
     * Related to Tetris models
     */
    public Piece currentPiece;
    public Piece[] settledPieces;
    private Timer timer;

    /**
     * Related to Tetris views
     */
    private Frame frame = new tetris.views.Frame();
    private Board board = new Board();
    private StatusAttributes statusAttributes = new StatusAttributes();
    private JLabel statusBar;

    /**
     * Related to user input
     */
    private KeyBoardHandler keyBoardHandler;
    private boolean isFallingFinished = false;
    private boolean isPaused = true;



    /**
     * If we call Controller, the game will start (init board)
     */
    public Controller() {
        frame.setLayout(new BorderLayout());
        statusBar = new JLabel(statusAttributes.toString());
        frame.add(statusBar, BorderLayout.SOUTH);
        frame.add(board, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);


        keyBoardHandler = new KeyBoardHandler(this);
        timer = new Timer(400, keyBoardHandler);
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void pause() {
        isPaused = true;
        timer.stop();
        this.statusAttributes.updateStatusPaused(true);
        this.statusBar.setText(this.statusAttributes.toString());
        board.repaint();
    }

    public void start() {
        isPaused = false;
        timer.start();
        this.statusAttributes.updateStatusPaused(false);
        this.statusBar.setText(this.statusAttributes.toString());
        board.repaint();
    }

    public void gameAction() {
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }



    public void playGame() {
        if (isPaused()) return;


    }



}
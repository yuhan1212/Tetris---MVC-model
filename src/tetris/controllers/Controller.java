package tetris.controllers;

import tetris.models.Piece;
import tetris.views.Frame;
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
    private Frame frame;
    private Board board;
    private StatusAttributes statusAttributes;
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
        keyBoardHandler = new KeyBoardHandler(this);
        frame = new Frame(keyBoardHandler);
        timer = new Timer(400, board);

        Color[][] colors = new Color[20][10];
        for (int r = 0; r < 20; r++) {
            for (int c = 0; c < 10; c++) {
                if (r == c) colors[r][c] = Color.CYAN;
                else if (r - 2 == c) colors[r][c] = Color.BLUE;
                else colors[r][c] = Color.RED;
            }
        }
        frame.updateBoard(colors);
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
//        if (currentPiece.isFallingFinished) {
//            settledPieces.add(currentPiece);
//            settledPieces.removeFullLines();
//            currentPiece = new Piece();
//
//        } else {
//            currentPiece.oneLineDown();
//        }
    }
//
//
//
//    public void playGame() {
//        while (!isPaused() || !is) {
//            if (currentPiece != null) {
//                currentPiece.oneLineDown();
//            }
//        }
//    }

    public static void main(String[] args) {
        Controller c = new Controller();


    }


}
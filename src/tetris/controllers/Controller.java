package tetris.controllers;

import tetris.models.Piece;
import tetris.views.Frame;
//import tetris.views.Board;

import tetris.views.StatusAttributes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;


/**
 * TetrisController will control the game board for Tetris.
 */
public class Controller implements ActionListener {

    /**
     * Related to Tetris models
     */
    public Piece currentPiece;
    public Piece[] settledPieces;

    private Color[][] curBoard;
    private Color[][] curPiece;
    private boolean isGameOver = false;

    /**
     * Related to Tetris views
     */
    private final double LevelRate = 0.5;
    private final int scoreToLevel = 10;
    private Frame frame;
    private double score = 0;
    private int level = 1;
    private int removedLines = 0;

    /**
     * Related to user input
     */
    private KeyBoardHandler keyBoardHandler;
    private boolean isPaused = true;

    private Timer timer;



    /**
     * If we call Controller, the game will start (init board)
     */
    public Controller() {
        keyBoardHandler = new KeyBoardHandler(this);
        frame = new Frame(keyBoardHandler);
        timer = new Timer(400, this);
    }

    public void pause() {
        this.isPaused = true;
        timer.stop();
    }

    public void start() {
        this.isPaused = false;
        timer.start();
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public void updateView() {
        this.frame.update(this.isGameOver,
                this.isPaused,
                this.curBoard,
                this.score,
                this.level,
                this.removedLines);
    }

    public void updateRecord() {
        int AddRemoveLine = this.curBoard.countFullLine();
        this.removedLines += AddRemoveLine;
        this.score = AddRemoveLine * (1 + this.level * this.LevelRate);
        this.level = (int) this.score / this.scoreToLevel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameAction();
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
        c.updateView();
    }


}
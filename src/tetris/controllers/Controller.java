package tetris.controllers;

import tetris.models.Game;
import tetris.views.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * TetrisController will control the game board for Tetris.
 */
public class Controller implements ActionListener {

    /**
     * Related to Tetris models
     */
    private Game game;

    /**
     * Related to Tetris views
     */
    private Frame frame;
    // records
    private int score = 0;
    private int level = 1;
    private int removedLines = 0;
    private int levelExtra = 10;
    private final int oneLinePoints = 100;
    private final int upGrade = 400;

    /**
     * Related to sounds and time
     */
    private Boolean noSound = false;
    private SoundEffect BGM;
    private SoundEffect BGM10 = new SoundEffect("Tetris.wav", true);
    private SoundEffect BGM14 = new SoundEffect("Tetris14.wav", true);
    private SoundEffect BGM16 = new SoundEffect("Tetris16.wav", true);
    private SoundEffect removeSoundEffect = new SoundEffect("remove.wav", false);
    private SoundEffect gameOverSoundEffect = new SoundEffect("gameover.wav", false);
    private Timer timer;
    private int timeDelay;
    private int startTimeDelay = 1000;
    private int minTimeDelay = 200;

    /**
     * Related to user input
     */
    private KeyBoardHandler keyBoardHandler;
    private boolean isPaused;


    /**
     * If we call Controller, the game will start (init board)
     */
    public Controller() {
        keyBoardHandler = new KeyBoardHandler(this);
        frame = new Frame(keyBoardHandler);
        game = new Game();
        game.newPiece();
        timeDelay = startTimeDelay;
        timer = new Timer(timeDelay, this);
        timer.start();
        this.BGM = this.BGM10;
        this.isPaused = true;
    }

    public void pause() {
        this.isPaused = true;
        updateView();
        BGM.stop();
        timer.stop();
    }

    public void start() {
        this.isPaused = false;
        updateView();
        BGM.resume();
        timer.start();
    }

    public void replay() {
        timer.stop();
        score = 0;
        level = 1;
        removedLines = 0;
        timeDelay = startTimeDelay;
        timer.setDelay(timeDelay);
        game = new Game();
        game.newPiece();
        BGM.stop();
        BGM = BGM10;
        BGM.restart();
        isPaused = true;
        noSound = false;
        timer.start();
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public void updateView() {
                this.frame.update(this.game.isGameOver(),
                this.isPaused,
                this.game.getBoard(),
                this.game.getColor(),
                this.game.currentCoords(),
                this.score,
                this.level,
                this.removedLines);
    }

    public void updateRecord() {
        int addRemoveLine = this.game.countFullLines();
        this.removedLines += addRemoveLine;
        this.score += addRemoveLine * (this.oneLinePoints + (this.level -1) * levelExtra);
        this.level = 1 + this.score / this.upGrade; // level is total score / 300
        if (addRemoveLine > 0) {
            removeSoundEffect.play();
        }
    }

    private void updateBGM() {
        switch (this.timeDelay) {
            case 600:
                this.BGM.stop();
                this.BGM = BGM14;
                break;
            case 200:
                this.BGM.stop();
                this.BGM = BGM16;
        }
        this.BGM.play();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // We reach here every time the alarm fires.
        // if not gameOver and not isPaused, we need to call models
        if (!this.game.isGameOver() && !this.isPaused()) {
            // if current piece is settled
            if (this.game.isFallingFinished()) {
                this.updateRecord(); // remove full line and update records
            }
            else {
                // keep move current piece one line down
                this.move(Action.DOWNONE);
            }
        }

        // keep update view, records and timer
        updateView();

        int oldTimeDelay = this.timeDelay;
        timer.setDelay(updateTimeDelay());

        if (this.timeDelay < oldTimeDelay) {
            this.updateBGM();
        }

        if (this.game.isGameOver() && !noSound) {
            timer.stop();
            BGM.stop();
            gameOverSoundEffect.play();
            noSound = true;
        }
        System.out.println(timeDelay);
    }

    public void move(Action action) {
        if (action == Action.LEFT) {
            game.moveLeft();
        } else if (action == Action.RIGHT) {
            game.moveRight();
        } else if (action == Action.ROTATE) {
            game.rotateLeft();
        } else if (action == Action.DOWNONE) {
            game.dropOneLine();
        } else if (action == Action.ALLDOWN) {
            game.dropDown();
        }
        updateView();
    }

    public int updateTimeDelay() {
        if (this.timeDelay <= minTimeDelay) {
            return minTimeDelay;
        }
        timeDelay = startTimeDelay - score / upGrade * upGrade;
        return timeDelay;
    }

    public static void main(String[] args) {
        Controller c = new Controller();
    }


}
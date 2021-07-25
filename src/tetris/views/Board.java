package tetris.views;

import tetris.models.Piece;

import javax.swing.*;
import java.awt.*;

/**
 * TetrisBoard will setup a game board for Tetris.
 */

public class Board extends JPanel {

    /**
     * game board attributes.
     */
    private final int boardWidth = 10;
    private final int boardHeight = 22;
    private boolean isGameOver = false;
    private Piece currentPiece;
    private Piece[] otherPieces;

    public Board() {
        // init a board
    }

    public Board(boolean isGameOver, Piece currentPiece, Piece[] otherPieces) {
        this.paint(isGameOver, currentPiece, otherPieces);
    }

    public int getWidth() {
        return this.boardWidth;
    }

    public int getHeight() {
        return this.boardHeight;
    }

    public void paint(boolean isGameOver, Piece currentPiece, Piece[] otherPieces) {
        this.isGameOver = isGameOver;
        this.currentPiece = currentPiece;
        this.otherPieces = otherPieces;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // paint currentPiece
        // paint otherPieces
    }
}

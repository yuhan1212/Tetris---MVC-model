package tetris.views;

import tetris.controllers.Controller;
import tetris.models.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * TetrisBoard will setup a game board for Tetris.
 */

public class Board extends JPanel implements ActionListener {

    /**
     * game board attributes.
     */
    private final int boardWidth = 300;
    private final int boardHeight = 600;
    private boolean isGameOver = false;
    private Color[][] colors;
    private Piece currentPiece;
    private Piece[] otherPieces;
    private Controller controller;



    public Board() {
        // make the panel focusable so that it can react to keyboard inputs
        setFocusable(true);
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setLayout(new GridLayout(20, 10));
        setBackground(Color.BLACK);
    }

//    public Board(boolean isGameOver, Piece currentPiece, Piece[] otherPieces) {
//        this.paint(isGameOver, currentPiece, otherPieces);
//    }


    // paint() invokes paintComponent().
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.colors == null) {
            return;
        }
        if (this.isGameOver) {
            // just paint a "Game Over" string
            g.drawString("Game Over", 130, 300);
            System.out.println("fini");
        } else {
            for (int r = 0; r < 20; r++) {
                for (int c = 0; c < 10; c++) {

                    g.drawRect(r * 3, c * 30, 30, 30);
                    g.setColor(colors[r][c]);
                    g.fillRect(r * 3, c * 30, 30, 30);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(boardWidth, boardHeight); // appropriate constants
    }

    public void paint(Color[][] colors) {
        this.colors = colors;
        repaint();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.gameAction();
    }

}

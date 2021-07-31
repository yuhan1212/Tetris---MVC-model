package tetris.views;

import javax.swing.*;
import java.awt.*;

/**
 * TetrisBoard will setup a game board for Tetris.
 */

public class Board extends JPanel {

    /**
     * game board attributes.
     */
    private final int BOARDWIDTH = 300;
    private final int BOARDHEIGHT = 600;
    private final int ROWS = 20;
    private final int COLS = 10;
    private final int BoardBoarder = 10;
    private final int gridLength = 30;
    private final Color BOARDCOLOR = Color.BLACK;
    private boolean isGameOver = false;
    private boolean isPaused = true;
    private Color[] board;
    private Color pieceColor;
    private int[][] piecePos;




    public Board() {
        // make the panel focusable so that it can react to keyboard inputs
        setFocusable(true);
        setBorder(BorderFactory.createEmptyBorder(BoardBoarder, BoardBoarder, BoardBoarder, BoardBoarder));
        setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));
        setLayout(new GridLayout(ROWS, COLS));
        setBackground(BOARDCOLOR);
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public void setBoard(Color[] board) {
        this.board = board;
    }

    public void setPieceColor(Color color) {
        this.pieceColor = color;
    }

    public void setPiecePos(int[][] pos) {
        this.piecePos = pos;
    }

    public void paint() {
        repaint();
    }

    // paint() invokes paintComponent().
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (this.isGameOver) {
            // just paint a "Game Over" string
            g.setColor(Color.RED);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
            g.drawString("Game Over", 95, 300);
        } else if (this.isPaused) {
            g.setColor(Color.WHITE);
            g.drawString("Press 's' to start", 90, 300);
        } else if (board != null){

            // paint base board
            int i = 0;
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    g.setColor(board[i]);
                    g.fillRect(c * gridLength, r * gridLength, gridLength, gridLength);
                }
            }

            //p paint current piece
            for (i = 0; i < 4; i++) {
                g.setColor(this.pieceColor);
                int x = this.piecePos[i][0];
                int y = this.piecePos[i][1];
                g.fillRect(x * gridLength, y * gridLength, gridLength, gridLength);
            }

            // draw vertical lines
            for (i = this.gridLength; i < this.BOARDWIDTH; i += this.gridLength) {
                g.setColor(Color.GRAY);
                g.drawLine(i, 0, i, this.BOARDHEIGHT);
            }

            // draw horizontal lines
            for (i = this.gridLength; i < this.BOARDHEIGHT; i += this.gridLength) {
                g.setColor(Color.GRAY);
                g.drawLine(0, i, this.BOARDWIDTH, i);
            }
        }
    }


}

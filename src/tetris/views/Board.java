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
    private final Color BOARDCOLOR = Color.BLACK;
    private boolean isGameOver = false;
    private boolean isPaused = true;
    private Color[][] colors;



    public Board() {
        // make the panel focusable so that it can react to keyboard inputs
        setFocusable(true);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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

    public void setColors(Color[][] colors) {
        this.colors = colors;
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
        } else if (colors != null){
            for (int r = 0; r < ROWS; r++) {
                for (int c = 0; c < COLS; c++) {
                    g.setColor(colors[r][c]);
                    g.fillRect(c * 30, r * 30, 30, 30);
                }
            }
        }
    }


}

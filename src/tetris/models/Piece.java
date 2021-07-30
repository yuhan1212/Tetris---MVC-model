package tetris.models;

import java.awt.*;

public class Piece {

    public enum Shapes {
        NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape
    }

    private Shapes shape;
    private int[][] coords;
    private Color color;
    private final Color[] colors;
    private final int[][][] coordsTable;

    /**
     * This is the constructor that doesn't take in any parameter
     * and create a new non-shaped piece on top of the panel.
     */
    public Piece() {
        // a piece will take up maximum 4*2 rectangle
        coords = new int[4][2];
        colors = new Color[8];
        colors[0] = Color.BLACK;
        colors[1] = Color.RED;
        colors[2] = Color.ORANGE;
        colors[3] = Color.YELLOW;
        colors[4] = Color.GREEN;
        colors[5] = Color.BLUE;
        colors[6] = Color.CYAN;
        colors[7] = Color.MAGENTA;
        coordsTable = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}}, // NoShape
                {{0, -1}, {0, 0}, {1, 0}, {1, 1}}, // ZShape
                {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}}, // SShape
                {{0, -1}, {0, 0}, {0, 1}, {0, 2}}, // LineShape
                {{-1, 0}, {0, 0}, {1, 0}, {0, 1}}, // TShape
                {{0, 0}, {1, 0}, {0, 1}, {1, 1}}, // SquareShape
                {{1, -1}, {0, -1}, {0, 0}, {0, 1}}, // LShape
                {{-1, -1}, {0, -1}, {0, 0}, {0, 1}} // MirroredLShape
        };
        setShape(Shapes.NoShape);
    }

    /**
     * This methods set the shape of the non-shaped piece.
     */
    public void setShape(Shapes shape) {
        for (int i = 0; i < 4; i++) {
            System.arraycopy(coordsTable[shape.ordinal()][i], 0, coords[i], 0, 2);
        }

        this.shape = shape;
        this.color = colors[shape.ordinal()];
    }

    /**
     * This method sets the x coordinate of a given index.
     *
     * @param index where the x coordinate should be set
     * @param x     the x coordinate
     */
    private void setX(int index, int x) {
        coords[index][0] = x;
    }

    /**
     * This method sets the y coordinate of a given index.
     *
     * @param index where the y coordinate should be set
     * @param y     the y coordinate
     */
    private void setY(int index, int y) {
        coords[index][1] = y;
    }

    /**
     * This method gets the x coordinate of the given index.
     *
     * @param index where to find the x coordinate
     * @return the x coordinate
     */
    public int getX(int index) {
        return coords[index][0];
    }

    /**
     * This method gets the y coordinate of the given index.
     *
     * @param index where to find the y coordinate
     * @return the y coordinate
     */
    public int getY(int index) {
        return coords[index][1];
    }

    /**
     * This method tells the shape of the current piece.
     *
     * @return the shape (enum)
     */
    public Shapes getShape() {
        return shape;
    }

    /**
     * This method picks a random shape for the new piece created.
     */
    void setRandomShape() {
        int max = Shapes.values().length - 1;
        int pick = (int) Math.round(Math.random() * max);
        setShape(Shapes.values()[pick]);
    }

    /**
     * This method gets smallest x coordinate of the piece.
     *
     * @return the smallest x coordinate
     */
    public int minX() {
        int min = coords[0][0];

        for (int i = 0; i < 4; i++) {
            min = Math.min(min, coords[i][0]);
        }

        return min;
    }

    /**
     * This method gets smallest y coordinate of the piece.
     *
     * @return the smallest y coordinate
     */
    public int minY() {
        int min = coords[0][1];

        for (int i = 0; i < 4; i++) {
            min = Math.min(min, coords[i][1]);
        }

        return min;
    }

    /**
     * This method rotates the piece left 90 degrees.
     *
     * @return a new piece that is rotated left
     */
    public Piece rotateLeft() {
        // No need to rotate square shape
        if (shape == Shapes.SquareShape) {
            return this;
        }

        Piece result = new Piece();

        for (int i = 0; i < 4; ++i) {
            result.setX(i, getY(i));
            result.setY(i, -(getX(i)));
        }
        return result;
    }

    public static void main(String[] args) {
        Piece piece = new Piece();
    }
}

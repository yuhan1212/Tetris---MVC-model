package tetris.models;

import java.awt.*;
import java.util.Random;

public class Piece {

    public enum Shapes {
        NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape
    }

    private Shapes shape;
    private int lastUsed = 0;
    private int[][] coords;
    private Color color;
    private final Color[] colors;
    private final int[][][] coordsTable;

    /**
     * This is the constructor that doesn't take in any parameter
     * and create a new non-shaped piece on top of the panel.
     */
    public Piece() {
        // a piece will have 4 coordinates, one for each square
        coords = new int[4][2];
        colors = new Color[8];
        colors[0] = Color.BLACK;
        colors[1] = Color.RED;
        colors[2] = Color.ORANGE;
        colors[3] = Color.YELLOW;
        colors[4] = Color.GREEN;
        colors[5] = Color.BLUE;
        colors[6] = Color.CYAN;
        colors[7] = new Color(102, 0 , 153);
        coordsTable = new int[][][]{
                {{0, 0}, {0, 0}, {0, 0}, {0, 0}}, // NoShape
                {{-1, 1}, {0, 1}, {0, 0}, {1, 0}}, // ZShape
                {{-1, 0}, {0, 0}, {0, 1}, {1, 1}}, // SShape
                {{-2, 0}, {-1, 0}, {0, 0}, {1, 0}}, // LineShape
                {{0, 1}, {-1, 0}, {0, 0}, {1, 0}}, // TShape
                {{-1, 1}, {-1, 0}, {0, 1}, {0, 0}}, // SquareShape
                {{-1, 0}, {0, 0}, {1, 0}, {1, 1}}, // LShape
                {{-1, 1}, {-1, 0}, {0, 0}, {1, 0}} // MirroredLShape
        };
        setShape(Shapes.NoShape);
    }

    /**
     * This method sets the shape of the non-shaped piece.
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
    public void setX(int index, int x) {
        coords[index][0] = x;
    }

    /**
     * This method sets the y coordinate of a given index.
     *
     * @param index where the y coordinate should be set
     * @param y     the y coordinate
     */
    public void setY(int index, int y) {
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
     * This method gives the color of the current piece.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * This method picks a random shape for the current piece.
     */
    void setRandomShape() {
        Random r = new Random();
        Shapes[] values = Shapes.values();
        int x = r.nextInt(values.length - 1) + 1;
        while (x == lastUsed) {
            x = r.nextInt(values.length - 1) + 1;
        }
        lastUsed = x;

        setShape(values[x]);
    }

    public String toString() {
        String res = "";
        res += this.shape.toString();
        res += this.color.toString();
        return res;
    }

}

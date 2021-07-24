package tetris.models;

public class Piece {

    public enum Shapes {
        NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape
    }

    private Shapes shape;
    private int[][] coords;

    /**
     * This is the constructor that doesn't take in any parameter
     * and create a new non-shaped piece on top of the panel.
     */
    public Piece() {
    }

    /**
     * This method sets the coordinate to x at the given index.
     *
     * @param index where the x should be set
     * @param x the x coordinate
     */
    private void setX(int index, int x) {

    }

    /**
     * This method sets the coordinate to y at the given index.
     *
     * @param index where the y should be set
     * @param y the y coordinate
     */
    private void setY(int index, int y) {

    }

    /**
     * This method gets the x coordinate of the given index.
     *
     * @param index where to find the x coordinate
     * @return the x coordinate
     */
    private int getX(int index) {

    }

    /**
     * This method gets the y coordinate of the given index.
     *
     * @param index where to find the y coordinate
     * @return the y coordinate
     */
    private int getY(int index) {

    }

    /**
     * This method gets the current tetrominoe piece that is in action.
     *
     * @return the current tetrominoe piece
     */
    private Piece getPiece() {
    }

    /**
     * This method picks a random shape for the new piece created.
     */
    void setRandomShape() {
    }

    /**
     * This method gets smallest x coordinate of the piece.
     *
     * @return the smallest x coordinate
     */
    private int minX() {

    }

    /**
     * This method gets smallest y coordinate of the piece.
     *
     * @return the smallest y coordinate
     */
    private int minY() {

    }

    /**
     * This method rotates the piece left 90 degrees.
     *
     * @return a new piece that is rotated left
     */
    private Piece rotateLeft() {

    }


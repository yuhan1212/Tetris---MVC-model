package tetris.models;

public class Piece {

    public enum Shapes {
        NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape
    }

    private Shapes shape;
    private int[][] coords;
    private int[][][] coordsTable;

    /**
     * This is the constructor that doesn't take in any parameter
     * and create a new non-shaped piece on top of the panel.
     */
    public Piece() {
        // a piece will take up maximum 4*2 rectangle
        coords = new int[4][2];
        int[][][] coordsTable = new int[][][]{
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
    private void setShape(Shapes shape) {
        for (int i = 0; i < 4; i++) {
            System.arraycopy(coordsTable[shape.ordinal()], 0, coords, 0, 4);
        }
        this.shape = shape;
    }

    /**
     * This method sets the coordinate to x at the given index.
     *
     * @param index where the x should be set
     * @param x     the x coordinate
     */
    private void setX(int index, int x) {
        coords[index][0] = x;
    }

    /**
     * This method sets the coordinate to y at the given index.
     *
     * @param index where the y should be set
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
    private int getX(int index) {
        return coords[index][0];
    }

    /**
     * This method gets the y coordinate of the given index.
     *
     * @param index where to find the y coordinate
     * @return the y coordinate
     */
    private int getY(int index) {
        return coords[index][1];
    }

    /**
     * This method tells the shape of the current piece.
     *
     * @return the shape (enum)
     */
    private Shapes getShape() {
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
    private int minX() {
        int min = coords[0][0];

        for (int i=0; i<4; i++) {
            min = Math.min(min, coords[i][0]);
        }

        return min;
    }

    /**
     * This method gets smallest y coordinate of the piece.
     *
     * @return the smallest y coordinate
     */
    private int minY() {
        int min = coords[0][1];

        for (int i=0; i<4; i++) {
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


    // Waiting for discussing (removeFullLines / tryMove / moveLeft / moveRight / rotateLeft / rotateRight)

    private void removeFullLines() {
        int numFullLines = 0;

        // TODO: 叫views or controller -> 需要boardHeight及boardWidth
        for (int i = views.getBoardHeight() - 1; i >= 0; i--) {
            boolean lineFull = true;

            for (int j = 0; j < views.getBoardWidth(); j++) {
                if (shapeAt(j, i) == Shapes.NoShape) {
                    lineFull = false;
                    break;
                }
            }

            if (lineFull) {
                numFullLines++;
                for (int k = i; k < views.getBoardHeight - 1; k++) {
                    for (int j = 0; j < views.getBoardWidth; j++) {
                        board[(k * views.getBoardWidth) + j] = shapeAt(j, k + 1);
                    }
                }
            }
        }

        // TODO: 這些methods都待定
        if (numFullLines > 0) {
            numLinesRemoved += numFullLines;
            statusbar.setText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            curPiece.setShape(Shapes.NoShape);
        }
    }

    private boolean tryMove(Piece newPiece, int newX, int newY) {
        return true;
    }

    public Piece moveLeft() {
        //tryMove(currentPiece, currentX - 1, currentY);
    }
    public Piece moveRight() {
        //tryMove(currentPiece, currentX + 1, currentY);
    }

    public Piece rotateRight() {
        //tryMove(currentPiece.rotateRight(), currentX, currentY);
    }

    public Piece dropDown() {
        //tryMove(currentPiece.rotateRight(), currentX, currentY);
    }

    public Piece oneLineDown() {
        //tryMove(currentPiece.rotateRight(), currentX, currentY);
    }
}


package tetris.models;
import java.awt.*;


public class Game {

    private final int boardWidth = 10;
    private final int boardHeight = 20;
    private boolean isFallingFinished = false;
    private boolean isGameOver = false;

    private int currentX = 0;
    private int currentY = 0;
    private Piece currentPiece;
    private Color[] board;


    /**
     * This is the constructor of the game logic, which takes no parameter
     */
    public Game() {
        currentPiece = new Piece();
        board = new Color[boardWidth*boardHeight];
        clearBoard();
    }

    /**
     * This method tells whether the piece can be moved to the provided coordinate.
     *
     * @param newPiece the new piece that will be created if the move is possible
     * @param newX the x coordinate of the destination
     * @param newY the y coordinate of the destination
     * @return true if the piece can be moved, otherwise false
     */
    public boolean tryMove(Piece newPiece, int newX, int newY) {
        for (int i = 0; i < 4; i++) {
            int x = newX + newPiece.getX(i);
            int y = newY - newPiece.getY(i);

            // if the piece is going to fall out of the board range
            if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight) {
                return false;
            }

            // if there is already other pieces at the new position
            if (colorAt(x, y) != Color.BLACK) {
                return false;
            }
        }

        currentPiece = newPiece;
        currentX = newX;
        currentY = newY;

        return true;
    }

    /**
     * This method rotates the piece left 90 degrees.
     *
     */
    public void rotateLeft() {
        // No need to rotate square shape
        if (currentPiece.getShape() != Piece.Shapes.SquareShape) {
            Piece result = new Piece();

            for (int i = 0; i < 4; ++i) {
                result.setX(i, currentPiece.getY(i));
                result.setY(i, -(currentPiece.getX(i)));
            }
            currentPiece = result;
        }
    }

    /**
     * This method continues drops the piece to the bottom.
     */
    public void dropDown() {
        int newY = currentY;
        while (newY < boardHeight) {
            // Y continues to decrement by 1 while possible
            if (!tryMove(currentPiece, currentX, newY + 1)) {
                break;
            }
            newY++;
        }
        pieceDropped();
    }

    /**
     * This method drops the piece by one line when being called.
     */
    public void dropOneLine() {
        // if the  piece can't drop, it's reached the bottom/other pieces
        if (!tryMove(currentPiece, currentX, currentY + 1)) {
            pieceDropped();
        }
    }

    /**
     * This method adds the current piece to the board and remove full lines.
     */
    private void pieceDropped() {
        // adding the current piece to the board
        for (int i = 0; i < 4; i++) {
            int x = currentX + currentPiece.getX(i);
            int y = currentY - currentPiece.getY(i);
            board[(y * boardWidth) + x] = currentPiece.getColor();
        }

        removeFullLines();

//        // TODO: 由controller叫？
//        if (!isFallingFinished) {
//            newPiece();
//        }
    }

    public int[][] currentPos() {
        int[][] res = new int[4][2];
        for (int i = 0; i < 4; i++) {
            int x = currentX + currentPiece.getX(i);
            int y = currentY - currentPiece.getY(i);
            res[i][0] = x;
            res[i][1] = y;
        }
        return res;
    }

    public int removeFullLines() {
        int numFullLines = 0;

        for (int i = boardHeight - 1; i >= 0; i--) {
            boolean lineFull = true;

            // if there is a non-shape piece within the line, then the line isn't full
            for (int j = 0; j < boardWidth; j++) {
                if (colorAt(j, i) == Color.BLACK) {
                    lineFull = false;
                    break;
                }
            }

            if (lineFull) {
                numFullLines++;

                // moving all the lines above the full line one line down
                for (int k = i; k < boardHeight - 1; k++) {
                    for (int j = 0; j < boardWidth; j++) {
                        board[(k * boardWidth) + j] = colorAt(j, k + 1);
                    }
                }
            }
        }

//        // TODO: 參透
//        if (numFullLines > 0) {
            isFallingFinished = true;
//            currentPiece.setShape(Piece.Shapes.NoShape);
//        }

        return numFullLines;
    }

    /**
     * This method clears the board, setting all shapes to noShape.
     */
    private void clearBoard() {
        for (int i = 0; i < boardHeight * boardWidth; i++) {
            board[i] = Color.BLACK;
        }
    }

    public Color getColor() {
        return currentPiece.getColor();
    }

    /**
     * This method updates the current piece and set it at top of the board.
     */
    public void newPiece() {
        currentX = boardWidth / 2 + 1;
        currentY = currentPiece.minY();
        currentPiece.setRandomShape();
        isFallingFinished = false;

        // cannot move anymore, game over
        if (!tryMove(currentPiece, currentX, currentY)) {
            isGameOver = true;
        }
    }


    /**
     * This method moves the current piece to the left by 1 if possible.
     */
    public void moveLeft() {
        tryMove(currentPiece, currentX - 1, currentY);
    }

    /**
     * This method moves the current piece to the right by 1 if possible.
     */
    public void moveRight() {
        tryMove(currentPiece, currentX + 1, currentY);
    }

    /**
     * This method returns the shape of the piece at the given coordinate.
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @return The shape of the tetris piece
     */
    private Color colorAt(int x, int y) {
        return board[(y*boardWidth) + x];
    }

    /**
     * This method returns the current status of game board
     */
    public Color[] getBoard() {
        return board;
    }

    /**
     * This method tells whether the game is over.
     *
     * @return true if the game is over, otherwise false
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    @Override
    public String toString() {
        String result = "";

        for (int i=0; i<(boardWidth * boardHeight); i++) {
            Color c = board[i];
            result += c.toString();
            if ((i +1) % 10 == 0) {
                result += "\n";
            }
        }
        return result;
    }

    public boolean isFallingFinished() {
        return isFallingFinished;
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.newPiece();
        game.currentPiece.setRandomShape();
        game.dropDown();
        System.out.println(game.currentPiece);
        System.out.println(game);
    }
}

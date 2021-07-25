package tetris.views;

public class StatusAttributes {

    private int score;
    private double level;
    private int removedLines;
    private boolean isPaused = false;

    public StatusAttributes() {
        score = 0;
        level = 1;
        removedLines = 0;
    }

    public StatusAttributes(int score, double level, int removedLines) {
        score = score;
        level = level;
        removedLines = removedLines;
    }

    public void updateStatusText(int lines) {
        score += lines * (1 + level * 0.5);
    }

    public void updateStatusPaused(boolean b) {
        isPaused = b;
    }

    public double getScore() {
        return this.score;
    }

    public String toString() {
        String res = "";
        res += "Tetris Status: ";
        if (isPaused) {
            res += "Paused\n";
        } else { res += "Ongoing\n";
        }
        res += String.format("Score: %f\n", score);
        res += String.format("Level: %d\n", level);
        res += String.format("Lined: %d", removedLines);

        return res;
    }

    public String gameResult() {
        String res = "";
        res += String.format("Score: %f\n", score);
        res += String.format("Level: %d\n", level);
        res += String.format("Lined: %d", removedLines);

        return res;
    }
}

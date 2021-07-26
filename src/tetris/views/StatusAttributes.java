package tetris.views;

public class StatusAttributes {

    private double score;
    private int level;
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

    public String fitLabel() {
        String res = "";
        res += "<html>Status: ";
        if (isPaused) {
            res += "Paused, press 's' to start<br />";
        } else { res += "<html>Started, press 'p' to pause<br />";
        }
        res += String.format("Score: %.2f<br />", score);
        res += String.format("Level: %d<br />", level);
        res += String.format("Lines: %d</html>", removedLines);

        return res;
    }

    public String gameResult() {
        String res = "";
        res += String.format("<html>Score: %f<br />", score);
        res += String.format("Level: %d<br />", level);
        res += String.format("Lined: %d</html>", removedLines);

        return res;
    }

}

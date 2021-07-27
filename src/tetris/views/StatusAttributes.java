package tetris.views;

public class StatusAttributes {

    private double score;
    private int level;
    private int removedLines;
    private boolean isPaused = true;
    private boolean isGameOver = false;

    public StatusAttributes() {
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public void setRecords(double score, int level, int lines) {
        this.score = score;
        this.level = level;
        this.removedLines = lines;
    }

    public String fitLabel() {
        String res = "";
        if (this.isGameOver) {
            res += String.format("<html>Score: %.2f<br />", this.score);
            res += String.format("Level: %d<br />", this.level);
            res += String.format("Lined: %d</html>", this.removedLines);
            res += "Press 's' to start a new game.";
            return res;
        }

        res += String.format("<html>Score: %.2f<br />", this.score);
        res += String.format("Level: %d<br />", this.level);
        res += String.format("Lines: %d<br />", this.removedLines);

        res += "Status: ";
        if (this.isPaused) {
            res += "Paused</html>";
        } else { res += "<html>Started, press 'p' to pause</html>";
        }

        return res;
    }

}

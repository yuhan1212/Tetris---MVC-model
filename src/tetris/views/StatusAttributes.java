package tetris.views;

public class StatusAttributes {

    private int score;
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

    public void setRecords(int score, int level, int lines) {
        this.score = score;
        this.level = level;
        this.removedLines = lines;
    }

    public String fitLabel() {
        String res = "";
        if (this.isGameOver) {
            res += String.format("<html>Score:   %d<br />", this.score);
            res += String.format("Level:   %d<br />", this.level);
            res += String.format("Lined:   %d<br />", this.removedLines);
            res += "Press <b>R</b> to start a new game.</html>";
            return res;
        }

        res += String.format("<html>Score:   %d<br />", this.score);
        res += String.format("Level:   %d<br />", this.level);
        res += String.format("Lines:   %d<br />", this.removedLines);

        res += "Status: ";
        if (this.isPaused) {
            res += "<html>PAUSE <br />Press <b>P</b> to pause, <b>R</b> to replay</html>";
        } else { res += "<html>START <br />Press <b>P</b> to pause, <b>R</b> to replay</html>";
        }

        return res;
    }

}

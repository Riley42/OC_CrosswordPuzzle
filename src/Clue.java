public class Clue
{
    private int row, col, number;
    private char orientation;
    private String clue, realAnswer, submittedAnswer;

    public Clue(int row, int col, int number, char orientation, String clue, String realAnswer)
    {
        this.row = row;
        this.col = col;
        this.number = number;
        this.orientation = orientation;
        this.clue = clue;
        this.realAnswer = realAnswer;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public char getOrientation() {
        return orientation;
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
    }

    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public String getRealAnswer() {
        return realAnswer;
    }

    public void setRealAnswer(String realAnswer) {
        this.realAnswer = realAnswer;
    }

    public String getSubmittedAnswer() {
        return submittedAnswer;
    }

    public void setSubmittedAnswer(String submittedAnswer) {
        this.submittedAnswer = submittedAnswer;
    }

    @Override
    public String toString() {
        return "Clue{" +
                "row=" + row +
                ", col=" + col +
                ", number=" + number +
                ", orientation=" + orientation +
                ", clue='" + clue + '\'' +
                ", realAnswer='" + realAnswer + '\'' +
                ", submittedAnswer='" + submittedAnswer + '\'' +
                '}';
    }
}

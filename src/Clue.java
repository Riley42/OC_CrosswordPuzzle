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
    public int getCol() {
        return col;
    }
    public int getNumber() {
        return number;
    }
    public char getOrientation() {
        return orientation;
    }
    public String getClue() {
        return clue;
    }
    public String getRealAnswer() {
        return realAnswer;
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

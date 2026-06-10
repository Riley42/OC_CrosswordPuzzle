public class Square
{
    public static final char BLACK_SQUARE  = '\u2588';
    public static final char EMPTY_SQUARE  = '.';
    private char content;
    private int clueNumber;

    public Square()
    {
        this.content    = EMPTY_SQUARE;
        this.clueNumber = 0;
    }
    public Square(char content)
    {
        this.content    = content;
        this.clueNumber = 0;
    }
    public Square(char content, int clueNumber)
    {
        this.content    = content;
        this.clueNumber = clueNumber;
    }

    public char getContent()
    {
        return content;
    }
    public void setContent(char content)
    {
        this.content = content;
    }
    public boolean isBlackSquare()
    {
        return content == BLACK_SQUARE;
    }
    public boolean isEmptySquare()
    {
        return content == EMPTY_SQUARE;
    }
    public boolean isNumberedSquare()
    {
        return clueNumber > 0;
    }
    public boolean hasLetter()
    {
        return Character.isLetter(content);
    }

    @Override
    public String toString()
    {
        return String.valueOf(content);
    }
}

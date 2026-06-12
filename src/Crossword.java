import java.util.ArrayList;

public class Crossword
{
    private Square[][] board;
    private ArrayList<Clue> clues;
    private int rows;
    private int cols;

    public Crossword(ArrayList<Clue> clues, int rows, int cols)
    {
        this.clues = clues;
        this.rows  = rows;
        this.cols  = cols;

        buildBoard();
    }

    private void buildBoard()
    {
        board = new Square[rows][cols];

        // Fill every square as black to start
        for (int row = 0; row < rows; row++)
            for (int col = 0; col < cols; col++)
                board[row][col] = new Square(Square.BLACK_SQUARE);

        // Fill possible answer squares
        for (Clue clue : clues)
        {
            int clueRow      = clue.getRow();
            int clueCol      = clue.getCol();
            char orientation = clue.getOrientation();
            String answer    = clue.getRealAnswer();
            int number       = clue.getNumber();

            for (int i = 0; i < answer.length(); i++)
            {
                int currentRow = (orientation == 'v') ? clueRow + i : clueRow;
                int currentCol = (orientation == 'h') ? clueCol + i : clueCol;

                // Only set clue number on the first square if not already numbered
                if (i == 0 && !board[currentRow][currentCol].isNumberedSquare())
                {
                    board[currentRow][currentCol] = new Square(Character.forDigit(number, 10), number);
                }
                else if (board[currentRow][currentCol].isBlackSquare())
                {
                    board[currentRow][currentCol] = new Square(Square.EMPTY_SQUARE);
                }
            }
        }
    }
    public void displayBoard()
    {
        for (int row = 0; row < rows; row++)
        {
            for (int col = 0; col < cols; col++)
            {
                System.out.printf("%4s", board[row][col].toString()); // string at least 4 characters long
            }
            System.out.println();
        }
    }

    public void trySetAnswer(Clue clue)
    {
        int startingRow = clue.getRow();
        int startingCol = clue.getCol();
        boolean isHorizontal = clue.getOrientation() == 'h';

        for (int i = 0; i < clue.getRealAnswer().length(); i++)
        {
            int row = startingRow + (isHorizontal ? 0 : i);
            int col = startingCol + (isHorizontal ? i : 0);
            board[row][col].setContent(clue.getSubmittedAnswer().charAt(i));
        }
    }
    public void eraseAnswer(Clue clue)
    {
        int startingRow = clue.getRow();
        int startingCol = clue.getCol();
        boolean isHorizontal = clue.getOrientation() == 'h';

        for (int i = 0; i < clue.getRealAnswer().length(); i++)
        {
            int row = startingRow + (isHorizontal ? 0 : i);
            int col = startingCol + (isHorizontal ? i : 0);

            boolean hasIntersection;

            if (isHorizontal)
            {
                hasIntersection = (row > 0 && Character.isLetter(board[row - 1][col].getContent())) ||
                                  (row < board.length - 1 && Character.isLetter(board[row + 1][col].getContent()));
            }
            else
            {
                hasIntersection = (col > 0 && Character.isLetter(board[row][col - 1].getContent())) ||
                                  (col < board[0].length - 1 && Character.isLetter(board[row][col + 1].getContent()));
            }

            if (!hasIntersection)
            {
                char content = (i == 0) ? Character.forDigit(clue.getNumber(), 10) : Square.EMPTY_SQUARE;
                board[row][col].setContent(content);
            }
        }
    }
    public boolean checkPuzzle()
    {
        for (Clue clue : clues)
        {
            if (clue.getSubmittedAnswer() == null ||
               !clue.getSubmittedAnswer().equals(clue.getRealAnswer()))
            {
                return false;
            }
        }
        return true;
    }
}

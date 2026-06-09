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

        // Carve out the answer squares using each clue
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
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < cols; c++)
            {
                System.out.printf("%4s", board[r][c].toString());
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

    public Square[][] getBoard()
    {
        return board;
    }

    public ArrayList<Clue> getClues()
    {
        return clues;
    }

    public int getRows()
    {
        return rows;
    }

    public int getCols()
    {
        return cols;
    }
}

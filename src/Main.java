import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);
        ArrayList<Clue> clues = FileUtils.loadAllClues();
        Crossword crossword = new Crossword(clues, 15, 15);

        // Test
        //fillAllAnswers(crossword, clues);

        crossword.displayBoard();

        while (true)
        {
            System.out.println("========================");
            int selection = getMenuSelection(userInput);
            boolean puzzleSuccess = false;
            switch (selection)
            {
                case 1:
                    displayAllClues(clues);
                    break;
                case 2:
                    displaySpecificClue(userInput, clues);
                    break;
                case 3:
                    answerAClue(userInput, clues, crossword);
                    break;
                case 4:
                    eraseAnAnswer(userInput, clues, crossword);
                    break;
                case 5:
                    puzzleSuccess = submitPuzzle(crossword);
                    break;
                case 6:
                    System.exit(0);
                    break;
            }

            if (puzzleSuccess)
                break;
        }
    }

    private static void displayAllClues(ArrayList<Clue> clues)
    {
        System.out.println("Horizontal");
        System.out.println("----------");

        for (Clue clue : clues)
        {
            if (clue.getOrientation() == 'h')
                System.out.println(clue.getNumber() + ". " + clue.getClue());
        }

        System.out.println();

        System.out.println("Vertical");
        System.out.println("--------");

        for (Clue clue : clues)
        {
            if (clue.getOrientation() == 'v')
                System.out.println(clue.getNumber() + ". " + clue.getClue());
        }
    }
    private static void displaySpecificClue(Scanner userInput, ArrayList<Clue> clues)
    {
        String input;

        System.out.println("Which clue?(i.e. 1h or 3v");
        input = userInput.nextLine();

        if (!isValidClueSelection(input)) return;

        // Split clue input into 2 tokens
        int number = Integer.parseInt(String.valueOf(input.charAt(0)));
        char orientation = Character.toLowerCase(input.charAt(1));

        Clue clue = getClue(number, orientation, clues);
        if (clue == null)
            System.out.println("Clue " + input + " not found.");
        else
            System.out.println(clue.getNumber() + ". " + clue.getClue());
    }
    private static void answerAClue(Scanner userInput, ArrayList<Clue> clues, Crossword crossword)
    {
        displayAllClues(clues);

        System.out.println("\nWhich clue would you like to answer? (i.e. 1h or 3v)");
        String input = userInput.nextLine();

        if (!isValidClueSelection(input)) return;

        // Split clue input into 2 tokens
        int number = Integer.parseInt(String.valueOf(input.charAt(0)));
        char orientation = Character.toLowerCase(input.charAt(1));

        Clue clue = getClue(number, orientation, clues);
        if (clue != null)
        {
            System.out.println(clue.getNumber() + ". " + clue.getClue());
            System.out.println("Answer length: " + clue.getRealAnswer().length() + " letters");
            System.out.print("Enter your answer: ");
            String userAnswer = userInput.nextLine().toUpperCase();

            if (userAnswer.length() != clue.getRealAnswer().length())
            {
                System.out.println("Incorrect length. Expected " + clue.getRealAnswer().length() + " letters.");
                return;
            }

            clue.setSubmittedAnswer(userAnswer);
            crossword.trySetAnswer(clue);
            System.out.println("Answer saved!");
            crossword.displayBoard();
        }
        else
        {
            System.out.println("Clue " + input + " not found.");
        }
    }
    private static void eraseAnAnswer(Scanner userInput, ArrayList<Clue> clues, Crossword crossword)
    {
        displayAllClues(clues);

        System.out.println("\nWhich answer would you like to erase? (i.e. 1h or 3v)");
        String input = userInput.nextLine();

        if (!isValidClueSelection(input)) return;

        // Split clue input into 2 tokens
        int number = Integer.parseInt(String.valueOf(input.charAt(0)));
        char orientation = Character.toLowerCase(input.charAt(1));

        Clue clue = getClue(number, orientation, clues);
        if (clue != null)
        {
            crossword.eraseAnswer(clue);
            clue.setSubmittedAnswer("");
            crossword.displayBoard();
        }
        else
        {
            System.out.println("Clue " + input + " not found.");
        }
    }
    private static boolean submitPuzzle(Crossword crossword)
    {
        if (crossword.checkPuzzle())
        {
            System.out.println("Puzzle solved!");
            return true;
        }
        else
        {
            System.out.println("Something isn't quite right!");
            return false;
        }
    }

    private static int getMenuSelection(Scanner userInput)
    {
        int selection = 0;
        do {
            System.out.println("1. Display All Clues.");
            System.out.println("2. Display Specific Clue.");
            System.out.println("3. Answer a Clue.");
            System.out.println("4. Erase an Answer.");
            System.out.println("5. Submit Puzzle.");
            System.out.println("6. Exit.");

            String selectionStr = userInput.nextLine();
            if (isValidNumber(selectionStr))
                selection = Integer.parseInt(selectionStr);
        }
        while(selection <= 0 || selection >= 7);

        return selection;
    }
    private static Clue getClue(int number, char orientation, ArrayList<Clue> clues)
    {
        for (Clue clue : clues)
        {
            if (clue.getNumber() == number && clue.getOrientation() == orientation)
            {
                return clue;
            }
        }
        return null;
    }
    private static boolean isValidClueSelection(String input)
    {
        if (input.isEmpty() || input.length() > 2)
        {
            System.out.println("Format must follow '1h' or '3v'");
            return false;
        }

        String possibleNumber = String.valueOf(input.charAt(0));
        char possibleChar = input.charAt(input.length() - 1);

        if (!isValidNumber(possibleNumber)) return false;
        return isValidChar(possibleChar);
    }
    private static boolean isValidNumber(String str)
    {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e)
        {
            // Handle the exception if the string is not a valid integer
            System.out.println("Error: '" + str + "' is not a valid integer.");
            return false;
        }
    }
    private static boolean isValidChar(char letter)
    {
        if (letter == 'v' || letter == 'h' ||
                letter == 'V' || letter == 'H')
        {
            return true;
        }
        System.out.println("Must enter 'v' or 'h'");
        return false;
    }

    private static void fillAllAnswers(Crossword crossword, ArrayList<Clue> clues)
    {
        for (int i = 1; i < clues.size(); i++)
        {
            clues.get(i).setSubmittedAnswer(clues.get(i).getRealAnswer());
            crossword.trySetAnswer(clues.get(i));
        }
    }
}

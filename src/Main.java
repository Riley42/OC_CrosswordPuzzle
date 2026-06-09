import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main
{
    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);
        ArrayList<Clue> clues = FileUtils.loadAllClues();
        Crossword crossword = new Crossword(clues, 15, 15);
        crossword.displayBoard();

        int selection = getSelection(userInput);

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
                eraseAnAnswer();
                break;
            case 5:
                submitPuzzle();
                break;
            case 6:
                System.exit(0);
                break;
        }


    }

    private static void submitPuzzle()
    {

    }

    private static void eraseAnAnswer()
    {

    }

    private static void answerAClue(Scanner userInput, ArrayList<Clue> clues, Crossword crossword)
    {
        displayAllClues(clues);

        System.out.println("\nWhich clue would you like to answer? (i.e. 1h or 3v)");
        String input = userInput.nextLine();

        String possibleNumber = input.substring(0, input.length() - 1);
        char possibleChar = input.charAt(input.length() - 1);

        if (!isValidNumber(possibleNumber)) return;
        int number = Integer.parseInt(possibleNumber);

        if (!isValidChar(possibleChar)) return;
        char orientation = Character.toLowerCase(possibleChar);

        for (Clue clue : clues)
        {
            if (clue.getNumber() == number && clue.getOrientation() == orientation)
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
                return;
            }
        }

        System.out.println("Clue " + input + " not found.");
    }

    private static void displaySpecificClue(Scanner userInput, ArrayList<Clue> clues)
    {
        String input;

        System.out.println("Which clue?(i.e. 1h or 3v");
        input = userInput.nextLine();

        String possibleNumber = input.substring(0, input.length() - 1);
        char possibleChar = input.charAt(input.length() - 1);

        if (!isValidNumber(possibleNumber)) return;
        int number = Integer.parseInt(possibleNumber);

        if (!isValidChar(possibleChar)) return;
        char orientation = Character.toLowerCase(possibleChar);

        for (Clue clue : clues)
        {
            if (clue.getNumber() == number && clue.getOrientation() == orientation)
            {
                System.out.println(clue.getNumber() + ". " + clue.getClue());
                return;
            }
        }

        System.out.println("Clue " + input + " not found.");
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

    private static int getSelection(Scanner userInput)
    {
        int selection;
        do {
            System.out.println("1. Display All Clues.");
            System.out.println("2. Display Specifc Clue.");
            System.out.println("3. Answer a Clue.");
            System.out.println("4. Erase an Answer.");
            System.out.println("5. Submit Puzzle.");
            System.out.println("6. Exit.");

            selection = userInput.nextInt();
        }
        while(selection <= 0 || selection >= 7);

        userInput.nextLine();
        return selection;
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
}

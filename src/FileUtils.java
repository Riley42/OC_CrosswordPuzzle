import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtils
{
    public final static String FILE_PATH = "src\\answers.txt";
    public final static String DELIMITER = ":";

    public static ArrayList<Clue> loadAllClues()
    {
        ArrayList<Clue> clues = new ArrayList<>();

        try (Scanner fileReader = new Scanner(new FileReader(FILE_PATH)))
        {
            String line;

            while (fileReader.hasNextLine())
            {
                line = fileReader.nextLine().trim();

                if (line.isEmpty())
                    continue;

                String[] parts = line.split(DELIMITER);

                if (parts.length < 6)
                {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                int row          = Integer.parseInt(parts[0].trim());
                int col          = Integer.parseInt(parts[1].trim());
                char orientation = parts[2].trim().charAt(0);
                int number       = Integer.parseInt(parts[3].trim());
                String answer    = parts[4].trim().toLowerCase();
                String clueText  = parts[5].trim().toLowerCase();

                Clue clue = new Clue(row, col, number, orientation, clueText, answer);
                clues.add(clue);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error reading file: " + e.getMessage());
        }
        catch (NumberFormatException e)
        {
            System.out.println("Error parsing number in file: " + e.getMessage());
        }

        return clues;
    }
}

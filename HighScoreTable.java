import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HighScoreTable {
    private DoubleLinkedList data = new DoubleLinkedList();

    public HighScoreTable() {
        readHighScoreFile();
    }

    private void readHighScoreFile() {
        File reader = new File("./highscore.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(reader);
            String currLine = scanner.nextLine();

            String name = currLine.split("#")[0];
            float score = Float.parseFloat(currLine.split("#")[1]);
            Player newPlayer = new Player(name, score);
            data.append(newPlayer);

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

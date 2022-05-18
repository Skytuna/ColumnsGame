import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Scanner;

public class HighScoreTable {
    private DoubleLinkedList data = new DoubleLinkedList();
    private EnigmaExtended console = new EnigmaExtended();

    public HighScoreTable() {
        readHighScoreFile();
    }

    private void readHighScoreFile() {
        File reader = new File("highscore.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(reader, "UTF-8");
            String currLine;
            while (scanner.hasNextLine()) {
                currLine = scanner.nextLine();
                String name = currLine.split("#")[0];
                float score = Float.parseFloat(currLine.split("#")[1]);
                Player newPlayer = new Player(name, score);
                data.addPlayer(newPlayer);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printHighScore() {
        int length = data.size();
        for (int i = 0; i < length; i++) {
            DoubleNode node = data.getIndex(i);
            Player player = (Player) node.getData();
            String name = player.getName();
            float score = player.getScore();
            console.setCursor(2, 15 + i);
            console.print(name + " " + score);
        }
    }

    public void addNewPlayer(Player newPlayer) {
        data.addPlayer(newPlayer);
    }

    public void saveToFile() {

        try {
            OutputStreamWriter myWriter = new OutputStreamWriter(new FileOutputStream("highscore.txt"),
                    Charset.forName("UTF-8").newEncoder());
            for (int i = 0; i < data.size(); i++) {
                Player currPlayer = (Player) data.getIndex(i).getData();
                myWriter.write(currPlayer.getName() + "#" + currPlayer.getScore() + (i != data.size() - 1 ? "\n" : ""));
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

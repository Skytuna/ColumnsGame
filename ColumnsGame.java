public class ColumnsGame {
    private int transferCount = 0;
    private int score = 0;
    private Box box = new Box();
    private Columns columns = new Columns();
    private HighScoreTable highScoreTable = new HighScoreTable();
    private EnigmaExtended console = new EnigmaExtended();

    public ColumnsGame() {
        
    }

    private void addSixCardsToEachColumn() {
        // Add 6 six cards for each column initially
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                // TODO: Add 6 cards for each column
            }
        }
    }

    private void printScore() {

    }

    private void printTransferCount() {

    }
}

public class ColumnsGame {
    private int transferCount = 0;
    private int score = 0;
    private Box box = new Box();
    private Columns columns = new Columns();
    private HighScoreTable highScoreTable = new HighScoreTable();
    private EnigmaExtended console = new EnigmaExtended();

    public ColumnsGame() {
        printStaticScreenElements();

        // TODO: print these for each iteration
        printScore();
        printTransferCount();

        addSixCardsToEachColumn();
        columns.printColumns();
    }

    private void addSixCardsToEachColumn() {
        // Add 6 six cards for each column initially
        for (int col = 1; col < 6; col++) {
            for (int cardIndex = 0; cardIndex < 6; cardIndex++) {
                Card card = box.getShownCard();
                columns.addCardToColumn("C" + col, card);
            }
        }
    }

    private void printScore() {
        String scoreAsString = String.valueOf(score);
        console.setCursor(40 - scoreAsString.length(), 1);
        console.print(scoreAsString);
    }

    private void printTransferCount() {
        String transferCountAsString = String.valueOf(transferCount);
        console.setCursor(40 - transferCountAsString.length(), 0);
        console.print(transferCountAsString);
    }

    private void printStaticScreenElements() {
        // column names
        console.setCursor(0, 0);
        for (int i = 1; i < 6; i++)
            console.print("C" + i + "  ");

        // column indicators
        console.setCursor(0, 1);
        for (int i = 1; i < 6; i++)
            console.print("--  ");

        // transfer
        console.setCursor(28, 0);
        console.print("Transfer:");

        // score
        console.setCursor(28, 1);
        console.print("Score:");

        // box
        console.setCursor(28, 4);
        console.print("Box");
        console.setCursor(28, 5);
        console.print("+--+");
        console.setCursor(28, 6);
        console.print("|  |");
        console.setCursor(28, 7);
        console.print("+--+");
    }
}

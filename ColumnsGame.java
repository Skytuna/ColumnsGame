public class ColumnsGame {
    private int transferCount = 0;
    private int score = 0;
    private Box box = new Box();
    private Columns columns = new Columns();
    private HighScoreTable highScoreTable = new HighScoreTable();
    private EnigmaExtended console = new EnigmaExtended();

    public ColumnsGame() {
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

    }

    private void printTransferCount() {

    }
}

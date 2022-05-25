import java.awt.event.KeyEvent;

public class ColumnsGame {
    private int transferCount = 0;
    private float score = 0;
    private Box box = new Box();
    private Columns columns = new Columns();
    private HighScoreTable highScoreTable = new HighScoreTable();
    private EnigmaExtended console = new EnigmaExtended();
    private boolean isGameRunning = true;
    private int selectedColumn = 0; // 0 means no selected column
    private boolean isBoxCardSelected;
    private boolean selectedCardExists;

    public ColumnsGame() {
        console.console.getTextWindow().addKeyListener(console.klis);
        addSixCardsToEachColumn();
        columns.highlightFirstCard();

        while (isGameRunning) {
            isBoxCardSelected = box.getShownCardState() == ShownStateEnum.SELECTED;
            selectedCardExists = columns.selectedCardExists();

            printStaticScreenElements();
            printScore();
            printTransferCount();
            printShownCard();
            printColumnNames();

            columns.printColumns();

            if (console.isKeyPressed() != -1) {
                switch (console.rkey) {
                    case KeyEvent.VK_E:
                        isGameRunning = false;
                        break;
                    case KeyEvent.VK_B:
                        if (selectedCardExists)
                            break;

                        columns.resetCardHighlightStates();
                        box.toggleTopCard();
                        isBoxCardSelected = box.getShownCardState() == ShownStateEnum.SELECTED;
                        if (isBoxCardSelected) {
                            selectedColumn = 1;
                            columns.resetCardHighlightStates();
                            break;
                        }
                        columns.highlightFirstCard();
                        break;
                    case KeyEvent.VK_LEFT:
                        if (isBoxCardSelected || selectedCardExists) {
                            if (selectedColumn == 1 || selectedColumn == 0)
                                break;
                            selectedColumn--;
                            break;
                        }
                        columns.moveHighlightedCursor(DirectionsEnum.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (isBoxCardSelected || selectedCardExists) {
                            if (selectedColumn == 5 || selectedColumn == 0)
                                break;
                            selectedColumn++;
                            break;
                        }
                        columns.moveHighlightedCursor(DirectionsEnum.RIGHT);
                        break;
                    case KeyEvent.VK_UP:
                        if (isBoxCardSelected || selectedCardExists)
                            break;
                        columns.moveHighlightedCursor(DirectionsEnum.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        if (isBoxCardSelected || selectedCardExists)
                            break;

                        columns.moveHighlightedCursor(DirectionsEnum.DOWN);
                        break;
                    case KeyEvent.VK_X:
                        if (isBoxCardSelected) {
                            boolean safelyTransfered = boxCardValidator();
                            if (safelyTransfered) {

                                int[] coords = columns.findMatch();
                                if (coords != null) {
                                    columns.deleteMatch(coords[0], coords[1]);
                                    score += 1000;
                                    console.clearConsole();
                                }

                                columns.highlightFirstCard();
                            }
                            break;
                        }
                        if (selectedCardExists) {
                            boolean safelyTransfered = columns.moveSelectedCardsToColumn(selectedColumn);
                            if (safelyTransfered) {
                                transferCount++;
                                columns.unhighlightAllCards();
                                columns.unselectAllCards();

                                int[] coords = columns.findMatch();
                                if (coords != null) {
                                    columns.deleteMatch(coords[0], coords[1]);
                                    score += 1000;
                                    console.clearConsole();
                                }

                                columns.highlightFirstCard();
                            }
                        }
                        break;
                    case KeyEvent.VK_Z:
                        if (isBoxCardSelected)
                            break;
                        if (columns.selectedCardExists()) {
                            columns.unhighlightAllCards();
                            columns.highlightFirstCard();
                            columns.unselectAllCards();
                            break;
                        }
                        selectedColumn = 1;
                        columns.selectCardsBelow();
                        break;
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        console.clearConsole();
        console.print("Game is over!\n");
        console.print("Please enter your name:");
        String playerName = console.console.readLine();

        // Mock player
        Player player = new Player(playerName, 243.2f);
        highScoreTable.addNewPlayer(player);

        highScoreTable.printHighScore();
        highScoreTable.saveToFile();
    }

    private void addSixCardsToEachColumn() {
        // Add 6 six cards for each column initially
        for (int col = 1; col < 6; col++) {
            for (int cardIndex = 0; cardIndex < 6; cardIndex++) {
                Card card = box.popShownCard();
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

    private void printShownCard() {
        console.setCursor(29, 6);
        if (box.getShownCardState() == ShownStateEnum.CLOSED) {
            console.print("  ");
            return;
        }

        String shownCardValue = String.valueOf(box.getShownCard().getValue());
        if (box.getShownCardState() == ShownStateEnum.SELECTED) {
            console.print(shownCardValue, Colors.redColor);
            return;
        }

        console.print(shownCardValue);
    }

    private void printStaticScreenElements() {
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

    private void printColumnNames() {
        console.setCursor(0, 0);
        for (int i = 1; i < 6; i++) {
            if (i == selectedColumn
                    && (isBoxCardSelected || selectedCardExists)) {
                console.print("C" + i, Colors.orangeColor);
                console.print("  ");
                continue;
            }
            console.print("C" + i + "  ");
        }
    }

    private boolean boxCardValidator() {
        Card lastCardOfColumn = columns.getLastCardOfColumn(selectedColumn);
        Card selectedCard = box.getShownCard();

        boolean isInRangeOne = lastCardOfColumn != null
                && (Math.abs(lastCardOfColumn.getValue() - selectedCard.getValue()) <= 1);
        boolean emptyColumnCheck = lastCardOfColumn == null
                && (selectedCard.getValue() == 1 || selectedCard.getValue() == 10);

        if (isInRangeOne || emptyColumnCheck) {
            box.popShownCard();
            columns.addCardToColumn("C" + selectedColumn, selectedCard);
            box.setShownCardState(ShownStateEnum.CLOSED);
            transferCount++;
            return true;
        }

        return false;
    }
}

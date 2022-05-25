public class Columns {
    private MultiLinkedList data = new MultiLinkedList();
    private EnigmaExtended console = new EnigmaExtended();

    public Columns() {
        // Add 5 columns as 5 parent nodes
        for (int i = 1; i < 6; i++)
            data.addParentNode("C" + i);
    }

    public void addCardToColumn(String columnName, Card newCard) {
        data.addChildNode(columnName, newCard);
    }

    public void printColumns() {
        // Parents represents the columns in the game
        // Children represents the cards itself
        for (int parentIndex = 0; parentIndex < 5; parentIndex++) {
            ParentNode parent = data.getParentByIndex(parentIndex);
            for (int childIndex = 0; childIndex < parent.sizeChild(); childIndex++) {
                ChildNode child = data.getChildByIndex(parentIndex, childIndex);
                Card card = (Card) child.getData();
                String cardValue = String.valueOf(card.getValue());
                console.setCursor(4 * parentIndex, childIndex + 2);

                // Print the card with the right color
                if (card.isSelected()) {
                    console.print(cardValue, Colors.blueColor);
                    continue;
                }

                if (card.isHighlighted()) {
                    console.print(cardValue, Colors.cyanColor);
                    continue;
                }

                console.print(cardValue);
            }
        }
    }

    /**
     * 
     * @param column 1-5
     * @return
     */
    public Card getLastCardOfColumn(int column) {
        if (data.getParentByIndex(column - 1).sizeChild() == 0)
            return null;

        return (Card) data.getChildByIndex(column - 1, data.getParentByIndex(column - 1).sizeChild() - 1).getData();
    }

    public void setSlot(int column, int row, Card newCard) {

    }

    /**
     * 
     * @param column 1-5
     * @param row    1-infinite
     * @return
     */
    public Card getSlot(int column, int row) {
        return (Card) data.getChildByIndex(column - 1, row - 1).getData();
    }

    public void highlightFirstCard() {
        for (int column = 1; column <= 5; column++) {
            ParentNode parent = data.getParentByIndex(column - 1);
            if (parent.getNextChild() == null)
                continue;
            getSlot(column, 1).setIsHighlighted(true);
            break;
        }
    }

    public void resetCardHighlightStates() {
        // Parents represents the columns in the game
        // Children represents the cards itself
        for (int parentIndex = 0; parentIndex < 5; parentIndex++) {
            ParentNode parent = data.getParentByIndex(parentIndex);
            for (int childIndex = 0; childIndex < parent.sizeChild(); childIndex++) {
                ChildNode child = data.getChildByIndex(parentIndex, childIndex);
                Card card = (Card) child.getData();

                // Set hightlight/selected states to false so that it get updated in the UI
                card.setIsHighlighted(false);
                card.setSelected(false);
            }
        }
    }

    public void moveHighlightedCursor(DirectionsEnum direction) {
        int[] coords = getHighlightedCardCoords();
        console.print("");
        if (coords == null)
            return;
        int x = coords[0], y = coords[1];

        Card prevCard, newCard;
        switch (direction) {
            case UP:
                if (y == 0)
                    return;
                prevCard = getSlot(x + 1, y + 1);
                newCard = getSlot(x + 1, y);
                prevCard.setIsHighlighted(false);
                newCard.setIsHighlighted(true);
                return;
            case DOWN:
                if (y == data.getParentByIndex(x).sizeChild() - 1)
                    return;
                prevCard = getSlot(x + 1, y + 1);
                newCard = getSlot(x + 1, y + 2);
                prevCard.setIsHighlighted(false);
                newCard.setIsHighlighted(true);
                return;
            case LEFT:
                if (x == 0)
                    return;

                for (int i = x - 1; i >= 0; i--) {
                    if (data.getChildByIndex(i, 0) == null)
                        continue;

                    prevCard = getSlot(x + 1, y + 1);
                    newCard = getSlot(1 + i, 1);
                    prevCard.setIsHighlighted(false);
                    newCard.setIsHighlighted(true);
                    return;
                }
                return;
            case RIGHT:
                if (x == 4)
                    return;

                for (int i = x + 1; i <= 4; i++) {
                    if (data.getChildByIndex(i, 0) == null)
                        continue;

                    prevCard = getSlot(x + 1, y + 1);
                    newCard = getSlot(1 + i, 1);
                    prevCard.setIsHighlighted(false);
                    newCard.setIsHighlighted(true);
                    return;
                }
                return;
        }
    }

    public int[] getHighlightedCardCoords() {
        for (int parentIndex = 0; parentIndex < 5; parentIndex++) {
            ParentNode parent = data.getParentByIndex(parentIndex);
            for (int childIndex = 0; childIndex < parent.sizeChild(); childIndex++) {
                ChildNode child = data.getChildByIndex(parentIndex, childIndex);
                Card card = (Card) child.getData();

                if (card.isHighlighted()) {
                    int[] coords = { parentIndex, childIndex };
                    return coords;
                }
            }
        }

        return null;
    }

    public Card getHighlightedCard() {
        for (int parentIndex = 0; parentIndex < 5; parentIndex++) {
            ParentNode parent = data.getParentByIndex(parentIndex);
            for (int childIndex = 0; childIndex < parent.sizeChild(); childIndex++) {
                ChildNode child = data.getChildByIndex(parentIndex, childIndex);
                Card card = (Card) child.getData();

                if (card.isHighlighted())
                    return card;
            }
        }

        return null;
    }

    public boolean selectedCardExists() {
        for (int parentIndex = 0; parentIndex < 5; parentIndex++) {
            ParentNode parent = data.getParentByIndex(parentIndex);
            for (int childIndex = 0; childIndex < parent.sizeChild(); childIndex++) {
                ChildNode child = data.getChildByIndex(parentIndex, childIndex);
                Card card = (Card) child.getData();

                if (card.isSelected())
                    return true;
            }
        }
        return false;
    }

    public void unselectAllCards() {
        for (int parentIndex = 0; parentIndex < 5; parentIndex++) {
            ParentNode parent = data.getParentByIndex(parentIndex);
            for (int childIndex = 0; childIndex < parent.sizeChild(); childIndex++) {
                ChildNode child = data.getChildByIndex(parentIndex, childIndex);
                Card card = (Card) child.getData();
                card.setSelected(false);
            }
        }
    }

    public void unhighlightAllCards() {
        for (int parentIndex = 0; parentIndex < 5; parentIndex++) {
            ParentNode parent = data.getParentByIndex(parentIndex);
            for (int childIndex = 0; childIndex < parent.sizeChild(); childIndex++) {
                ChildNode child = data.getChildByIndex(parentIndex, childIndex);
                Card card = (Card) child.getData();
                card.setIsHighlighted(false);
            }
        }
    }

    /**
     * @note "selectionFlag" is a boolean value that helps us mark the cards for
     *       those are under the highlighted card
     */
    public void selectCardsBelow() {
        for (int parentIndex = 0; parentIndex < 5; parentIndex++) {
            ParentNode parent = data.getParentByIndex(parentIndex);
            boolean selectionFlag = false;
            for (int childIndex = 0; childIndex < parent.sizeChild(); childIndex++) {
                ChildNode child = data.getChildByIndex(parentIndex, childIndex);
                Card card = (Card) child.getData();

                if (card.isHighlighted()) {
                    selectionFlag = true;
                    card.setSelected(true);
                    continue;
                }

                if (selectionFlag)
                    card.setSelected(true);
            }
        }
    }

    public boolean moveSelectedCardsToColumn(int selectedColumn) {
        Card lastCardOfColumn = getLastCardOfColumn(selectedColumn);
        Card highlightedCard = getHighlightedCard();
        int[] highlightedCardCoords = getHighlightedCardCoords();
        int x = highlightedCardCoords[0], y = highlightedCardCoords[1];
        if (x + 1 == selectedColumn)
            return false;

        boolean isInRangeOne = lastCardOfColumn != null
                && (Math.abs(lastCardOfColumn.getValue() - highlightedCard.getValue()) <= 1);
        boolean emptyColumnCheck = lastCardOfColumn == null
                && (highlightedCard.getValue() == 1 || highlightedCard.getValue() == 10);

        if (isInRangeOne || emptyColumnCheck) {
            // Add selected cards to the new column
            for (int currentRow = y + 1; currentRow < data.getParentByIndex(x).sizeChild() + 1; currentRow++) {
                Card cardToMove = getSlot(x + 1, currentRow);
                data.addChildNode("C" + selectedColumn, cardToMove);
            }

            // Delete old cards from the old column
            for (int currentRow = data.getParentByIndex(x).sizeChild() + 1; currentRow >= 0; currentRow--) {
                data.deleteChildByIndex(x, y);
                console.setCursor(4 * x, currentRow + 2);
                console.print("  ");
            }

            return true;
        }

        return false;
    }

    /**
     * 
     * Checks whether there is a match. If there is, returns its coords.
     * 
     * @return coords, if there is no match then null
     */
    public int[] findMatch() {
        for (int parentIndex = 0; parentIndex < 5; parentIndex++) {
            ParentNode parent = data.getParentByIndex(parentIndex);
            int matchCounter = 0; // if matchCounter hits 10, this means there is a match
            int matchStarter = 0; // 0 if no match starter (0 or 10) found

            ChildNode prevChild = data.getChildByIndex(parentIndex, 0);
            if (prevChild == null)
                continue;

            Card prevCard = (Card) prevChild.getData();

            if (prevCard.getValue() == 1) {
                matchCounter = 1;
                matchStarter = 1;
            }

            if (prevCard.getValue() == 10) {
                matchCounter = 1;
                matchStarter = 10;
            }

            for (int childIndex = 1; childIndex < parent.sizeChild(); childIndex++) {
                prevChild = data.getChildByIndex(parentIndex, childIndex - 1);
                prevCard = (Card) prevChild.getData();
                ChildNode child = data.getChildByIndex(parentIndex, childIndex);
                Card card = (Card) child.getData();

                if (matchStarter == 1) {
                    if ((card.getValue() == prevCard.getValue() + 1)) {
                        matchCounter++;

                        if (matchCounter == 10) {
                            int[] coords = { parentIndex, childIndex - 9 };
                            return coords;
                        }

                        continue;
                    }

                    matchCounter = 0;
                    matchStarter = 0;
                }

                if (matchStarter == 10) {
                    if ((card.getValue() == prevCard.getValue() - 1)) {
                        matchCounter++;

                        if (matchCounter == 10) {
                            int[] coords = { parentIndex, childIndex - 9 };
                            return coords;
                        }
                        continue;
                    }

                    matchCounter = 0;
                    matchStarter = 0;
                }

                if (card.getValue() == 1) {
                    matchCounter = 1;
                    matchStarter = 1;
                    continue;
                }

                if (card.getValue() == 10) {
                    matchCounter = 1;
                    matchStarter = 10;
                    continue;
                }
            }
        }

        return null;
    }

    public void deleteMatch(int x, int y) {
        for (int i = 0; i < 10; i++) {
            data.deleteChildByIndex(x, y);
        }
    }

    public boolean isAnyCardsLeft() {
        for (int parentIndex = 0; parentIndex < 5; parentIndex++) {
            if (data.getParentByIndex(parentIndex).sizeChild() != 0)
                return true;
        }
        return false;
    }
}
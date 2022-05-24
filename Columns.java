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

                if (card.isHighlighted() && !selectedCardExists()) {
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
        // TODO: kart var mı diye çek et yoksa sütunları iterate et
        getSlot(1, 1).setIsHighlighted(true);
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
}
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
                console.print(cardValue);
            }
        }
    }
}

public class Columns {
    private MultiLinkedList data = new MultiLinkedList();

    public Columns() {
        // Add 5 columns as 5 parent nodes
        for (int i = 1; i < 6; i++)
            data.addParentNode("C" + i);
    }

    public void addCardToColumn(String columnName, Card newCard) {
        data.addChildNode(columnName, newCard);
    }

    public void printColumns() {
        ParentNode parent;///////////////////////////////////////////////////////////////////
        for (int column = 0; column < 5; column++) {
            parent = data.getParentByIndex(index)
            for (int i = 1; i < )
        }
    }
}

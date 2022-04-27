public class Card {
    private int value;
    private boolean isHighlighted = false;

    public Card(int value) {
        this.value = value;
    }

    public boolean getIsHighlighted() {
        return this.isHighlighted;
    }

    public void setIsHighlighted(boolean isHighlighted) {
        this.isHighlighted = isHighlighted;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}

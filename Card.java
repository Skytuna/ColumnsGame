public class Card {
    private int value;
    private boolean isHighlighted = false;
    private boolean isSelected = false;

    public Card(int value) {
        this.value = value;
    }

    public boolean isHighlighted() {
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

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}

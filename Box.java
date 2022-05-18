public class Box {
    private ShownStateEnum shownState = ShownStateEnum.CLOSED;
    private SingleLinkedList cards = new SingleLinkedList();

    public Box() {
        // Create a linked list that contains unsort cards
        SingleLinkedList unsortedCardTypes = new SingleLinkedList();
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 11; j++) {
                unsortedCardTypes.add(j);
            }
        }

        // Sort the unsorted cards list by
        while (cards.size() < 50) {
            int randomIndex = Utils.getRandomNumber(0, unsortedCardTypes.size());
            int randomCardType = (int) unsortedCardTypes.getIndex(randomIndex);
            unsortedCardTypes.deleteIndex(randomIndex);

            Card newCard = new Card(randomCardType);
            cards.add(newCard);
        }
    }

    public ShownStateEnum getShownCardState() {
        return this.shownState;
    }

    public void setShownCardState(ShownStateEnum newShownState) {
        this.shownState = newShownState;
    }

    // Returns the card thats on top of the deck
    public Card popShownCard() {
        Card card = (Card) cards.getIndex(cards.size() - 1);
        cards.deleteIndex(cards.size() - 1);
        return card;
    }

    public Card getShownCard() {
        Card card = (Card) cards.getIndex(cards.size() - 1);
        return card;
    }

    public int getCardsSize() {
        return cards.size();
    }

    public void toggleTopCard() {
        switch (shownState) {
            case CLOSED:
                this.shownState = ShownStateEnum.OPENED;
                break;
            case OPENED:
                this.shownState = ShownStateEnum.SELECTED;
                break;
            case SELECTED:
                this.shownState = ShownStateEnum.OPENED;
                break;
        }
    }
}

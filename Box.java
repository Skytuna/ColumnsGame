public class Box {
    private boolean showTopCardValue = false;
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

    public boolean isShowTopCardValue() {
        return this.showTopCardValue;
    }

    public void setShowTopCardValue(boolean showTopCardValue) {
        this.showTopCardValue = showTopCardValue;
    }

    // Returns the card thats on top of the deck
    public Card getShownCard() {
        Card card = (Card) cards.getIndex(cards.size() - 1);
        cards.deleteIndex(cards.size() - 1);
        return card;
    }
    
    public int getCardsSize() {
    	return cards.size();
    }
}

package matatu;

public class CardStack {
	private Card[] deckOfCards;
	private int cardsUsed;
	private int noOfCards = 52;

	public CardStack() {
		deckOfCards = new Card[noOfCards];
		int count = 0;
		for (int suit = Card.SPADE; suit <= Card.CLUB; suit++) {
			for (int value = 1; value <= 13; value++) {
				deckOfCards[count] = new Card(value, suit);
				count++;
			}
		}
		cardsUsed = 0;
	}

	public void shuffle() {
		int i, j, k;
		for (k = 0; k < 51; k++) {
			i = (int) (noOfCards * Math.random());
			j = (int) (noOfCards * Math.random());
			Card tmp = deckOfCards[i];
			deckOfCards[i] = deckOfCards[j];
			deckOfCards[j] = tmp;
		}
		cardsUsed = 0;
	}

	public int cardsLeft() {
		return 52 - cardsUsed;
	}

	public Card dealCard() {
		if (cardsUsed == 52)
			shuffle();
		cardsUsed++;
		return deckOfCards[cardsUsed - 1];
	}
}

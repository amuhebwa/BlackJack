package matatu;

public class BlackjackPlayer extends Hand {
	// Get the total card value.
	// If the hand contains an ace, the The first one is worth 11 points and the rest are each equal to one.
	public int getBlackjackValue() {
		int val = 0, cards = getCardCount();
		boolean ace = false;

		for (int i = 0; i < cards; i++) {
			Card card;
			int cardVal;
			card = getCard(i);
			cardVal = card.getValue();
			if (cardVal > 10) {
				cardVal = 10;
			}
			if (cardVal == 1) {
				ace = true;
			}
			val = val + cardVal;
		}

		if (ace == true && val + 10 <= 21)
			val = val + 10;

		return val;

	}

}

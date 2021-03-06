package matatu;

import java.util.ArrayList;

public class Hand {
	/**
	 * hand refers to the cards in a player/dealer's hand during a single game
	 * session
	 */
	private ArrayList<Card> hand;

	public Hand() {
		hand = new ArrayList<Card>();
	}

	// Add a single card to the player/dealer's hand
	public void addCard(Card c) {
		if (c != null)
			hand.add(c);
	}

	public Card getCard(int position) {
		if (position >= 0 && position < hand.size())
			return (Card) hand.get(position);
		else
			return null;
	}

	public void removeCard(int position) {
		if (position >= 0 && position < hand.size())
			hand.remove(position);
	}

	public int getCardCount() {
		return hand.size();
	}

	public void clear() {
		hand.clear();
	}
}

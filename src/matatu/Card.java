package matatu;

public class Card {
	public final static int SPADE = 1, HEARTS = 2, DIAMONDS = 3, CLUB = 4;
	public final static int ACE = 1, JACK = 11, QUEEN = 12, KING = 13;
	private final int suit;
	private final int value;

	public Card(int theValue, int theSuit) {
		value = theValue;
		suit = theSuit;
	}

	public int getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}

	public String getSuitAsString() {
		switch (suit) {
		case SPADE:
			return "Spades";
		case HEARTS:
			return "Hearts";
		case DIAMONDS:
			return "Diamonds";
		case CLUB:
			return "Clubs";
		default:
			return null;
		}
	}

	public String getValueAsString() {
		switch (value) {
		case 1:
			return "Ace";
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";
		case 5:
			return "5";
		case 6:
			return "6";
		case 7:
			return "7";
		case 8:
			return "8";
		case 9:
			return "9";
		case 10:
			return "10";
		case 11:
			return "Jack";
		case 12:
			return "Queen";
		case 13:
			return "King";
		default:
			return null;
		}
	}

	public String toString() {
		return getValueAsString() + " of " + getSuitAsString();
	}

}

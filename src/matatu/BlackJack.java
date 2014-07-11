package matatu;

import java.util.Scanner;

public class BlackJack {
	static int tokens = 100; // Intial amount of tokens
	static int bet = 0;
	static boolean userWins = false;
	static int amount = 0;
	static char playOption;
	static long totalWinnings = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		totalWinnings = calculateOdds();
		System.out.println("Your Winnings : " + totalWinnings + '.');
	}

	/**
	 * Calculates the number of tokens that player has, depending on whether he
	 * wins or is busted
	 * 
	 * @returns tokens : number of tokens left
	 */
	public static long calculateOdds() {
		while (true) {
			System.out.println("You have : " + tokens + " Tokens");
			bet = getBetAmount();
			if (bet == 0)
				break;
            /**
             * Get the winner. If a player wins, increase the number of their
             * tokens by the amount they've placed. Otherwise, subtract the amount
             * they've placed from their total token amount
             */
			userWins = playBlackJack();
			if (userWins)
				tokens = tokens + bet;
			else
				tokens = tokens - bet;
			if (tokens == 0) {
				System.out.println("No more tokens");
				break;
			}

		}
		return tokens;

	}

	/**
	 * Request the user to enter the number of tokens the wish to place as a bet
	 * 
	 * @return amount: No of tokens to place
	 */
	public static int getBetAmount() {
		do {
			System.out.println("Enter 0 to Exit game ");
			System.out.println("How many tokens do you want to place ? ");

			Scanner scanner = new Scanner(System.in);
			try {
				amount = scanner.nextInt();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Please enter a number");
			}

			if (amount < 0 || amount > tokens)
				System.out.println("Tokens out of range");

		} while (amount < 0 || amount > tokens);

		return amount;

	}

	/**
	 * Method to simulate actual game play
	 * 
	 * @return winner: true if a player wins, false if the dealer wins
	 */
	static boolean playBlackJack() {
        // Create a hand for player and dealer
		CardStack cardStack = new CardStack();
		BlackjackPlayer dealerHand = new BlackjackPlayer();
		BlackjackPlayer userHand = new BlackjackPlayer();
        
		// Shuffle the cards so that a player won't read them
		cardStack.shuffle();
		
		// Add two cards to the dealer's hand
		dealerHand.addCard(cardStack.dealCard());
		dealerHand.addCard(cardStack.dealCard());
		// Add two initial cards to the player's hand
		userHand.addCard(cardStack.dealCard());
		userHand.addCard(cardStack.dealCard());
        
		// Check if player or dealer has a black jack (21)
		blackJackWinner(dealerHand, userHand);
		
        // None has a black jack. Show them their cards
		while (true) {
			// Request the user to hit(play again) or stand (stop playing)
			for (int i = 0; i < userHand.getCardCount(); i++)
				System.out.println(userHand.getCard(i));
			System.out.println("Your total is " + userHand.getBlackjackValue());
			System.out.println("Dealer is showing the " + dealerHand.getCard(0));

			System.out.println("Hit (H) or Stand (S)? ");
			do {

				try {
					Scanner scanner = new Scanner(System.in);
					playOption = Character.toUpperCase(scanner.next().charAt(0));
				} catch (Exception e) {
					// TODO Auto-generated catch block

				}
				if (playOption != 'H' && playOption != 'S')
					System.out.println("Enter H or S:  ");
			} while (playOption != 'H' && playOption != 'S');

			if (playOption == 'S') {
				break;
			} else {
				Card newCard = cardStack.dealCard();
				userHand.addCard(newCard);
				System.out.println("User hits");
				System.out.println("Your card is the " + newCard);
				System.out.println("Your total is now "+ userHand.getBlackjackValue());
				if (userHand.getBlackjackValue() > 21) {
					System.out.println("You busted(total card value is over 21 ).  You lose");
					System.out.println("Dealer's other card was the "+ dealerHand.getCard(1));
					return false;
				}
			}
		}

		System.out.println("User stands.");
		System.out.println("Dealer's cards are");
		System.out.println(dealerHand.getCard(0) + ", " + dealerHand.getCard(1));

		/**
		 * If the dealer's hand is less than 17, they keeps hitting until
		 * their tally is more than 17
		 */
		while (dealerHand.getBlackjackValue() < 17) {
			Card newCard = cardStack.dealCard();
			System.out.println("Dealer hits & gets the " + newCard);
			dealerHand.addCard(newCard);
			if (dealerHand.getBlackjackValue() > 21) {
				System.out.println("Dealer busted.  You win");
				return true;

			}
		}

		System.out.println("Dealer's total is "+ dealerHand.getBlackjackValue());
		if (dealerHand.getBlackjackValue() == userHand.getBlackjackValue()) {
			System.out.println("Dealer wins on a tie.  You lose");
			return false;
		} else if (dealerHand.getBlackjackValue() > userHand.getBlackjackValue()) {
			System.out.println("Dealer wins with " + dealerHand.getBlackjackValue() + " . You have " + userHand.getBlackjackValue());
			return false;
		} else {
			System.out.println("You win with " + userHand.getBlackjackValue() + " Dealer has " + dealerHand.getBlackjackValue());
			return true;
		}

	}

	/**
	 * Method to calculate the black Jack winner, that is whether the total card
	 * score for the dealer/player is equal to 21 for the first hand
	 * 
	 * @param dealerHand
	 *            : array List containing dealer's first two cards
	 * @param userHand
	 *            : array List containing player's first two cards
	 * @return winner:true if player wins, false if dealer wins
	 */
	public static boolean blackJackWinner(BlackjackPlayer dealerHand,
			BlackjackPlayer userHand) {
		boolean winner = false;
		if (dealerHand.getBlackjackValue() == 21) {
			System.out.println("Dealer has the " + dealerHand.getCard(0) + " and the " + dealerHand.getCard(1));
			System.out.println("User has the " + userHand.getCard(0) + " and the " + userHand.getCard(1));
			System.out.println("Dealer has 21 points and wins with black jack");
			winner = false;
		}

		if (userHand.getBlackjackValue() == 21) {
			System.out.println("Dealer has the " + dealerHand.getCard(0) + " and  " + dealerHand.getCard(1));
			System.out.println("User has the " + userHand.getCard(0) + " and the " + userHand.getCard(1));
			System.out.println("You have 21 points and win with a blackjack");
			winner = true;
		}
		return winner;
	}
}

package matatu;

import java.util.Scanner;

public class BlackJack {
    static int tokens = 100;
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
    public static long calculateOdds() {
    	while (true) {
            System.out.println("You have : " + tokens + " Tokens");
            bet = getBettAmount();
            if (bet == 0)
				break;
            
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

	public static int getBettAmount() {
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

	static boolean playBlackJack() {

		CardStack cardStack = new CardStack();
		BlackjackPlayer dealerHand = new BlackjackPlayer();
		BlackjackPlayer userHand = new BlackjackPlayer();

		cardStack.shuffle();
		dealerHand.addCard(cardStack.dealCard());
		dealerHand.addCard(cardStack.dealCard());
		userHand.addCard(cardStack.dealCard());
		userHand.addCard(cardStack.dealCard());
	
		blackJackWinner(dealerHand,userHand );
		

		while (true) {
			/* Display user's cards, and let user decide to Hit or Stand. */
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

		while (dealerHand.getBlackjackValue() <= 17) {
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
		}
		else if (dealerHand.getBlackjackValue() > userHand.getBlackjackValue()) {
			System.out.println("Dealer wins with " + dealerHand.getBlackjackValue()+ " . You have " + userHand.getBlackjackValue());
			return false;
		}
		else {
			System.out.println("You win with " + userHand.getBlackjackValue()+ " Dealer has " + dealerHand.getBlackjackValue());
			return true;
		}

	}
public static boolean blackJackWinner(BlackjackPlayer dealerHand, BlackjackPlayer userHand) {
	boolean winner = false;
	if (dealerHand.getBlackjackValue() == 21) {
		System.out.println("Dealer has the " + dealerHand.getCard(0) + " and the " + dealerHand.getCard(1));
		System.out.println("User has the " + userHand.getCard(0) + " and the " + userHand.getCard(1));
		System.out.println("Dealer has 21 points and wins with black jack");
		winner = false;
	}

	if (userHand.getBlackjackValue() == 21) {
		System.out.println("Dealer has the " + dealerHand.getCard(0) + " and  " + dealerHand.getCard(1));
		System.out.println("User has the " + userHand.getCard(0)+ " and the " + userHand.getCard(1));
		System.out.println("You have 21 points and win with a blackjack");
		winner = true;
	}
	return winner;
}
}

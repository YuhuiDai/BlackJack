package impl;
import api.Card;
import api.Hand;
import java.util.HashSet;

public class BlackJackHand extends Hand {
	
	public int suitTotal;
	
	public BlackJackHand() {

		this.suitTotal = 0;
		this.cards = new HashSet<>();
	}

	@Override
	public int compareTo(Hand o) {
		// TODO Auto-generated method stub
		if (this.valueOf() < o.valueOf()) {
			return -1;
		} else if (this.valueOf() == o.valueOf()) {
			// in case of a tie
			int oSuit = 0;
			for (Card c: o.getCards()){
				oSuit += c.getSuit().getSuit();
			}
			if (suitTotal < oSuit) {
				return -1;
			} else if (suitTotal == oSuit) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		if (this.valueOf() <= 21) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isWinner() {
		
		if (this.valueOf() == 21) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int valueOf() {
		int total = 0;
		for (Card c: cards) {
			total = c.getValue().getValue() + total;
		}
		return total;
	}
	
}
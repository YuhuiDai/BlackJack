package impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import api.Card;
import api.Dealer;
import api.Hand;
import api.Player;

public class BlackJackDealer extends BlackJackPlayer implements Dealer {
	public static ArrayList<Card> deck = new ArrayList<Card>();
//	public Hand myHand;
	public int suitTotal;

	
	public BlackJackDealer(int name) {
		super(name);
		this.suitTotal = 0;
		this.myHand = new BlackJackHand();

		// initialize a deck of cards
		
		for (Card.Value v: Card.Value.values()){
			
			for (Card.Suit s: Card.Suit.values()){
				deck.add(new Card(v,s));
			}
		}
		this.strat = new DealerStrategy(this);
		
	}

	@Override
	public void dealCard(Player player) {
		// player receive the card and remove from the deck of cards
		player.receive(deck.get(0));
		deck.remove(0);
	}

	@Override
	public void collectCards(Player player) {
		Iterator<Card> iterator = player.getHand().getCards().iterator(); 
		while (iterator.hasNext()) {
			Card c = iterator.next();
			BlackJackDealer.deck.add(c);
			iterator.remove();
		}	
	}

	@Override
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
}
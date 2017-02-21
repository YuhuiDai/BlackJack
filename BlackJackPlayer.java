package impl;

import java.util.Random;

import api.Strategy;
import api.Card;
import api.Hand;
import api.Player;

public class BlackJackPlayer implements Player {
	
	Strategy strat;
	
	private String name;
	public double balance;
	public Hand myHand;
	public int suitTotal;
	public double wager;

	public BlackJackPlayer(int i){
		this.setName(i);
		this.balance = 2500;
		this.suitTotal = 0;
		this.myHand = new BlackJackHand();
	}

	public void bet(){
		strat.bet();
	}
	
	public boolean hit(){
		return strat.hit();
	}
	
	public void setStrat(Strategy st) {
		strat = st;
	}
	
	
	@Override
	public int compareTo(Player o) {
		// general compare total value, -1 means o wins
		int result = myHand.compareTo(o.getHand());
		return result;
	}

	@Override
	public void receive(Card card) {		
		myHand.addCard(card);
		suitTotal += card.getSuit().getSuit();	
	}

	@Override
	public Hand getHand() {
		return myHand;
	}

	@Override
	public double wager() {
		return wager;
	}

	@Override
	public void payOut(double money) {
		
		balance = balance + money;
	}

	@Override
	public double getMoney() {
		return balance;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean requestCard() {
		// <17 request more cards
		if (this.myHand.valueOf() <= 17) {
			return true;
		} else {
			return false;
		}
		
	}

	public void setName(int i) {
		if (i == 0) {
			this.name = "Dealer";
		}else if (i%2 == 0) {
			this.name = "Crazy Player "+ Integer.toString(i/2);
		} else {
			this.name = "Conservative Player "+ Integer.toString((i+1)/2);
		}
		
	}	
	
}
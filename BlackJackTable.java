package impl;

import api.Player;
import java.math.*;
import api.Table;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


public class BlackJackTable extends Table{

	public BlackJackTable(int numberOfPlayers) {
		// TODO Auto-generated constructor stub
		this.players = new ArrayList<>();
		this.dealer = new BlackJackDealer(0);
		this.wagers = new HashMap<>();
		for (int i = 1; i <= numberOfPlayers; i++){
			if (i%2 == 0) {
				Player player = new CrazyPlayer(i);
				this.players.add(player);
			} else {
				Player player = new ConservativePlayer(i);
				this.players.add(player);
			}
			
		}
	}

	@Override
	public boolean isGameOver() {
		// if there is still one person that has money, the game is not over
		for (int i = 0; i < this.players.size(); i++){
			if (this.players.get(i).getMoney() > 0) {
				return false;
			}
		}
		System.out.println("Game is over");
		return true;
	}

	@Override
	public String toString() {
		// A string representation of the table   round the money for clean string representation     
		String str ="";
		for (int i = 0; i < this.players.size(); i++){
			str += this.players.get(i).getName() + ": $" +Double.toString(Math.round( (this.players.get(i).getMoney() * 100.0) / 100.0)) +"\n"; 
		}
		return str;
	}
	
	@Override
	protected void collectCards() {
		// remove player's cards & put back into the original deck
		List<Player> list = new ArrayList<Player>(this.players);
		list.add((Player)this.dealer);
	
		for (Player player: list) {
			this.dealer.collectCards(player);
		}
	}

	@Override
	protected void dealTable() {
		List<Player> list = new ArrayList<Player>(this.players);
		list.add((Player)this.dealer);
		for (Player player : list) {
			this.dealer.dealCard(player);
			// deal twice
			this.dealer.dealCard(player);
		}
		
	}

	@Override
	protected void collectBets() {
		// shuffle the deck
		for (int i=0; i < this.players.size(); i++) {
			if (this.players.get(i).getMoney() <= 0) {
				// this player should be marked for removal from the global list
					this.players.remove(i);
				}
		}
		
		if (!this.isGameOver()){
			this.dealer.shuffle();
			
			for (int i = 0; i < this.players.size(); i++){
				// if there is less than 400 dollars left, all players become conservative
				
				if (this.players.get(i).getMoney() <= 400) {
					((BlackJackPlayer) this.players.get(i)).setStrat(new ConservativeStrategy((BlackJackPlayer) this.players.get(i)));
				}
				
				// update wager table
				((BlackJackPlayer) this.players.get(i)).bet();
				
				this.wagers.put(this.players.get(i), this.players.get(i).wager());	
				
			}
		}
		
	}

	@Override
	protected void playerTurns() {
		// Give each player a turn to ask for more cards

		for (int i = 0; i < this.players.size(); i++) {
			while (((BlackJackPlayer) this.players.get(i)).hit()) {
				
				this.dealer.dealCard(this.players.get(i));
				
			}
		}
		while (((BlackJackPlayer) this.dealer).hit()) {
			
			this.dealer.dealCard((Player) this.dealer);
		}
		
	}

	@Override
	protected void playerEvaluations() {
		// find winner, initialize a winner list
		
		ArrayList<BlackJackPlayer> validList = new ArrayList<BlackJackPlayer>();
		
		for (int i = 0; i < this.players.size(); i++) {
			if (this.players.get(i).getHand().isValid()) {
				validList.add((BlackJackPlayer) this.players.get(i));
				System.out.println(this.players.get(i).getName()+ " has a valid hand");
			}
		}
		
		
		// at this point the winner list should be populated, ideally just one person
		if (validList.size() >= 1) {
			for (BlackJackPlayer bj : validList) {
				if (bj.compareTo((Player) this.dealer) == 1) {
					//player wins, get twice the money he/she puts in
					bj.payOut(bj.wager*2);
					System.out.println("For "+ bj.getName() + " : "+ "The winner is " + bj.getName());
					System.out.println(bj.getMoney() + " current balance");
				} else if (bj.compareTo((Player) this.dealer) == -1) {
					//dealer wins
					System.out.println("For "+ bj.getName() + " : "+ "Dealer wins");
				} else {
					System.out.println("For "+ bj.getName() + " : "+ "It's a tie with the dealer");
					// tie winner gets his/her original wager back
					bj.payOut(bj.wager);
					System.out.println(bj.getMoney() + " current balance");
				}
			}
		} else {
			System.out.println("Dealer wins");
		}
		
		//System.out.println(this.toString());
		
	}
	
}
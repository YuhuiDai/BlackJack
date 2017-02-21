package impl;

import api.Player;
import api.Strategy;

public class CrazyStrategy implements Strategy {
	BlackJackPlayer crazyPlayer;
	public CrazyStrategy(Player player){
		crazyPlayer = (BlackJackPlayer) player;
	}
	
	@Override
	public boolean hit() {
		// using 19 as a cut-off
		if ( crazyPlayer.myHand.valueOf() <= 19) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void bet() {
		// always bet 70% of his total balance
		crazyPlayer.wager = 0.7*crazyPlayer.balance;
		// XXX ensure that balance isn't less-than zero and augment wager if so
		if (crazyPlayer.balance < 50) {
			crazyPlayer.wager = crazyPlayer.balance;
			crazyPlayer.balance = 0;
		}else if(crazyPlayer.balance-crazyPlayer.wager >= 0) {
			crazyPlayer.balance -= crazyPlayer.wager;
		} 
	}
	
}

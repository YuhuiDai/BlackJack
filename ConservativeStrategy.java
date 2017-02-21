package impl;

import api.Strategy;
import api.Player;

public class ConservativeStrategy implements Strategy{
	BlackJackPlayer consePlayer;
	public ConservativeStrategy(Player player){
		consePlayer = (BlackJackPlayer) player;
	}
	
	@Override
	public boolean hit() {
		// use 17 as a cut-off
				if ( consePlayer.myHand.valueOf() <= 17) {
					return true;
				} else {
					return false;
				}	
	}

	@Override
	public void bet() {
		// always 10% of his balance
		consePlayer.wager = 0.1*consePlayer.balance;
		
		// XXX ensure that balance isn't less-than zero and augment wager if so
		if (consePlayer.balance < 50) {
			consePlayer.wager = consePlayer.balance;
			consePlayer.balance = 0;
		}else if(consePlayer.balance-consePlayer.wager >= 0) {
			consePlayer.balance -= consePlayer.wager;
		}
		
	}

}

package impl;

import api.Player;
import api.Strategy;

public class DealerStrategy implements Strategy{
	BlackJackDealer bjDealer;
	public DealerStrategy(Player player){
		bjDealer = (BlackJackDealer) player;
	}
	@Override
	public boolean hit() {
		// use 17 as a cut off
		if ( bjDealer.myHand.valueOf() <= 17) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void bet() {
		// TODO Auto-generated method stub
		
	}

}

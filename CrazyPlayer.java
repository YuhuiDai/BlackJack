package impl;

public class CrazyPlayer extends BlackJackPlayer {

	public CrazyPlayer(int name) {
		super(name);
		this.strat = new CrazyStrategy(this);
	}

}

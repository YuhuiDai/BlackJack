package impl;

public class ConservativePlayer extends BlackJackPlayer {

	public ConservativePlayer(int name) {
		super(name);
		this.strat = new ConservativeStrategy(this);
	}

}

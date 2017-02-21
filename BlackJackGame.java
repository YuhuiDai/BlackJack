package test;

import impl.BlackJackTable;
import api.Table;

/*
 * For now there is a single "test": to play a game until all players
 * are out of money (unless you're a Black Jack wizard, that point
 * should come).
 *
 * Use this test as a template for how the program should work;
 * essentially, everything is driven through the table.
 */
public class BlackJackGame {
    public static void main(String[] args) {
	int numberOfPlayers = Integer.parseInt(args[0]);
	
	Table table = new BlackJackTable(numberOfPlayers);
	for (int i = 0; !table.isGameOver(); i++) {
	    System.out.println("Round " + i);
	    table.round();
	    System.out.println(table);
	}
    }
}

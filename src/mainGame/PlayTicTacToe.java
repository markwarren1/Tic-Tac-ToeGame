package mainGame;

import java.util.ArrayList;

public class PlayTicTacToe {

	public static void main(String[] args) {
		//playGame();
		
		//Tests
		testprintEmptyBoard();

	}
	
	public static void playGame() {
//		global variables that keep track of game state
		int turn = (int) Math.round(Math.random());
		Board b = new Board(turn);
		boolean endGame = false;
		
//		game
		while(endGame != true) {
			b.toString();
			if(b.getTurn() == 1) {
				b.add(playerMove());
			}
			else {
				board.add(computerMove());
			}
			b.updateTurn();
		}
		
		
		
		
		//Helper Methods
		public static void updateTurn() {
			if(turn == 1) {
				turn = 0;
			}
			else {
				turn = 1;
			}
		}
		
		System.out.print("Hello");
	}
	
	public static void testprintEmptyBoard() {
		System.out.print("---Testing board toString---\n");
		Board b = new Board(1);
		System.out.print(b);
	}

}

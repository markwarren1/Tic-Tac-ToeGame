package mainGame;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayTicTacToe {

	public static void main(String[] args) {
		//playGame();
		
		//Tests
		testprintEmptyBoard();

	}
	
	public static void playGame() {
//		global variables that keep track of game state
		int turn = (int) Math.round(Math.random());
		Character letter = 'Z';
		boolean endGame = false;
		
		if(turn == 0) {
			letter = 'O';
		}
		if(turn == 1) {
			letter = 'X';
		}
		Board b = new Board(turn, letter);
		
//		RULES
		String rules = String.format("Computer will be - O\nPlayter is X\n");
		rules += ("Enter your move as a number (1-9)\n");
		rules += ("The moves correspond left to right on the board ex. 4 would be middle-left");
		System.out.println(rules);
		
//		game
		while(endGame != true) {
			b.toString();
			//Turn message
			b.turnIndicator();
			
			if(b.getTurn() == 1) {
				int playerMove = b.moveCheck();
				b.move(playerMove);
			}
			else {
				int computerMove = b.bestMove();
				b.move(computerMove);
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

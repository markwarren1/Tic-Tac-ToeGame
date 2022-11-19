package mainGame;

import java.util.Scanner;

public class PlayTicTacToe {

	public static void main(String[] args) {
		playGame();
		
		//Tests
		//testprintEmptyBoard();

	}
	
	public static void playGame() {
//		global variables that keep track of game state
		int turn = (int) Math.round(Math.random());
		Character letter = 'Z';
		boolean endGame = false;
		Scanner scnr = new Scanner(System.in);
		int q = -10;
		
		
		if(turn == 0) {
			letter = 'O';
		}
		if(turn == 1) {
			letter = 'X';
		}
		Board b = new Board(turn, letter);
		
//		RULES
		String rules = String.format("Computer will be O\nPlayer is X\n");
		rules += ("Enter your move as a number (1-9)\n");
		rules += ("The moves correspond left to right on the board ex. 4 would be middle-left");
		System.out.println(rules);
		
//		game
		while(endGame != true) {
			System.out.print(b.toString());
			//Turn message
			b.turnIndicator();
			
			if(b.getTurn() == 1) {
				boolean moveIsValid = false;
				while(moveIsValid == false) {
					q = b.inputForMove(scnr);
					if(b.moveCheck(q)) {
						moveIsValid = true;
					}
				}
				
				b.move(q - 1); // translates length from 1-9 to 0-8 user to array
			}
			else {
				int computerMove = b.bestMove();
				b.move(computerMove);
			}
			
			//check for end game
			if(b.xWin(b.getBoard())) {
				System.out.printf(b.toString() + "\nI don't know how you managed it but you won...");
				endGame = true;
			}
			if(b.oWin(b.getBoard())) {
				System.out.printf(b.toString() + "\nYou lost");
				endGame = true;
			}
			if(b.checkTie(b.getBoard()) && endGame == false) {
				System.out.printf(b.toString() + "\nIt's a tie", b.getTurnCharacter());
				endGame = true;
			}
			
			b.updateTurn();
		}
	}
	
	public static void testprintEmptyBoard() {
		System.out.print("---Testing board toString---\n");
		Board b = new Board(1, 'c');
		System.out.print(b);
	}

}

package mainGame;

import java.util.Arrays;
import java.util.Scanner;

public class Board {
	private Character[] board = new Character[9];
	private int turn = 0;
	private Character turnCharacter = 'O';
//	player is x and x is always turn 1
	
	public Board(int t, Character c) {
		this.turn = t;
		this.turnCharacter = c;
		
	}
	
	public int moveCheck() { //should only be for player
		Scanner scnr = new Scanner(System.in);
		boolean acceptInput = false;
		int index = -1;
		while(acceptInput == false) {
			index = scnr.nextInt();
			index = index - 1;
			try {
				index = index - 1;
				if(index > 8 || index < 0) {
					if(board[index] == 'X' || board[index] == 'O') {
						acceptInput = true;
					}
				}
			}
			catch(Exception e) {
				System.out.print("How hard can it be... 1-9");
			}
		}
		scnr.close();
		return index;
	}
	
	public void move(int move) {
		board[move] = getTurnCharacter();
	}
	
	//computer main
	public int bestMove() {
		Character[] tempBoard = getBoard().clone();
		int bestWeight = Integer.MIN_VALUE;
		int moveIndex = Integer.MIN_VALUE;
		
		for(int i = 0; i < tempBoard.length; i++) {
			if(tempBoard[i].equals(null)) { //for all the empty spots left in the game we will call the recursive weighting function
				int currentWeight = findWeight(tempBoard, i);
				if(currentWeight > bestWeight) {
					bestWeight = currentWeight;
					moveIndex = i;
				}
			}
		}
		return moveIndex;
		
	}
	
	//recursive call
	public int findWeight(Character[] b, int moveSpace) {
		Character t = getTurnCharacter();
		b[moveSpace] = t;
		updateTurn();
		int weight = 0;
		
		//base cases
		if(b[0].equals('t') && b[1].equals('t') && b[2].equals('t')) {
			return 1;
		}
		if(b[3].equals('t') && b[4].equals('t') && b[5].equals('t')) {
			return 1;
		}
		if(b[6].equals('t') && b[7].equals('t') && b[8].equals('t')) {
			return 1;
		}
		if(b[0].equals('t') && b[3].equals('t') && b[6].equals('t')) {
			return 1;
		}
		if(b[1].equals('t') && b[4].equals('t') && b[7].equals('t')) {
			return 1;
		}
		if(b[2].equals('t') && b[5].equals('t') && b[8].equals('t')) {
			return 1;
		}
		if(b[0].equals('t') && b[4].equals('t') && b[8].equals('t')) {
			return 1;
		}
		if(b[2].equals('t') && b[4].equals('t') && b[6].equals('t')) {
			return 1;
		}
		
		for(int i = 0; i < b.length; i++) {
			if(b[i].equals(null)) {
				weight += findWeight(b, i);
			}
		}
		return weight;
		
	}
	
	
	public int getTurn() {
		return turn;
	}
	
	public void updateTurn() {
		if(turn == 0) {
			turn = 1;
			setTurnCharacter('X');
		}
		else {
			turn = 0;
			setTurnCharacter('O');
		}
	}
	
	public Character[] getBoard() {
		return board;
	}
	
	public Character getTurnCharacter() {
		return turnCharacter;
	}

	public void setTurnCharacter(Character turnCharacter) {
		this.turnCharacter = turnCharacter;
	}
	
	public void turnIndicator() {
		String turnIndicator = String.format("It is %c's turn\n", getTurnCharacter());
		System.out.println(turnIndicator);
	}

	@Override
	public String toString() {
		String msg = String.format("\n");
		msg += String.format("|%c|%c|%c|\n", board[0], board[1], board[2]);
		msg += String.format("------------\n");
		msg += String.format("|%c|%c|%c|\n", board[3], board[4], board[5]);
		msg += String.format("------------\n");
		msg += String.format("|%c|%c|%c|\n", board[6], board[7], board[8]);
		return msg;
	}
}

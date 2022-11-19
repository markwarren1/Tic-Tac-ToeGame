package mainGame;

import java.util.Scanner;

public class Board {
	private Character[] board = {'_', '_', '_', '_', '_', '_', '_', '_', '_'};
	private int turn = 0;
	private Character turnCharacter = 'Z';
	private Character ArtificalTC = 'Z';
//	player is x and x is always turn 1
	
	public Board(int t, Character c) {
		this.turn = t;
		this.turnCharacter = c;
		this.ArtificalTC = c;
		
	}
	
	public int inputForMove(Scanner c) {
		if(c.hasNextInt()) {
			return c.nextInt();
		}
		else {
			System.out.print("Not a valid input");
			return -10;
		}
	}
	
	public boolean moveCheck(int move) { //should only be for player
			if(move < 9 || move >= 0) {
				if(board[move -1].equals('_')) {
					return true;
				}
			}
		return false;
	}
	
	public void move(int move) {
		board[move] = getTurnCharacter();
	}
	
	//computer main
	public int bestMove() {
		int bestWeight = Integer.MIN_VALUE;
		int moveIndex = Integer.MIN_VALUE;
		
		for(int i = 0; i < board.length; i++) {
			if(board[i].equals('_')) { //for all the empty spots left in the game we will call the recursive weighting function
				board[i] = 'O';
				updateArtificalTC();
				int currentWeight = findWeight(board, false); //for any given move of the computer, what is the boardest one?
				board[i] = '_';
				if(currentWeight > bestWeight) {
					bestWeight = currentWeight;
					moveIndex = i;
				}
			}
		}
		return moveIndex;
		
	}
	
	//recursive call
	public int findWeight(Character[] b, boolean isMax) {
		//base cases
		if(checkTie(b) == true) {
			return 0;
		}
		if(oWin(b) == true) { //win for Computer
			return 15;
		}
		if(xWin(b) == true) { //win for Computer
			return -15;
		}
		
		//recursive
		//  Computer, find the maximum val.
        if (isMax) {
            int highestVal = Integer.MIN_VALUE;
            for (int index = 0; index < b.length; index++) {
            	if(b[index] == '_') {
                board[index] = 'O';
                highestVal = Math.max(highestVal, findWeight(board, false));
                board[index] = '_';
                }
            }
            return highestVal;
        }
            // Player, find minimum val;
        else {
        	int lowestVal = Integer.MAX_VALUE;
            for (int index = 0; index < b.length; index++) {
            	if(b[index] == '_') {
                board[index] = 'X';
                lowestVal = Math.min(lowestVal, findWeight(board, true));
                board[index] = '_';
                }
            }
            return lowestVal;
        }
	}
	
	//check win
	public boolean xWin(Character[] b) {
		if(b[0] == ('X') && b[1] == ('X') && b[2] == ('X')) {
			return true;
		}
		if(b[3] == ('X') && b[4] == ('X') && b[5] == ('X')) {
			return true;
		}
		if(b[6] == ('X') && b[7] == ('X') && b[8] == ('X')) {
			return true;
		}
		if(b[0] == ('X') && b[3] == ('X') && b[6] == ('X')) {
			return true;
		}
		if(b[1] == ('X') && b[4] == ('X') && b[7] == ('X')) {
			return true;
		}
		if(b[2] == ('X') && b[5] == ('X') && b[8] == ('X')) {
			return true;
		}
		if(b[0] == ('X') && b[4] == ('X') && b[8] == ('X')) {
			return true;
		}
		if(b[2] == ('X') && b[4] == ('X') && b[6] == ('X')) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean oWin(Character[] b) {
		if(b[0] == ('O') && b[1] == ('O') && b[2] == ('O')) {
			return true;
		}
		if(b[3] == ('O') && b[4] == ('O') && b[5] == ('O')) {
			return true;
		}
		if(b[6] == ('O') && b[7] == ('O') && b[8] == ('O')) {
			return true;
		}
		if(b[0] == ('O') && b[3] == ('O') && b[6] == ('O')) {
			return true;
		}
		if(b[1] == ('O') && b[4] == ('O') && b[7] == ('O')) {
			return true;
		}
		if(b[2] == ('O') && b[5] == ('O') && b[8] == ('O')) {
			return true;
		}
		if(b[0] == ('O') && b[4] == ('O') && b[8] == ('O')) {
			return true;
		}
		if(b[2] == ('O') && b[4] == ('O') && b[6] == ('O')) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean checkTie(Character[] b) {
		for(int i = 0; i < b.length; i++) {
			if (b[i] == '_') {
				return false;
			}
		}
		return true;
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
	
	public void updateArtificalTC() {
		if(getArtificalTC() == 'X') {
			ArtificalTC = 'O';
		}
		else {
			ArtificalTC = 'X';
		}
	}
	
	
	
	//getters setters and toString
	
	public int getTurn() {
		return turn;
	}
	
	public Character[] getBoard() {
		return board;
	}
	
	public Character getTurnCharacter() {
		return turnCharacter;
	}
	
	public Character getArtificalTC() {
		return ArtificalTC;
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
		msg += String.format("--------\n");
		msg += String.format("|%c|%c|%c|\n", board[3], board[4], board[5]);
		msg += String.format("--------\n");
		msg += String.format("|%c|%c|%c|\n", board[6], board[7], board[8]);
		return msg;
	}
}

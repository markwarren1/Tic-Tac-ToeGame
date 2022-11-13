package mainGame;


public class Board {
	Character[] board = new Character[9];
	private int turn = 0;
//	player is x and x is always turn 1
	
	public Board(int t) {
		this.turn = t;
	}
	
	public boolean playerMove(int space) {
		int index = space - 1;
		
		if(index > 8 || index < 0) {
			return false;
		}
		if(board[index].equals('X') || board[index].equals('O')) {
			return false;
		}
		board[index] = 'X';
		return true;
	}
	
	
	
	public int getTurn() {
		return turn;
	}
	
	public void updateTurn() {
		if(turn == 0) {
			turn = 1;
		}
		else {
			turn = 0;
		}
	}
	
	@Override
	public String toString() {
		String msg = String.format("_________\n");
		msg += String.format("|%c|%c|%c|", board[0], board[1], board[2]);
		return msg;
	}
}


import java.util.Scanner;

public class TicTacToe {
	private char[] board;

	TicTacToePlayer player1;
	TicTacToePlayer player2;
	private static final char EMPTY = '.';
	private TicTacToePlayer current;
	private Scanner sc;
	public void run() {
		while(canMove()){
			drawboard();
			makeMove();
		}
		
		drawboard();
		printGameEndMessage();
	}
	
	private void printGameEndMessage() {
		if(getWon() != EMPTY) {
			System.out.println(getWon() + " has won!");
		} else {
			System.out.println("The game was a draw!");
		}
	}

	private void makeMove() {
		boolean hasMoved = false;
		do {
			System.out.println("Please make a move for " + current + " (0-8)");
			
			int move = sc.nextInt();
			if(move < 0 || move > 8) {
				System.out.println("Please make a move between 0 and 8.");
			} else if (board[move] != EMPTY){
				System.out.println("Please make a move on an empty square.");
			} else {
				board[move] = current.getSymbol();
				if(current == player1) current = player2;
				else current = player1;
				hasMoved = true;
			}
		} while (!hasMoved);
	}
	
	private boolean canMove() {
		return !isDraw() && (getWon() == EMPTY);
	}

	private char getWon() {
		char win;
		char winningMove = EMPTY;
		//Check Diagonals
		if(board[4] != EMPTY) {
			if(board[0] == board[4] && board[4] == board[8]) winningMove = board[4];
			if(board[2] == board[4] && board[4] == board[6]) winningMove = board[4];
		}
			
		//Check Rows
		for(int row = 0; row < 3; row++) {
			if(
					board[row*3+0] != EMPTY &&
					board[row*3+0] == board[row*3+1] &&
					board[row*3+0] == board[row*3+2]		
					) winningMove = board[row*3+0];
		}
			
		
		//Check Columns
		for(int col = 0; col < 3; col++) {
			if(
					board[0*3+col] != EMPTY &&
					board[0*3+col] == board[1*3+col] &&
					board[0*3+col] == board[2*3+col]		
					) winningMove = board[0*3+col];
		}
		win = winningMove;
		return winningMove;
	}

	private boolean isDraw() {
		boolean full = true;
		for(char c: board) {
			if(c==EMPTY) {
				full = false;
				break;
			}
		}
		return full;
	}
	private void drawboard() {
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				System.out.print(board[row*3+col]);
			}
			System.out.println();
		}
	}

	public TicTacToe(TicTacToePlayer player1, TicTacToePlayer player2) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		sc = new Scanner(System.in);
		board = new char[9];
		current = player1;
		for(int i = 0; i < board.length; i++) board[i] = EMPTY;
	}


}

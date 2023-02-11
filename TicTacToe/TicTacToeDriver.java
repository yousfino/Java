
public class TicTacToeDriver {

	public static void main(String[] args) {
		TicTacToePlayer p1 = new TicTacToePlayer("Player1", 'X');
		TicTacToePlayer p2 = new TicTacToePlayer("Player2", 'O');
		TicTacToe game = new TicTacToe(p1, p2);
		game.run();
	}

}
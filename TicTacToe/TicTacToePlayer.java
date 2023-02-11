
public class TicTacToePlayer extends Player{
	char symbol;

	public char getSymbol() {
		return symbol;
	}

	public TicTacToePlayer(String name, char symbol) {
		super(name);
		this.symbol = symbol;
	}

	
}

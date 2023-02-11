
public class Player {
	private String name;

	public String getName() {
		return name;
	}

	public Player(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
}

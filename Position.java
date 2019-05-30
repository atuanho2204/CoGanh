public class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isValid() {
		return (0 <= x && x < 5 && 0 <= y && y < 5);
	}
}
public class Piece {
	private Position position;

	public enum Color {WHITE, BLACK, NONE}
	private Color color;

	public Piece(Position position, Color color) {
		this.position = position;
		this.color = color;
	}

	public Position getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String toString() {
		switch(color) {
			case WHITE:
				return "W";

			case BLACK:
				return "B";

			default:
				return ".";
		}
	}
}
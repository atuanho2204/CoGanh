public class Piece {
	private Position position;
	private String color;

	public Piece(Position position, String color) {
		this.position = position;
		this.color = color;
	}

	public Position getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}

}
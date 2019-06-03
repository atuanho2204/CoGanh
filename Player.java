public class Player {
	private String name;
	private Piece.Color color;

	public void setPlayer(String name, Piece.Color color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public Piece.Color getColor() {
		return color;
	}

	public Piece getPiece(Board board, int x, int y) {
		return board.getPiece(new Position(x, y));
	}

	public Piece[][] getBoard(Board board) {
		return board.getBoard();
	}

	// return false if the move is invalid, else return true
	public boolean makeMove(Board board, int oldX, int oldY, int newX, int newY) {
		
		// return false if the old piece does not belong to this player
		if (board.getPiece(new Position(oldX, oldY)).getColor() != color) {
			return false;
		}

		return board.makeMove(new Move(board, new Position(oldX, oldY), new Position(newX, newY)));
	}
}
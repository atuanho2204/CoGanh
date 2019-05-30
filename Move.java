public class Move {
	private Position oldPos;
	private Position newPos;
	private Board board;

	public Move(Board board, Position oldPos, Position newPos)	{
		this.oldPos = oldPos;
		this.newPos = newPos;
		this.board = board;
	}

	public boolean isValid() {
		// false if invalid positions
		if (!oldPos.isValid() || !newPos.isValid()) {
			return false;
		}

		// false if newPos already has a piece
		if (board.getPiece(newPos).getColor() != Piece.Color.NONE) {
			return false;
		}

		// 


		return true;
	}
}
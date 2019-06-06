public class Move {
	private Position oldPos;
	private Position newPos;
	private Board board;

	public Move(Board board, Position oldPos, Position newPos)	{
		this.oldPos = oldPos;
		this.newPos = newPos;
		this.board = board;
	}

	public Position getOldPosition() {
		return oldPos;
	}

	public Position getNewPosition() {
		return newPos;
	}

	public boolean isValid() {
		// false if invalid positions
		if (!oldPos.isValid() || !newPos.isValid()) {
			// System.out.println("isValidksdfisj is invalid");
			return false;
		}

		// false if newPos already has a piece
		if (board.getPiece(newPos).getColor() != Piece.Color.NONE) {
			// System.out.println("colokor is invalid");
			return false;
		}
		// System.out.println("o123");
		// true if move in the same colume
		if (oldPos.getX() == newPos.getX() && Math.abs(oldPos.getY() - newPos.getY()) == 1 ) {
			// System.out.println(?)
			return true;
		}

		// true if move in the same row
		if (oldPos.getY() == newPos.getY() && Math.abs(oldPos.getX() - newPos.getX()) == 1 )	{
			return true;
		}

		// check if move in the diagonal 
		if(Math.abs(oldPos.getX() - newPos.getX()) == 1 && Math.abs(oldPos.getY() - newPos.getY()) == 1) {
			if (((oldPos.getX() % 2 == 0) && (oldPos.getY() % 2 == 0)) || ((oldPos.getX() % 2 == 1) && (oldPos.getY() % 2 == 1)))	{
				return true; // if coordinates of old position are both odd or even, return true when the piece moves on the diagonal 
			} else {
				return false; // else return false when the piece moves on the diagonal
			}
		}  

		return false;
	}

	public boolean isValidForBlockedPieces()	{
		if (!oldPos.isValid() || !newPos.isValid()) {
			// System.out.println("isValidksdfisj is invalid");
			return false;
		}

		// true if move in the same colume
		if (oldPos.getX() == newPos.getX() && Math.abs(oldPos.getY() - newPos.getY()) == 1 ) {
			return true;
		}

		// true if move in the same row
		if (oldPos.getY() == newPos.getY() && Math.abs(oldPos.getX() - newPos.getX()) == 1 )	{
			return true;
		}

		// check if move in the diagonal 
		if(Math.abs(oldPos.getX() - newPos.getX()) == 1 && Math.abs(oldPos.getY() - newPos.getY()) == 1) {
			if (((oldPos.getX() % 2 == 0) && (oldPos.getY() % 2 == 0)) || ((oldPos.getX() % 2 == 1) && (oldPos.getY() % 2 == 1)))	{
				return true; // if coordinates of old position are both odd or even, return true when the piece moves on the diagonal 
			} else {
				return false; // else return false when the piece moves on the diagonal
			}
		}  

		return false;

	}
}
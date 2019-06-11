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
		
		// false if newPos already has a piece
		if (isValidPosition() == true && isExistPiece() == true && isConnected() == true)	{
			return true;
		}

		return false;
	}

	public boolean isValidPosition()	{
		if (!oldPos.isValid() || !newPos.isValid()) {
			return false;
		}
		return true;
	}

	public boolean isExistPiece()	{

		if ( isValidPosition() == true && (board.getPiece(newPos).getColor() != Piece.Color.NONE)) {
			return false;
		}
		return true;
	}

	public boolean isConnected()	{

		if (isValidPosition() == true && (isConnectedColume() == true || isConnectedRow() == true || isConnectedDiagonal() == true))	{
			return true;
		}
		return false;
	}

	public boolean isConnectedColume()	{

		// true if move in the same colume
		if (oldPos.getX() == newPos.getX() && Math.abs(oldPos.getY() - newPos.getY()) == 1 ) {
			return true;
		}
		return false;
	} 
	public boolean isConnectedRow()	{

		// true if move in the same row
		if (oldPos.getY() == newPos.getY() && Math.abs(oldPos.getX() - newPos.getX()) == 1 )	{
			return true;
		}
		return false;
	}

	public boolean isConnectedDiagonal()	{

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
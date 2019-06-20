import java.util.ArrayList;
public class NewRuleBoard extends Board {
	// public ArrayList<Move> checkForceToEat(Piece.Color color) {
	// 	ArrayList<Move> checkForceToEat = new ArrayList<Move>();
	// 	for (Move validMove : allValidMoves(color)) {
	// 		if (validMove.foo()) {
	// 			checkForceToEat.add(validMove);
	// 		}
	// 	}
	// 	return checkForceToEat;
	// }
	private Move a;
	private ArrayList<Move> check = new ArrayList<Move>();
	public void checkForceToEat(Piece.Color color) {
		for (Move validMove : allValidMoves(color)) {
			if (validMove.foo()) {
				a = validMove;
				check.add(a);
			}
		}
	}
	public boolean makeMove(Move move) {
		
		allValidMoves(color);
		if (checkForceToEat(color).isEmpty()) {
			if (allValidMoves.contains(move)) {
				changeBoard();
				return true;
			}
		} else {
			if (checkForceToEat.contains(move))	{
				changeBoard();
				return true;
			}
		}
		return false;
	}
	public boolean foo() {
		super.eatable();
	}
}
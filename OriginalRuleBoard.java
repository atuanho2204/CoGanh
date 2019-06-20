public class OriginalRuleBoard extends Board {
	public boolean makeMove(Move move) {
		super(move);
		if (allValidMoves.contains(move)) {
			changeBoard();
			return true;
		}
		return false;
	}
}
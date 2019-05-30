public class Main {
	public static void main(String[] args) {
		Board board = new Board();
		System.out.println((new Move(board, new Position(0, 1), new Position(0, 0))).isValid());
		System.out.println((new Move(board, new Position(1, 1), new Position(3, 3))).isValid());
		System.out.println((new Move(board, new Position(0, 1), new Position(1, 2))).isValid());
		System.out.println((new Move(board, new Position(1, 2), new Position(2, 1))).isValid());
	}
}	
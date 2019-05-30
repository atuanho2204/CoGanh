public class Board {
	private Piece[][] grid = new Piece[5][5];

	public Board()	{
		for (int x = 0; x < 5; x++)	{
			createPiece(x, 0 , Piece.Color.WHITE);
		}
		createPiece(0, 1, Piece.Color.WHITE);
		createPiece(4, 1, Piece.Color.WHITE);
		createPiece(4, 2, Piece.Color.WHITE);
		for (int x = 0; x < 5; x++)	{
			createPiece(x, 4, Piece.Color.BLACK);
		}
		createPiece(0, 3, Piece.Color.BLACK);
		createPiece(4, 3, Piece.Color.BLACK);
		createPiece(0, 2, Piece.Color.BLACK);
		for( int x = 1; x < 4; x++)	{
			for( int y = 1; y < 4; y++)	{
				createPiece(x, y, Piece.Color.NONE);
			}
		}

		

	}
	private void createPiece(int x, int y,  Piece.Color color)	{
		grid[x][y] = new Piece(new Position(x,y), color);
	}
	private final int NUM_ROWS = 5;
	private final int NUM_COLS = 5;

	public void print() {
		for (int y = 0; y < NUM_COLS; y++) {
			for (int x = 0; x < NUM_ROWS; x++) {
				System.out.print(grid[x][y]);
			}
			System.out.println();
		} 
	} 
}
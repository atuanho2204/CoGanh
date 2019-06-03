public class Board {
	private Piece[][] grid = new Piece[5][5];
	private final int NUM_ROWS = 5;
	private final int NUM_COLS = 5;

	public Board()	{
		// first row: all White
		for (int x = 0; x < 5; x++)	{
			createPiece(x, 0 , Piece.Color.WHITE);
		}

		// the rest White
		createPiece(0, 1, Piece.Color.WHITE);
		createPiece(4, 1, Piece.Color.WHITE);
		createPiece(4, 2, Piece.Color.WHITE);
		
		// last row: all Black
		for (int x = 0; x < 5; x++)	{
			createPiece(x, 4, Piece.Color.BLACK);
		}

		// the rest Black
		createPiece(0, 3, Piece.Color.BLACK);
		createPiece(4, 3, Piece.Color.BLACK);
		createPiece(0, 2, Piece.Color.BLACK);
		
		// the rest: None
		for( int x = 1; x < 4; x++)	{
			for( int y = 1; y < 4; y++)	{
				createPiece(x, y, Piece.Color.NONE);
			}
		}
	}

	// create a piece at (x, y) with color 
	private void createPiece(int x, int y,  Piece.Color color)	{
		grid[x][y] = new Piece(new Position(x,y), color);
	}

	// print the board
	public void print() {
		for (int y = 0; y < NUM_COLS; y++) {
			for (int x = 0; x < NUM_ROWS; x++) {
				System.out.print(grid[x][y]);
			}
			System.out.println();
		} 
	} 
	
	public Piece getPiece(Position pos) {
		return grid[pos.getX()][pos.getY()];
	}	

	public Piece[][] getBoard() {
		return grid;
	}

	// return false if invalid, true if valid
	public boolean makeMove(Move move) {
		System.out.println("Hello");
		if (!move.isValid()) return false;
		// change the board
		swap(move.getOldPosition().getX(), move.getOldPosition().getY(), move.getNewPosition().getX(), move.getNewPosition().getY() );
		return true;



	}

	private void swap(int oldX, int oldY, int newX, int newY)	{
		Piece temp = grid[oldX][oldY];
		grid[oldX][oldY] = grid[newX][newY];
		grid[newX][newY] = temp;

	}
}
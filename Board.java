import java.util.Queue;
import java.util.LinkedList; 

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
		System.out.print("  ");
		for (int i = 0; i < 5; i++) {
			System.out.print(i + " ");
		}
		System.out.println("-- x");

		for (int y = 0; y < NUM_COLS; y++) {
			System.out.print(y + " ");
			for (int x = 0; x < NUM_ROWS; x++) {
				System.out.print(grid[x][y] + " ");
			}
			System.out.println();
		} 
		System.out.println("|");
		System.out.println("y");
	} 
	
	public Piece getPiece(Position pos) {
		return grid[pos.getX()][pos.getY()];
	}	

	public Piece[][] getBoard() {
		return grid;
	}

	// return false if invalid, true if valid
	public boolean makeMove(Move move) {
		// System.out.println("Hello");
		if (!move.isValid()) return false;
		// change the board
		swap(move.getOldPosition().getX(), move.getOldPosition().getY(), move.getNewPosition().getX(), move.getNewPosition().getY() );
		int newX = move.getNewPosition().getX();
		int newY = move.getNewPosition().getY(); 

		// System.out.println(grid[newX - 1][newY].getColor()+"  "+getOppositeColor(grid[newX][newY]) + " " + grid[newX + 1][newY].getColor());
		// System.out.println(newX +" " + newY); 

		if (newX >= 1 && newX <= 3 && grid[newX - 1][newY].getColor() == getOppositeColor(grid[newX][newY]) && grid[newX + 1][newY].getColor() == getOppositeColor(grid[newX][newY])  ) {
			
			grid[newX - 1][newY].setColor(grid[newX][newY].getColor());
			grid[newX + 1][newY].setColor(grid[newX][newY].getColor());
		}
		if (newY >= 1 && newY <= 3 && grid[newX][newY - 1].getColor() == getOppositeColor(grid[newX][newY]) && grid[newX][newY + 1].getColor() == getOppositeColor(grid[newX][newY])  ) {
			grid[newX][newY - 1].setColor(grid[newX][newY].getColor());
			grid[newX][newY + 1].setColor(grid[newX][newY].getColor());
		}

		boolean[][] canEatDiagonal = new boolean[5][5];
		canEatDiagonal[1][1] = true;
		canEatDiagonal[3][1] = true;
		canEatDiagonal[2][2] = true;
		canEatDiagonal[1][3] = true;
		canEatDiagonal[3][3] = true;
		if(canEatDiagonal[newX][newY]) {
			if( grid[newX - 1][newY - 1].getColor() == getOppositeColor(grid[newX][newY]) && grid[newX + 1][newY + 1].getColor() == getOppositeColor(grid[newX][newY])  ) {
				grid[newX - 1][newY - 1].setColor(grid[newX][newY].getColor());
				grid[newX + 1][newY + 1].setColor(grid[newX][newY].getColor());
			}
			if( grid[newX - 1][newY + 1].getColor() == getOppositeColor(grid[newX][newY]) && grid[newX + 1][newY - 1].getColor() == getOppositeColor(grid[newX][newY])  ) {
				grid[newX - 1][newY + 1].setColor(grid[newX][newY].getColor());
				grid[newX + 1][newY - 1].setColor(grid[newX][newY].getColor());
			}
		}

		// eat all pieces which cannot escape 
		Piece.Color oppositeColor = getOppositeColor(grid[newX][newY]);
		boolean[][] isAlive = new boolean[5][5];
		Queue<Piece> queue = new LinkedList<>();

		for(int x = 0; x < 5; x++)	{
			for(int y = 0; y < 5; y++)	{

				// only consider piece with opposite color
				if (grid[x][y].getColor() == oppositeColor) {
					for (int x1 = 0; x1 < 5; x1++) {
						for (int y1 = 0; y1 < 5; y1++) {
							Position oldPos = new Position(x, y);
							Position newPos = new Position(x1, y1);

							// check if oldPos can move to newPos
							if ((new Move(this, oldPos, newPos)).isValid() && grid[x1][y1].getColor() == Piece.Color.NONE) {
								// oldPos can move
								isAlive[x][y] = true;
								queue.add(grid[x][y]);

								System.out.println(x + " " + y);
							} 
						}
					}
				}
			}
		}

		// while queue.size > 0
		// 	Piece top = queue.peek()
		// 	queue.remove()

		// 	top.x, top.y
		// 	for all (x1,y1):
		// 		if top can move to (x1, y1) and (x1, y1) is opposite color
		// 			if (isAlive[x1][y1] == false) 
		// 				isAlive[x1][y1] = true
		// 				queue.add(grid[x1][y1])



		return true;
	}


	private Piece.Color getOppositeColor(Piece p) {
		if(p.getColor() == Piece.Color.BLACK)	{
			return Piece.Color.WHITE;
		}
		if(p.getColor() == Piece.Color.WHITE)	{
			return Piece.Color.BLACK;
		}
		return Piece.Color.NONE;
	}
	private void swap(int oldX, int oldY, int newX, int newY)	{
		Piece temp = grid[oldX][oldY];
		grid[oldX][oldY] = grid[newX][newY];
		grid[newX][newY] = temp;

	}
}
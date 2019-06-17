import java.util.Queue;
import java.util.LinkedList; 

public class Board {
	private Piece[][] grid;
	private final int NUM_ROWS = 5;
	private final int NUM_COLS = 5;
	private Move lastMove;
	// public int checkIfWin = 0;
	// public int checkIfForceToEat = 0;
	// public static int forceNewX = 0;
	// public static int forceNewY = 0;
	// public static int forceOldX = 0;
	// public static int forceOldY = 0;
	// public static int getAnswer = 0;
	// public static int applyForceToEat = 0;

	public Board() {
		grid = new Piece[5][5];
	}

	public void setUpBoard() {

		//first row: all White
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
	public void createPiece(int x, int y,  Piece.Color color)	{
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

	// return false if invalid, true otherwise
	public boolean makeMove(Move move); 

	// change the board after every valid move
	public void changeBoard() {
		swap(move.getOldPosition().getX(), move.getOldPosition().getY(), move.getNewPosition().getX(), move.getNewPosition().getY() );

		// ---- eat column
		if (newX >= 1 && newX <= 3 && grid[newX - 1][newY].getColor() == getOppositeColor(grid[newX][newY]) && grid[newX + 1][newY].getColor() == getOppositeColor(grid[newX][newY])  ) {
			grid[newX - 1][newY].setColor(grid[newX][newY].getColor());
			grid[newX + 1][newY].setColor(grid[newX][newY].getColor());
		}

		// ---- eat row
		if (newY >= 1 && newY <= 3 && grid[newX][newY - 1].getColor() == getOppositeColor(grid[newX][newY]) && grid[newX][newY + 1].getColor() == getOppositeColor(grid[newX][newY])  ) {
			grid[newX][newY - 1].setColor(grid[newX][newY].getColor());
			grid[newX][newY + 1].setColor(grid[newX][newY].getColor());
		}

		// ---- eat diagonal

		// five positions that can eat diagonal
		boolean[][] canEatDiagonal = new boolean[5][5];
		canEatDiagonal[1][1] = true; 
		canEatDiagonal[3][1] = true;
		canEatDiagonal[2][2] = true;
		canEatDiagonal[1][3] = true;
		canEatDiagonal[3][3] = true;

		// check eatable
		if(canEatDiagonal[newX][newY]) {
			if(grid[newX - 1][newY - 1].getColor() == getOppositeColor(grid[newX][newY]) && grid[newX + 1][newY + 1].getColor() == getOppositeColor(grid[newX][newY])  ) {
				grid[newX - 1][newY - 1].setColor(grid[newX][newY].getColor());
				grid[newX + 1][newY + 1].setColor(grid[newX][newY].getColor());
			}
			if(grid[newX - 1][newY + 1].getColor() == getOppositeColor(grid[newX][newY]) && grid[newX + 1][newY - 1].getColor() == getOppositeColor(grid[newX][newY])  ) {
				grid[newX - 1][newY + 1].setColor(grid[newX][newY].getColor());
				grid[newX + 1][newY - 1].setColor(grid[newX][newY].getColor());
			}
		}

		// process after moving

		killBlockedPieces(newX, newY);
		checkIfWin(newX, newY); // comments? 
	}

	// return false if invalid, true if valid
	public boolean makeMove(Move move) {
		if (!move.isValid()) return false;

		int newX = move.getNewPosition().getX();
		int newY = move.getNewPosition().getY();
		int oldX = move.getOldPosition().getX();
		int oldY = move.getOldPosition().getY();

		if (getAnswer == 1)	{
			if (applyForceToEat == 1) {
				Position forceOldPos = new Position(oldX,oldY);
				Position forceNewPos = new Position(forceNewX,forceNewY);
				if (!new Move(this, forceOldPos, forceNewPos).isConnected())	{
					System.out.println("You have to pick right piece due to ForceToEatRule" );
					return false;
				}
				if (newX != forceNewX && newY != forceNewY )	{
					System.out.println("You have to move to " + forceNewX + " " + forceNewY);
					return false;
				}
				// if (oldX != forceOldX && oldY != forceOldY)	{
				// 	System.out.println("You have to pick " + forceOldX + " " + forceOldY);
				// 	return false;
				// }
			}
		}
		
		return true;
	}

	// return 0 if no one won
	// return 1 if first player won
	// return 2 if second player won
	private int checkIfWin(int newX, int newY)	{
		int count = 0;

		for (int x = 0; x < 5; x++)	{
			for (int y = 0; y < 5; y++)	{
				if (grid[x][y].getColor() == getOppositeColor(grid[newX][newY])) {
					count++;
				}
			}
		}

		if (count == 0)	{
			checkIfWin = 1;
		}
	}

	private void killBlockedPieces(int newX, int newY)	{
		Piece.Color oppositeColor = getOppositeColor(grid[newX][newY]);
		boolean[][] isAlive = new boolean[5][5];
		Queue<Piece> queue = new LinkedList<>();

		// algorithm??? BFS

		// find all open pieces
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
							}
						}
					}
				}
			}
		}

		// find all alive pieces		
		while (!queue.isEmpty())	{
			Piece top = queue.peek();
			queue.remove();

			int topX = top.getPosition().getX();
			int topY = top.getPosition().getY();
			
			for (int x1 = 0; x1 < 5; x1++) {
				for (int y1 = 0; y1 < 5; y1++) {
					Position oldPos = new Position(topX, topY);
					Position newPos = new Position(x1, y1);

					// check if oldPos is relate to newPos
					if ((new Move(this, oldPos, newPos)).isConnected() && grid[x1][y1].getColor() == oppositeColor ) {
						// oldPos can move
						if(isAlive[x1][y1] == false)	{

							isAlive[x1][y1] = true;
							queue.add(grid[x1][y1]);

						}
					} 
				}
			}
		}

		// kill blocked pieces 
		for (int x2 = 0; x2 < 5; x2++)	{
			for (int y2 = 0; y2 < 5; y2++)	{
				if (isAlive[x2][y2] == false && grid[x2][y2].getColor() == oppositeColor)	{
					grid[x2][y2].setColor(grid[newX][newY].getColor());
				}
			}
		}
	}
	public void forceToEatRule(int newX, int newY) 	{
		forceNewX = newX;
		forceNewY = newY;
	}


	public boolean checkOtherPieceIsConnected (int newX, int newY) {
		boolean check = checkThreeSamePieces(newX, newY);
		if (check) {
			for (int x1 = 0; x1 < 5; x1++) {
				for (int y1 = 0; y1 < 5; y1++) {
					Position oldPos = new Position(newX, newY);
					Position newPos = new Position(x1, y1);

					// check if oldPos is relate to newPos
					if ((new Move(this, oldPos, newPos)).isConnected() && grid[newX][newY].getColor() == getOppositeColor(grid[x1][y1]))	{
						return true;
					}
				}
			}
		}
		return false;
	}
		
	public boolean checkThreeSamePieces (int newX, int newY)	{
		
		if (newX >= 1 && newX <= 3 && grid[newX - 1][newY].getColor() == grid[newX][newY].getColor() && grid[newX + 1][newY].getColor() == grid[newX][newY].getColor() )	{
			return true;
		}

		if (newY >= 1 && newY <= 3 && grid[newX][newY - 1].getColor() == grid[newX][newY].getColor() && grid[newX][newY + 1].getColor() == grid[newX][newY].getColor() )	{
			return true;
		}

		boolean[][] canEatDiagonal = new boolean[5][5];
		canEatDiagonal[1][1] = true;
		canEatDiagonal[3][1] = true;
		canEatDiagonal[2][2] = true;
		canEatDiagonal[1][3] = true;
		canEatDiagonal[3][3] = true;
		if(canEatDiagonal[newX][newY]) {
			if( grid[newX - 1][newY - 1].getColor() == grid[newX][newY].getColor() && grid[newX + 1][newY + 1].getColor() == grid[newX][newY].getColor()  ) {
				return true;
			}
			if( grid[newX - 1][newY + 1].getColor() == grid[newX][newY].getColor() && grid[newX + 1][newY - 1].getColor() == grid[newX][newY].getColor()  ) {
				return true;
			}
		}
		return false;
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

	public void makeBoardForTestGame(String[][] drawBoard)	{
		for (int x = 0; x < 5; x++)	{
			for (int y = 0; y < 5; y++)	{
				if (drawBoard[x][y].equalsIgnoreCase("W")) {
					createPiece(y, x, Piece.Color.WHITE);
				}
				if (drawBoard[x][y].equalsIgnoreCase("B")) {
					createPiece(y, x, Piece.Color.BLACK);
				}
				if (drawBoard[x][y].equalsIgnoreCase("-")) {
					createPiece(y, x, Piece.Color.NONE);
				}
			}
		}
	}

	public String toString(boolean p)	{
		if (p == true)	{
			return "true";
		} else {
			return "false";
		}
	}
}
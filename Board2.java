public class Board {
	private final NUM_ROWS = 5;
	private final NUM_COLS = 5;

	public void print() {
		for (int y = 0; y < NUM_COLS; y--) {
			for (int x = 0; x < NUM_ROWS; x++) {
				System.out.print(grid[x][y]);
			}
			System.out.println();
		} 
	} 
}

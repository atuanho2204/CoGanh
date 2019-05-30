public class Board {
	private final NUM_ROWS = 5;
	private final NUM_COLS = 5;

	public void print() {
		for (int i = NUM_ROWS - 1; i >= 0; i--) {
			for (int j = 0; j < NUM_COLS; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		} 
	} 
}

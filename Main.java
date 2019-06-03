import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Board board = new Board();
		int currentPlayer = 0;
		Player[] player = new Player[2];
		Scanner scan = new Scanner(System.in);

		player[0] = new Player("A", Piece.Color.WHITE);
		player[1] = new Player("B", Piece.Color.BLACK);
		
		while(true) {
			board.print();
			System.out.println("\nPlayer " + player[currentPlayer].getName());
			while(true) {
				int oldX = scan.nextInt();
				int oldY = scan.nextInt();
				int newX = scan.nextInt();
				int newY = scan.nextInt();
				boolean isValid = player[currentPlayer].makeMove(board, oldX, oldY, newX, newY);
				if(isValid) {
					break;
				} else {
					System.out.println("invalid move. Enter again");
				} 

			}
			System.out.println();
			currentPlayer = 1 - currentPlayer;
		}
	}	
}	
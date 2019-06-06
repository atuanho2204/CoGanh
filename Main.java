import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Board board = new Board();
		int currentPlayer = 0;
		Player[] player = new Player[2];
		Scanner scan = new Scanner(System.in);

		System.out.print("Enter player 1 name: ");
		player[0] = new Player(scan.nextLine(), Piece.Color.WHITE);

		System.out.print("Enter player 2 name: ");
		player[1] = new Player(scan.nextLine(), Piece.Color.BLACK);

		boolean game = true;
		
		while (game == true) {
			board.print();
			System.out.println("\nPlayer " + player[currentPlayer].getName());
			while (true) {
				int oldX = scan.nextInt();
				int oldY = scan.nextInt();
				int newX = scan.nextInt();
				int newY = scan.nextInt();
				boolean isValid = player[currentPlayer].makeMove(board, oldX, oldY, newX, newY);
				if (isValid) {
					break;
				} else {
					System.out.println("invalid move. Enter again");
				}

			}
			if (board.checkIfWin == 1) {
				System.out.println(player[currentPlayer].getName() + " WON ");
				game = false;
			}

			System.out.println();
			currentPlayer = 1 - currentPlayer;


		}
	}	
}	
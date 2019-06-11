import java.util.Scanner;
public class Main {

	public static void main(String[] args) {

		Board board = new Board();
		board.makeBoardForTestGame(new TestGame().setDrawBoard());

		// Board board = new Board();
		// board.setUpBoard();

		int currentPlayer = 0;
		Player[] player = new Player[2];
		Scanner scan = new Scanner(System.in);

		System.out.print("Do you want to play with FORCE TO EAT RULE: ");
		String answer = scan.nextLine();

		System.out.print("Enter player 1 name: ");
		player[0] = new Player(scan.nextLine(), Piece.Color.WHITE);

		System.out.print("Enter player 2 name: ");
		player[1] = new Player(scan.nextLine(), Piece.Color.BLACK);

		boolean game = true;
		int applyForceToEat = 0;
		int getOldX = 0;
		int getOldY = 0;
		boolean p;
		if (answer.equalsIgnoreCase("YES"))	{
			board.getAnswer = 1;
		}
		if (answer.equalsIgnoreCase("NO"))	{
			board.getAnswer = 0;
		}

		while (game == true) {
			
			board.print();
			System.out.println("\nPlayer " + player[currentPlayer].getName());

			while (true) {
				int oldX = scan.nextInt();
				int oldY = scan.nextInt();
				int newX = scan.nextInt();
				int newY = scan.nextInt();

				getOldX = oldX;
				getOldY = oldY;

				p = board.checkOtherPieceIsConnected(getOldX,getOldY);

				boolean isValid = player[currentPlayer].makeMove(board, oldX, oldY, newX, newY );

				if (isValid) {
					break;
				} else {	
					System.out.println("InValid move. Enter again");
				}
			}

			if (p == true) {
				board.forceToEatRule(getOldX, getOldY); // remember the old position of this turn if FORCETOEAT rule occurs
				board.applyForceToEat = 1;
			} else {
				board.applyForceToEat = 0;
			}

			
			if (board.checkIfWin == 1) {
				board.print();
				System.out.println(player[currentPlayer].getName() + " WON !!! ");
				game = false;
			}


			System.out.println();
			currentPlayer = 1 - currentPlayer;
		}
	}	
}	
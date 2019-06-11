import java.lang.*;
public class TestGame	{
	private String[][] drawBoard;
	public TestGame() {
		String[][] drawBoard = new String[5][5];
	}
	public String[][] setDrawBoard()	{	
		String[][] drawBoard = {
			{"W" , "W" , "W" , "W" , "W"},

			{"W" , "-" , "-" , "-" , "W"},

			{"B" , "-" , "-" , "W" , "W"},

			{"B" , "-" , "-" , "W" , "B"},

			{"B" , "B" , "W" , "B" , "-"},

			
			};
		return drawBoard;
	}
}
//CheckersBoardTest.java

public class CheckersBoardTest{
	public static void main(String[]args){
		CheckersBoard man = new CheckersBoard();
		//man.display();
		//System.out.println(man.count(CheckersBoard.RED));
		man.move(1,3,2,4);
		man.move(2,6,3,5);
		man.move(4,6,5,5);
		man.move(2,4,4,6);
		man.display();
	}
}
//OODAss2.java
//aka CheckersBoard.java
//Priyonto Saha
//A class that is designed to be used to play a game of checkers.
//Top left of board is coord 1,1
import java.util.*;
import java.math.*;
class CheckersBoard{
	private int[][]board={{2,0,2,0,2,0,2,0},
						  {0,2,0,2,0,2,0,2},
						  {2,0,2,0,2,0,2,0},
						  {0,0,0,0,0,0,0,0},
						  {0,0,0,0,0,0,0,0},
						  {0,1,0,1,0,1,0,1},
						  {1,0,1,0,1,0,1,0},
						  {0,1,0,1,0,1,0,1}};
	public static final int BLACK=1;
	public static final int RED=2;						 
	public int count(int colour){//counts up the number of pieces of a colour
		int counter=0;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if (board[i][j]==colour){
					counter+=1;
				}
			}
		}
		return counter;
	}
	
	public void display(){//Displays the board
		System.out.print("_________________\n|");
		for(int y=0;y<8;y++){
			for(int x=0;x<8;x++){
				if(board[y][x]==0){
					System.out.print(" |");
				}
				if(board[y][x]==1){
					System.out.print("B|");
				}
				if(board[y][x]==2){
					System.out.print("R|");
				}
				if(x==7 && y!=7){
					System.out.print("\n|");
				}
				if(x==7 && y==7){
					System.out.print("\n");
				}
			}	
		}
		System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
	}	
	
	public boolean move(int x1,int y1,int x2,int y2){
		boolean valid=true;
		if (x1<1||x1>8||y1<1||y1>8||x2<1||x2>8||y2<1||y2>8){//Makes sure that no parameters are off board
			valid=false;
		}
		x1-=1;//lowers the coords by one to be nicer in 2d lists
		y1-=1;
		x2-=1;
		y2-=1;
		int counter=0;//counter for jumping, how many jumps it needs to make
		if (valid){
			if (board[y1][x1]==0){//if there is no piece you are moving->Invalid
				valid=false;
			}
			if (board[y2][x2]!=0){//if the place you are moving is occupied->Invalid
				valid=false;
			}
		}
		if (valid){
			if (board[y1][x1]==2){//Red can only move down
				if (y1>=y2){
					valid=false;
				}
			}
			else if (board[y1][x1]==1){//Black can only move up
				if (y1<=y2){
					valid=false;
				}
			}
		}
		if (valid){//Finds out how many jumps
			if((Math.abs(y2-y1))%2==0){
				counter=(Math.abs(y2-y1))/2;
			}
		}
	
		int curx=x1;//temporary variables to trace captured pieces and jump multiple times
		int cury=y1;
		ArrayList<String>capt=new ArrayList<String>();//List of captured pieces this move
		if (valid){
			if (Math.abs(y2-y1)==Math.abs(x2-x1)&&Math.abs(y2-y1)==1){//Checks for a basic move, i.e. no capturing
				board[y2][x2]=board[y1][x1];
				board[y1][x1]=0;
				return true;
			}
			else{
				for(int i=0;i<counter;i++){//jumps the # of times needed
					if (board[y1][x1]==RED){
						if (curx+2<7&&cury+2<7){//jump not out of board
							if (board[cury+1][curx+1]==3-board[y1][x1]){//jumping over other colour
								if (board[cury+2][curx+2]==0){//other side of jump is empty
									capt.add(String.format("%d%d",cury+1,curx+1));//adds the captured coords to a list to change to empty later
									cury=cury+2;
									curx=curx+2;
								}
							}	
						}
						if (curx-2<7&&cury+2<7){//jump not out of board
							if (board[cury+1][curx-1]==3-board[y1][x1]){//jumping over other colour
								if (board[cury+2][curx-2]==0){//other side of jump is empty
									capt.add(String.format("%d%d",cury+1,curx-1));//adds the captured coords to a list to change to empty later
									cury=cury+2;
									curx=curx-2;
								}
							}	
						}
					}
					if (board[y1][x1]==BLACK){
						if (curx+2<7&&cury-2<7){//jump not out of board
							if (board[cury-1][curx+1]==3-board[y1][x1]){//jumping over other colour
								if (board[cury-2][curx+2]==0){//other side of jump is empty
									capt.add(String.format("%d%d",cury-1,curx+1));//adds the captured coords to a list to change to empty later
									cury=cury-2;
									curx=curx+2;
								}
							}	
						}
						if (curx-2<7&&cury-2<7){//jump not out of board
							if (board[cury-1][curx-1]==3-board[y1][x1]){//jumping over other colour
								if (board[cury-2][curx-2]==0){//other side of jump is empty
									capt.add(String.format("%d%d",cury-1,curx-1));//adds the captured coords to a list to change to empty later
									cury=cury-2;
									curx=curx-2;
								}
							}	
						}
					}
				}
			}
		}
		
		if (valid){
			if (cury==(y2)&&curx==(x2)){//checks if the landed place is actually where it should be
				for	(int i=0;i<capt.size();i++){//takes each item in the captured list and removes them from the board
					board[Character.getNumericValue((capt.get(i)).charAt(0))][Character.getNumericValue((capt.get(i)).charAt(1))]=0;
				}
				board[y2][x2]=board[y1][x1];//end spot is where the moved piece lands
				board[y1][x1]=0;//original spot is now empty
				return true;
			}
			else{
				return false;	
			}
		}
		else{
			return false;
		}
	}
}		
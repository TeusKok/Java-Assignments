import java.util.Scanner;
import java.util.InputMismatchException;
public class Nim{
	static int numberOfMatches = 11;
	static int gameType;
	
	public static void main(String []args){
		Scanner input = new Scanner(System.in);
		int move;
		int gameDuration = 0;
		
		while(gameType!=1&&gameType!=2){
			
			System.out.println("Type 2 for a 2 player game and 1 for a game versus the computer.");
			gameType = input.nextInt();
			
		}
		String player = "Player 1";
		
		while(numberOfMatches>0){
			player = whichPlayer(gameDuration);
			System.out.println("\nThere are "+numberOfMatches+" matches.");
			System.out.println("How many do you want to take "+player+"?");
			
				
			move = input.nextInt();
			if(validMove(move)){
				numberOfMatches-=move;
				gameDuration+=1;
				if(gameType == 1&&numberOfMatches>0){
					player = whichPlayer(gameDuration);
					System.out.println("\nThere are "+numberOfMatches+" matches.");
					int computerMove = computerMove();
					System.out.println("The computer takes "+computerMove+" match");
					numberOfMatches-=computerMove();
					gameDuration+=1;
				}
				
			}
			else System.out.println("You can't take that many matches, take between "+1
			+" and "+Math.min(4,numberOfMatches)+" matches.");
		
		}
		input.close();
		System.out.println("\n"+player+" took the last match.");
		System.out.println(player+" lost!");
	}
	
	public static boolean validMove(int move){
		if(move>=1&&move<=4&&move<=numberOfMatches)return true;
		return false;
	}
	
	public static String whichPlayer(int gameDuration){
		if(gameDuration%2==0) return "Player 1";
		else if(gameType==1) return "Computer";
		else return "Player 2";		
	}
	
	public static int computerMove(){
		if((numberOfMatches%5)==1)return 1;
		else return (numberOfMatches-1)%5;
	}
}
	
